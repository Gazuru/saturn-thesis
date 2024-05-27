import {Component, Input, OnInit} from '@angular/core';
import {
  Course,
  CourseService,
  Exam,
  ExamService,
  SubjectOfTeacher,
  SubjectService
} from "../../../../modules/generated";
import {MatCard, MatCardContent, MatCardSubtitle, MatCardTitle} from "@angular/material/card";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatLabel} from "@angular/material/select";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Observable, switchMap} from "rxjs";
import {AsyncPipe} from "@angular/common";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {CourseDialogComponent} from "../course-dialog/course-dialog.component";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {DayOfWeekPipe} from "../../day-of-week.pipe";
import {CourseTypePipe} from "../../course-type.pipe";
import {MatTooltip} from "@angular/material/tooltip";
import {User} from "../../../../modules/generated/model/user";
import {UserService} from "../../../../modules/generated/api/user.service";
import {MatIcon} from "@angular/material/icon";
import {ExamDialogComponent} from "../exam-dialog/exam-dialog.component";

@Component({
  selector: 'app-subject-details',
  standalone: true,
  imports: [
    MatCard,
    MatCardTitle,
    MatCardSubtitle,
    MatCardContent,
    MatFormField,
    MatInput,
    FormsModule,
    MatRadioGroup,
    MatRadioButton,
    MatButton,
    MatLabel,
    ReactiveFormsModule,
    AsyncPipe,
    MatTable,
    MatHeaderCell,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatHeaderRowDef,
    MatColumnDef,
    MatHeaderCellDef,
    MatCellDef,
    MatProgressSpinner,
    DayOfWeekPipe,
    CourseTypePipe,
    MatTooltip,
    MatIconButton,
    MatIcon
  ],
  templateUrl: './subject-details.component.html',
  styleUrl: './subject-details.component.scss'
})
export class SubjectDetailsComponent implements OnInit {

  private _subject!: SubjectOfTeacher;

  @Input({required: true}) set subject(subjectOfTeacher: SubjectOfTeacher) {
    this._subject = subjectOfTeacher;
    this.ngOnInit();
    this.subjectForm.controls["subjectName"].setValue(subjectOfTeacher.name);
    this.subjectForm.controls["creditAmount"].setValue(subjectOfTeacher.creditIndex);
    this.subjectForm.controls["period"].setValue(subjectOfTeacher.registerablePeriodOfYear);
  }

  get subject(): SubjectOfTeacher {
    return this._subject;
  }

  subjectForm!: FormGroup;

  courses$?: Observable<Course[]>;
  students$?: Observable<User[]>;
  exams$?: Observable<Exam[]>;

  students?: User[];
  exams?: Exam[];

  displayedColumns: string[] = ['name', 'courseType', 'startTime', 'length', 'dayOfWeek', 'location', 'courseTeachers'];

  studentDisplayedColumns: string[] = ['firstName', 'lastName', 'saturnCode', 'remove']

  examDisplayedColumns: string[] = ['examDate', 'startTime', 'location'];

  constructor(protected readonly subjectService: SubjectService,
              protected readonly courseService: CourseService,
              protected readonly userService: UserService,
              protected readonly examService: ExamService,
              private snackBar: MatSnackBar,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.courses$ = this.courseService.getCoursesOfSubject(this.subject.id!);
    this.exams$ = this.examService.getExamsOfSubject(this.subject.id!);
    this.students$ = this.subjectService.getRegisteredStudents(this.subject.id!).pipe(
      switchMap((uuids: string[]) => this.userService.getStudents(uuids))
    );
    this.subjectForm = new FormGroup({
      subjectName: new FormControl(this.subject.name, Validators.required),
      creditAmount: new FormControl(this.subject.creditIndex, Validators.required),
      period: new FormControl(this.subject.registerablePeriodOfYear, Validators.required),
    })
    this.getStudentsOfSubject();
    this.getExamsOfSubject();
  }

  getStudentsOfSubject() {
    this.students$?.subscribe(users => this.students = users);
  }

  getExamsOfSubject() {
    this.exams$?.subscribe(exams => this.exams = exams);
  }

  saveSubject() {
    this.subjectService.updateSubject(this.subject.id as string,
      {
        subjectName: this.subjectForm.controls["subjectName"].value,
        creditAmount: this.subjectForm.controls["creditAmount"].value,
        period: this.subjectForm.controls["period"].value,
      }).subscribe(() => {
      this.subject.name = this.subjectForm.controls["subjectName"].value;
      this.subject.creditIndex = this.subjectForm.controls["creditAmount"].value;
      this.subject.registerablePeriodOfYear = this.subjectForm.controls["period"].value;
      this.snackBar.open("Tárgy sikeresen mentve!");
    })
  }

  openCourseDialog() {
    const dialogRef = this.dialog.open(CourseDialogComponent, {data: {}, width: '33%'})

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.courseService.createCourseForSubject(this.subject.id as string,
          {
            courseName: result.name,
            courseType: result.courseType,
            startTime: result.startTime,
            length: result.length,
            dayOfWeek: result.dayOfWeek,
            location: result.location || '',
            courseDeputies: result.courseDeputies,
          }).subscribe(() => this.snackBar.open("Kurzus sikeresen létrehozva!"));
      }
    })
  }

  openExamDialog() {
    const dialogRef = this.dialog.open(ExamDialogComponent, {data: {}, width: '33%'});

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.examService.createExamForSubject(this.subject.id as string, {
          examDate: result.examDate,
          startTime: result.startTime,
          location: result.location,
        }).subscribe(() => {
          this.snackBar.open("Vizsga sikeresen létrehozva!");
          this.getExamsOfSubject();
        })
      }
    })
  }

  removeStudent(student: User) {
    this.subjectService.removeStudentRegistration(this.subject.id as string, student.studentInformation!.id as string)
      .subscribe(() => {
        this.snackBar.open("Hallgató sikeresen eltávolítva!");
        this.getStudentsOfSubject();
      })
  }
}
