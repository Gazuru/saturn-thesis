import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../modules/generated/api/user.service";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import KeycloakAdminClient from "@keycloak/keycloak-admin-client";
import {CreateUserRequest} from "../../../../modules/generated/model/createUserRequest";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";

@Component({
  selector: 'app-invite',
  standalone: true,
  imports: [
    FormsModule,
    MatButton,
    MatCard,
    MatCardContent,
    MatCardTitle,
    MatFormField,
    MatInput,
    MatLabel,
    MatOption,
    MatSelect,
    ReactiveFormsModule,
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatNativeDateModule
  ],
  templateUrl: './invite.component.html',
  styleUrl: './invite.component.scss'
})
export class InviteComponent implements OnInit {


  userForm: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    saturnCode: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    dateOfBirth: new FormControl(new Date()),
    locationOfBirth: new FormControl(''),
    roles: new FormControl([], Validators.required),
  })

  roleList = ['student', 'manager', 'teacher'];

  keycloakAdminClient = new KeycloakAdminClient({
    baseUrl: 'http://localhost:8400',
    realmName: 'saturn'
  });

  constructor(private readonly userService: UserService,
              private snackBar: MatSnackBar) {
  }

  async ngOnInit() {
    await this.keycloakAdminClient.auth({
      username: 'saturn',
      password: 'saturn',
      grantType: 'password',
      clientId: 'saturn',
      totp: '123456'
    });
  }

  async createKeycloakUser(userRequest: CreateUserRequest, email: string) {
    await this.keycloakAdminClient.users.create({
      realm: 'saturn',
      username: userRequest.saturnCode,
      email: email,
      enabled: true,
      emailVerified: true,
      requiredActions: ["UPDATE_PASSWORD"],
      groups: userRequest.roles,
      firstName: userRequest.firstName,
      lastName: userRequest.lastName,
    })
  }

  onSubmit() {
    const userRequest: CreateUserRequest = {
      firstName: this.userForm.controls["firstName"].value,
      lastName: this.userForm.controls["lastName"].value,
      saturnCode: this.userForm.controls["saturnCode"].value,
      dateOfBirth: this.userForm.controls["dateOfBirth"].value,
      locationOfBirth: this.userForm.controls["locationOfBirth"].value,
      roles: this.userForm.controls["roles"].value,
    };
    this.userService.createUser(userRequest).subscribe(() => {
      this.snackBar.open("Felhasználó létrehozva!")
      this.createKeycloakUser(userRequest, this.userForm.controls["email"].value).then(() => console.log("user created in kc"));
    })
  }


}
