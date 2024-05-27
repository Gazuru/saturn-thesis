import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatLabel, MatOption, MatSelect} from "@angular/material/select";
import {CourseTypePipe} from "../../course-type.pipe";
import {Course} from "../../../../modules/generated";
import {DayOfWeekPipe} from "../../day-of-week.pipe";
import {MatButton} from "@angular/material/button";
import {UserService} from "../../../../modules/generated/api/user.service";
import {Observable} from "rxjs";
import {AsyncPipe} from "@angular/common";
import {User} from "../../../../modules/generated/model/user";
import CourseTypeEnum = Course.CourseTypeEnum;
import DayOfWeekEnum = Course.DayOfWeekEnum;


@Component({
  selector: 'app-course-dialog',
  standalone: true,
  imports: [
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatFormField,
    MatInput,
    MatLabel,
    MatSelect,
    MatOption,
    CourseTypePipe,
    DayOfWeekPipe,
    MatButton,
    ReactiveFormsModule,
    AsyncPipe
  ],
  templateUrl: './course-dialog.component.html',
  styleUrl: './course-dialog.component.scss'
})
export class CourseDialogComponent implements OnInit {
  form: FormGroup = new FormGroup({
    name: new FormControl(this.data.name || '', Validators.required),
    courseType: new FormControl(this.data.courseType || '', Validators.required),
    startTime: new FormControl(this.data.startTime || '', Validators.required),
    length: new FormControl(this.data.length || '', Validators.required),
    dayOfWeek: new FormControl(this.data.dayOfWeek || '', Validators.required),
    location: new FormControl(this.data.location || ''),
    courseDeputies: new FormControl(this.data.courseDeputies || [], Validators.required)
  });

  courseTypes: CourseTypeEnum[] = ['LECTURE', 'PRACTICE', 'LABORATORY', 'EXAM'];
  daysOfWeek: DayOfWeekEnum[] = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY'];

  teachers$?: Observable<User[]>;

  constructor(private dialogRef: MatDialogRef<CourseDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              protected readonly userService: UserService) {
  }

  ngOnInit(): void {
    this.teachers$ = this.userService.getTeachers();
  }

  onSave() {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  onCancel() {
    this.dialogRef.close(null);
  }
}
