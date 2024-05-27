import {Component, OnInit} from '@angular/core';
import {
  SemesterRegistrationOfStudent,
  SemesterService,
  SubjectRegistrationOfStudent,
  SubjectService
} from "../../../../modules/generated";
import {Observable} from "rxjs";
import {MatFormField} from "@angular/material/form-field";
import {MatLabel, MatOption, MatSelect} from "@angular/material/select";
import {FormsModule} from "@angular/forms";
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
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";

@Component({
  selector: 'app-averages',
  standalone: true,
  imports: [
    MatFormField,
    MatSelect,
    FormsModule,
    AsyncPipe,
    MatOption,
    MatLabel,
    MatTable,
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
    MatHeaderCellDef
  ],
  templateUrl: './averages.component.html',
  styleUrl: './averages.component.scss'
})
export class AveragesComponent implements OnInit {

  semesterRegistrations$?: Observable<SemesterRegistrationOfStudent[]>;

  selectedSemester?: SemesterRegistrationOfStudent;

  subjects?: SubjectRegistrationOfStudent[];

  regularAverage?: number = undefined;
  weightedAverage?: number = undefined;

  displayedColumns = ['name', 'creditIndex', 'grade'];

  constructor(private readonly semesterService: SemesterService,
              private readonly subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.semesterRegistrations$ = this.semesterService.getSemesterRegistrations();
  }

  updateSubjects() {
    this.subjectService.getSubjectsOfSemester(this.selectedSemester?.id as string).subscribe(data => {
      this.subjects = data;
      this.calcAverage();
    });
  }

  calcAverage() {
    const filteredSubjects = this.subjects?.filter(s => !!s.grade);

    if (filteredSubjects) {
      let sum: number = 0;
      let weightedSum: number = 0;

      filteredSubjects.forEach(subject => {
        sum += subject.grade!;
        weightedSum += subject.creditIndex! * subject.grade!;
      })

      this.regularAverage = sum / filteredSubjects.length;
      this.weightedAverage = weightedSum / 30;
    }
  }
}
