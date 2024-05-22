import {Component} from '@angular/core';
import {MatToolbar} from "@angular/material/toolbar";
import {MatMenu, MatMenuItem, MatMenuTrigger} from "@angular/material/menu";
import {MatButton, MatIconButton} from "@angular/material/button";

import {HeaderComponent} from "../../../auth/components/header/header.component";
import {KeycloakService} from "../../../auth/services/keycloak.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MatToolbar,
    MatMenu,
    MatIconButton,
    MatMenuTrigger,
    MatButton,
    MatMenuItem,
    HeaderComponent,
    RouterLink
],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  constructor(protected readonly keycloakService: KeycloakService) {
  }

}
