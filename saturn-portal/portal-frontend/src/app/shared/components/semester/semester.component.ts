import {Component, OnInit} from '@angular/core';
import {MatFormField, MatLabel, MatOption, MatSelect} from "@angular/material/select";
import {AsyncPipe} from "@angular/common";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {MatButton} from "@angular/material/button";
import {SemesterService} from "../../../../modules/generated/api/semester.service";
import {filter, Observable} from "rxjs";
import {Semester} from "../../../../modules/generated/model/semester";
import {FormsModule} from "@angular/forms";
import {RegisterForCurrentSemesterRequest, SemesterRegistration} from "../../../../modules/generated";
import {MatIcon} from "@angular/material/icon";
import {MatSnackBar} from "@angular/material/snack-bar";
import StatusEnum = RegisterForCurrentSemesterRequest.StatusEnum;

@Component({
  selector: 'app-semester',
  standalone: true,
  imports: [
    MatSelect,
    MatFormField,
    MatLabel,
    MatOption,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatRadioGroup,
    MatRadioButton,
    MatButton,
    AsyncPipe,
    FormsModule,
    MatIcon
  ],
  templateUrl: './semester.component.html',
  styleUrl: './semester.component.scss'
})
export class SemesterComponent implements OnInit {

  currentSemester$?: Observable<Semester>;
  currentRegistration$?: Observable<SemesterRegistration>

  statuses = ['ACTIVE', 'PASSIVE'];
  selectedStatus: string = this.statuses[1];

  registered: boolean = false;

  constructor(protected readonly semesterService: SemesterService, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.currentSemester$ = this.semesterService.getCurrentSemester();
    this.currentRegistration$ = this.semesterService.getCurrentSemesterRegistration().pipe(filter(registration => !!registration));
    this.currentRegistration$.subscribe(registration => {
      this.registered = true;
      this.selectedStatus = registration.status ? registration.status : 'PASSIVE';
    })
  }

  registerForCurrent() {
    this.semesterService.registerForCurrentSemester({status: this.selectedStatus as StatusEnum}).subscribe(() => {
      this.registered = true;
      this.snackBar.open("Szemeszter jelentkezés sikeresen elmentve!");
    });
  }
}
