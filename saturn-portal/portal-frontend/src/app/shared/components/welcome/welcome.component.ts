import {Component} from '@angular/core';

import {KeycloakService} from "../../../auth/services/keycloak.service";

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [],
  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.scss'
})
export class WelcomeComponent {

  constructor(protected readonly keycloakService: KeycloakService) {
  }

}
