import {Component, OnInit} from '@angular/core';
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {MatLabel, MatOption, MatSelect} from "@angular/material/select";
import {MatButton} from "@angular/material/button";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {UserService} from "../../../../modules/generated/api/user.service";
import {User} from "../../../../modules/generated/model/user";
import {Observable} from "rxjs";
import {AsyncPipe} from "@angular/common";
import {CreateSubjectRequest, SubjectService} from "../../../../modules/generated";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-subject-creation',
  standalone: true,
  imports: [
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatFormField,
    MatInput,
    MatLabel,
    MatRadioGroup,
    MatRadioButton,
    MatSelect,
    MatOption,
    MatButton,
    ReactiveFormsModule,
    AsyncPipe
  ],
  templateUrl: './subject-creation.component.html',
  styleUrl: './subject-creation.component.scss'
})
export class SubjectCreationComponent implements OnInit {

  teachers$?: Observable<User[]>;

  constructor(protected readonly userService: UserService,
              protected readonly subjectService: SubjectService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.teachers$ = this.userService.getTeachers();
  }

  subjectForm = new FormGroup({
    subjectName: new FormControl('', Validators.required),
    creditAmount: new FormControl(1, Validators.required),
    period: new FormControl('SPRING', Validators.required),
    subjectDeputies: new FormControl([], Validators.required)
  })


  saveSubject() {
    this.subjectService.createSubject({
      subjectName: this.subjectForm.value.subjectName,
      creditAmount: this.subjectForm.value.creditAmount,
      period: this.subjectForm.value.period,
      subjectDeputies: this.subjectForm.value.subjectDeputies
    } as CreateSubjectRequest).subscribe(() => this.snackBar.open("Tárgy sikeresen létrehozva!"));
  }
}
