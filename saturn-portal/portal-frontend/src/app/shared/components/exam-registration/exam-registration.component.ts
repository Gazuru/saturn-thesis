import {Component, OnInit} from '@angular/core';
import {Exam, ExamRegistration, ExamService} from "../../../../modules/generated";
import {Observable} from "rxjs";
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
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {MatTooltip} from "@angular/material/tooltip";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-exam-registration',
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
    MatIcon,
    MatIconButton,
    MatProgressSpinner,
    MatRow,
    MatRowDef,
    MatTable,
    MatTooltip,
    MatHeaderCellDef
  ],
  templateUrl: './exam-registration.component.html',
  styleUrl: './exam-registration.component.scss'
})
export class ExamRegistrationComponent implements OnInit {

  exams$?: Observable<Exam[]>;
  examRegistrations$?: Observable<ExamRegistration[]>;

  examRegistrations?: ExamRegistration[];

  displayedColumns = ['examDate', 'startTime', 'location', 'signUp'];

  constructor(private readonly examService: ExamService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.exams$ = this.examService.getAllExams();
    this.examRegistrations$ = this.examService.getAllExamRegistrations();
    this.getExamRegistrations();
  }

  getExamRegistrations() {
    this.examRegistrations$?.subscribe(data => this.examRegistrations = data);
  }

  hasCurrentRegistration(exam: Exam) {
    return this.examRegistrations?.some(registration => registration.examUuid == exam.id);
  }

  getButtonIcon(exam: Exam): string {
    return this.hasCurrentRegistration(exam) ? 'check' : 'add';
  }

  handleButtonClick(exam: Exam) {
    if (!this.hasCurrentRegistration(exam)) {
      this.examService.registerForExam(exam.id as string).subscribe(() => {
        this.snackBar.open("Sikeres vizsgajelentkezés!");
        this.getExamRegistrations();
      })
    }
  }
}
