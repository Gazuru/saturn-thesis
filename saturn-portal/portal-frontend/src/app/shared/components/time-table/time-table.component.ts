import {Component, OnInit} from '@angular/core';
import {Course, CourseService} from "../../../../modules/generated";
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
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {map, Observable} from "rxjs";
import {AsyncPipe} from "@angular/common";
import CourseTypeEnum = Course.CourseTypeEnum;

@Component({
  selector: 'app-time-table',
  standalone: true,
  imports: [
    CourseTypePipe,
    DayOfWeekPipe,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatIcon,
    MatIconButton,
    MatRow,
    MatRowDef,
    MatTable,
    MatTooltip,
    AsyncPipe,
    MatHeaderCellDef
  ],
  templateUrl: './time-table.component.html',
  styleUrl: './time-table.component.scss'
})
export class TimeTableComponent implements OnInit {

  myCourses$?: Observable<Course[]>;

  displayedColumns: Course.DayOfWeekEnum[] = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY'];

  constructor(private readonly courseService: CourseService) {
  }

  ngOnInit() {
    this.myCourses$ = this.courseService.getCoursesOfCurrentSemester().pipe(
      map(courses => {
        courses.sort((a, b) => {
          const timeA = this.parseTime(a.startTime as string);
          const timeB = this.parseTime(b.startTime as string);
          return timeA - timeB;
        })
        return courses;
      })
    );
  }

  parseTime(startTime: string) {
    const [hours, minutes] = startTime.split(':').map(Number);
    return (hours * 3600) + (minutes * 60);
  }

  getCourseInformation(course: Course): string {
    const [hours, minutes] = course.startTime!.split(':').map(Number);
    const date = new Date();
    date.setHours(hours);
    date.setMinutes(minutes);
    date.setSeconds(0);
    date.setMilliseconds(0);
    date.setMinutes(date.getMinutes() + course.length!);
    const endTime = date.getHours() + ':' + date.getMinutes()
    return `${course.startTime!.substring(0, course.startTime!.length - 3)} - ${endTime} ${course.name} (${course.courseType as CourseTypeEnum})`;
  }
}
