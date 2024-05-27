import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AsyncPipe} from "@angular/common";
import {CourseTypePipe} from "../../course-type.pipe";
import {DayOfWeekPipe} from "../../day-of-week.pipe";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import {RequestOfManager} from "../../../../modules/generated/model/requestOfManager";
import {RequestStatusPipe} from "../../request-status.pipe";
import {Request} from "../../../../modules/generated/model/request";
import {RequestTypePipe} from "../../request-type.pipe";
import StatusEnum = RequestOfManager.StatusEnum;
import RequestTypeEnum = Request.RequestTypeEnum;

@Component({
  selector: 'app-request-of-manager-dialog',
  standalone: true,
  imports: [
    AsyncPipe,
    CourseTypePipe,
    DayOfWeekPipe,
    MatButton,
    MatFormField,
    MatInput,
    MatLabel,
    MatOption,
    MatSelect,
    ReactiveFormsModule,
    RequestStatusPipe,
    RequestTypePipe
  ],
  templateUrl: './request-of-manager-dialog.component.html',
  styleUrl: './request-of-manager-dialog.component.scss'
})
export class RequestOfManagerDialogComponent {

  form: FormGroup = new FormGroup({
    requestType: new FormControl({value: this.data.request.requestType || '', disabled: true},),
    status: new FormControl(this.data.request.status || '', Validators.required),
    description: new FormControl(this.data.request.description || '', Validators.required),
    comment: new FormControl(this.data.request.comment || ''),
  })

  statuses: StatusEnum[] = ['NEW', 'IN_PROGRESS', 'REQUESTER_FEEDBACK', 'IN_REVIEW', 'ACCEPTED', 'DENIED']
  requestTypes: RequestTypeEnum[] = ['FAIRNESS', 'DISMISSAL', 'SUBJECT', 'OTHER'];

  constructor(private dialogRef: MatDialogRef<RequestOfManagerDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onCancel() {
    this.dialogRef.close(null);
  }

  onSave() {
    this.dialogRef.close(this.form.value);
  }
}
