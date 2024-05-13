import {Injectable} from "@angular/core";
import Keycloak from "keycloak-js";

export interface UserProfile {
  sub: string;
  email: string;
  given_name: string;
  family_name: string;
  token: string;
  roles: string[];
}

@Injectable({providedIn: "root"})
export class KeycloakService {
  _keycloak: Keycloak | undefined;
  profile: UserProfile | undefined;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: "http://localhost:8400",
        realm: "saturn",
        clientId: "saturn"
      });
    }
    return this._keycloak;
  }

  async init() {
    const authenticated = await this.keycloak.init({
      onLoad: 'login-required'
    });

    if (!authenticated) {
      return authenticated;
    }
    this.profile = (await this.keycloak.loadUserInfo()) as unknown as UserProfile;
    this.profile.token = this.keycloak.token || "";

    return true;
  }

  get isStudent(): boolean {
    return this.keycloak?.hasResourceRole("student", "saturn");
  }

  get isTeacher(): boolean {
    return this.keycloak?.hasResourceRole("teacher", "saturn");
  }

  get isManager(): boolean {
    return this.keycloak?.hasResourceRole("manager", "saturn");
  }

  get isAdmin(): boolean {
    return this.isStudent && this.isTeacher && this.isManager;
  }


  login() {
    return this.keycloak.login();
  }

  logout() {
    return this.keycloak.logout({redirectUri: "http://localhost:4200/"});
  }
}
