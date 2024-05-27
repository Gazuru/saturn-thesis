import {Component, OnInit} from '@angular/core';
import {RequestService} from "../../../../modules/generated/api/request.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {RequestOfManager} from "../../../../modules/generated/model/requestOfManager";
import {Observable} from "rxjs";
import {MatDialog} from "@angular/material/dialog";
import {AsyncPipe} from "@angular/common";
import {CourseTypePipe} from "../../course-type.pipe";
import {DayOfWeekPipe} from "../../day-of-week.pipe";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
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
import {RequestStatusPipe} from "../../request-status.pipe";
import {RequestTypePipe} from "../../request-type.pipe";
import {RequestOfManagerDialogComponent} from "../request-of-manager-dialog/request-of-manager-dialog.component";

@Component({
  selector: 'app-request-manager',
  standalone: true,
  imports: [
    AsyncPipe,
    CourseTypePipe,
    DayOfWeekPipe,
    MatButton,
    MatCard,
    MatCardContent,
    MatCardTitle,
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
    RequestStatusPipe,
    RequestTypePipe
  ],
  templateUrl: './request-manager.component.html',
  styleUrl: './request-manager.component.scss'
})
export class RequestManagerComponent implements OnInit {

  myRequests$?: Observable<RequestOfManager[]>;

  myRequests?: RequestOfManager[];

  displayedColumns = ['requestType', 'status', 'description', 'comment', 'requester']

  constructor(private readonly requestService: RequestService,
              private snackBar: MatSnackBar,
              private matDialog: MatDialog) {
  }

  ngOnInit() {
    this.myRequests$ = this.requestService.getMyRequests();
    this.getMyRequests();
  }

  getMyRequests() {
    this.myRequests$?.subscribe(data => this.myRequests = data);
  }

  handleClick(row: RequestOfManager) {
    this.matDialog.open(RequestOfManagerDialogComponent, {
      data: {request: row},
      width: '50%',
      autoFocus: false
    }).afterClosed().subscribe(result => {
      if (result) {
        this.requestService.updateRequest(row.id as string, {
          description: result.description,
          comment: result.comment,
          status: result.status,
        }).subscribe(() => {
          this.snackBar.open("Kérvény sikeresen frissítve!");
          this.getMyRequests();
        })
      }
    })
  }
}
