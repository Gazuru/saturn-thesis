import {Component, Inject, OnInit} from '@angular/core';
import {filter, map, Observable} from "rxjs";
import {Course, CourseService} from "../../../../modules/generated";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AsyncPipe} from "@angular/common";
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
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-courses-of-subject-dialog',
  standalone: true,
  imports: [
    AsyncPipe,
    CourseTypePipe,
    DayOfWeekPipe,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatProgressSpinner,
    MatRow,
    MatRowDef,
    MatTable,
    MatTooltip,
    MatHeaderCellDef,
    MatIcon,
    MatIconButton
  ],
  templateUrl: './courses-of-subject-dialog.component.html',
  styleUrl: './courses-of-subject-dialog.component.scss'
})
export class CoursesOfSubjectDialogComponent implements OnInit {

  displayedColumns: string[] = ['name', 'courseType', 'startTime', 'length', 'dayOfWeek', 'location', 'courseTeachers', 'signUp'];

  courses$?: Observable<Course[]>;
  registeredCourseUuids$?: Observable<string[]>;

  registeredCourseUuids?: string[];

  constructor(private dialogRef: MatDialogRef<CoursesOfSubjectDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              protected readonly courseService: CourseService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.courses$ = this.courseService.getCoursesOfSubject(this.data.subject.id);
    this.registeredCourseUuids$ = this.courseService.getCourseRegistrationsOfSubject(this.data.subject.id)
      .pipe(
        filter(data => !!data),
        map(data =>
          data
            .map(data => data.courseUuid)
            .filter((data): data is string => !!data)
        )
      );
    this.getRegisteredCourseUuids();
  }

  getButtonIcon(course: Course) {
    return this.hasRegistrationFor(course) ? 'check' : 'add';
  }

  hasRegistrationFor(course: Course) {
    return this.registeredCourseUuids!.some(uuid => uuid == course.id);
  }

  getRegisteredCourseUuids() {
    this.registeredCourseUuids$?.subscribe(uuids => this.registeredCourseUuids = uuids);
  }

  handleButtonClick(course: Course) {
    if (!this.hasRegistrationFor(course)) {
      this.courseService.registerForCourse(course.id as string).subscribe(() => {
        this.snackBar.open("Sikeres kurzusfelvétel!");
        this.getRegisteredCourseUuids();
      })
    } else {
      console.log("Already has registration");
    }
  }
}
