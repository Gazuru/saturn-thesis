import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {AsyncPipe} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatOption} from "@angular/material/autocomplete";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {MatSelect} from "@angular/material/select";
import {RequestService} from "../../../../modules/generated/api/request.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Observable} from "rxjs";
import {RequestOfStudent} from "../../../../modules/generated/model/requestOfStudent";
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
import {RequestStatusPipe} from "../../request-status.pipe";
import {RequestTypePipe} from "../../request-type.pipe";
import {MatTooltip} from "@angular/material/tooltip";

@Component({
  selector: 'app-request-student',
  standalone: true,
  imports: [
    AsyncPipe,
    FormsModule,
    MatButton,
    MatCard,
    MatCardContent,
    MatCardTitle,
    MatFormField,
    MatInput,
    MatLabel,
    MatOption,
    MatRadioButton,
    MatRadioGroup,
    MatSelect,
    ReactiveFormsModule,
    MatTable,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRow,
    MatRowDef,
    MatHeaderCellDef,
    RequestStatusPipe,
    RequestTypePipe,
    MatTooltip
  ],
  templateUrl: './request-student.component.html',
  styleUrl: './request-student.component.scss'
})
export class RequestStudentComponent implements OnInit {

  requestFormGroup: FormGroup = new FormGroup({
    requestType: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    comment: new FormControl(''),
  })

  requests$?: Observable<RequestOfStudent[]>;
  requests?: RequestOfStudent[];

  displayedColumns = ['status', 'requestType', 'description', 'comment', 'assignee'];

  constructor(private readonly requestService: RequestService,
              private snackBar: MatSnackBar,) {
  }

  ngOnInit() {
    this.requests$ = this.requestService.getRequests();
    this.getRequestsOfStudent();
  }

  getRequestsOfStudent() {
    this.requests$?.subscribe(data => this.requests = data);
  }

  sendRequest() {
    this.requestService.createRequest({
      requestType: this.requestFormGroup.controls["requestType"].value,
      description: this.requestFormGroup.controls["description"].value,
      comment: this.requestFormGroup.controls["comment"].value || '',
    }).subscribe(() => {
        this.snackBar.open("Kérvény sikeresen létrehozva!");
        this.getRequestsOfStudent();
      }
    )
  }

}
