import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "../../../auth/services/keycloak.service";
import {RequestStudentComponent} from "../request-student/request-student.component";
import {RequestManagerComponent} from "../request-manager/request-manager.component";

@Component({
  selector: 'app-requests',
  standalone: true,
  imports: [
    RequestStudentComponent,
    RequestManagerComponent
  ],
  templateUrl: './requests.component.html',
  styleUrl: './requests.component.scss'
})
export class RequestsComponent implements OnInit {

  constructor(protected readonly keycloakService: KeycloakService) {
  }

  ngOnInit() {
  }


}
