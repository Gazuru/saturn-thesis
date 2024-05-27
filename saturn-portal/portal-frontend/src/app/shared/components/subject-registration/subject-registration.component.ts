import {Component, OnInit} from '@angular/core';
import {filter, map, Observable} from "rxjs";
import {SemesterRegistration, SemesterService, SubjectOfStudent, SubjectService} from "../../../../modules/generated";
import {AsyncPipe} from "@angular/common";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {CourseTypePipe} from "../../course-type.pipe";
import {DayOfWeekPipe} from "../../day-of-week.pipe";
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
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {CoursesOfSubjectDialogComponent} from "../courses-of-subject-dialog/courses-of-subject-dialog.component";

@Component({
  selector: 'app-subject-registration',
  standalone: true,
  imports: [
    AsyncPipe,
    MatProgressSpinner,
    CourseTypePipe,
    DayOfWeekPipe,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRow,
    MatRowDef,
    MatTable,
    MatTooltip,
    MatHeaderCellDef,
    MatIcon,
    MatIconButton
  ],
  templateUrl: './subject-registration.component.html',
  styleUrl: './subject-registration.component.scss'
})
export class SubjectRegistrationComponent implements OnInit {

  currentSemesterRegistration$?: Observable<SemesterRegistration>;

  subjects$?: Observable<SubjectOfStudent[]>;
  registeredSubjectUuids$?: Observable<string[]>;

  registeredSubjectUuids?: string[];

  displayedColumns: string[] = ['name', 'creditIndex', 'subjectDeputies', 'signUp'];


  constructor(protected readonly semesterService: SemesterService,
              protected readonly subjectService: SubjectService,
              private snackBar: MatSnackBar,
              private dialog: MatDialog) {
  }


  ngOnInit(): void {
    this.currentSemesterRegistration$ = this.semesterService.getCurrentSemesterRegistration();
    this.subjects$ = this.subjectService.getSubjectsOfCurrentSemester();
    this.registeredSubjectUuids$ = this.subjectService.getSubjectRegistrationsOfCurrentSemester()
      .pipe(
        filter(data => !!data),
        map(data =>
          data
            .map(data => data.subjectUuid)
            .filter((data): data is string => !!data)
        ),
      );
    this.getRegisteredSubjectUuids();
  }

  handleRowClick(subject: SubjectOfStudent) {
    if (this.getClass(subject) == 'clickable') {
      this.dialog.open(CoursesOfSubjectDialogComponent, {data: {subject: subject}, width: '50%', autoFocus: false});
    }
  }

  getClass(subject: SubjectOfStudent): string {
    return this.hasRegistrationFor(subject) ? 'clickable' : '';
  }

  hasRegistrationFor(subject: SubjectOfStudent) {
    return this.registeredSubjectUuids!.some(uuid => uuid == subject.id)
  }

  getRegisteredSubjectUuids() {
    this.registeredSubjectUuids$?.subscribe(uuids => this.registeredSubjectUuids = uuids);
  }

  getButtonIcon(subject: SubjectOfStudent): string {
    return this.hasRegistrationFor(subject) ? 'check' : 'add';
  }

  handleButtonClick($event: Event, subject: SubjectOfStudent) {
    $event.stopPropagation();

    if (!this.hasRegistrationFor(subject)) {
      console.log('No registration, registering')

      this.subjectService.registerForSubjectOfCurrentSemester(subject.id as string).subscribe(() => {
        this.snackBar.open("Sikeres tárgyfelvétel!");
        this.getRegisteredSubjectUuids();
      })
    } else {
      console.log("Already has registration");
    }
  }
}
