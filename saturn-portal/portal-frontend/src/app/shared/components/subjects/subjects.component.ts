import {Component, OnInit} from '@angular/core';
import {SubjectOfTeacher, SubjectService} from "../../../../modules/generated";
import {Observable} from "rxjs";
import {MatFormField, MatLabel, MatOption, MatSelect} from "@angular/material/select";
import {AsyncPipe} from "@angular/common";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {FormsModule} from "@angular/forms";
import {SubjectDetailsComponent} from "../subject-details/subject-details.component";

@Component({
  selector: 'app-subjects',
  standalone: true,
  imports: [
    MatSelect,
    MatOption,
    AsyncPipe,
    MatFormField,
    MatProgressSpinner,
    MatLabel,
    FormsModule,
    SubjectDetailsComponent
  ],
  templateUrl: './subjects.component.html',
  styleUrl: './subjects.component.scss'
})
export class SubjectsComponent implements OnInit {

  subjects$?: Observable<SubjectOfTeacher[]>;

  selectedSubject?: SubjectOfTeacher;

  constructor(protected readonly subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.subjects$ = this.subjectService.getSubjectsOfTeacher();
  }
}
