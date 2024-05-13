import {Component, inject} from "@angular/core";
import {RouterLink} from "@angular/router";
import {MatButtonModule} from "@angular/material/button";
import {AsyncPipe} from "@angular/common";
import {SecurityStore} from "../../../core/security-store";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [RouterLink, MatButtonModule, AsyncPipe],
})
export class HeaderComponent {
  securityStore = inject(SecurityStore);
  user = this.securityStore.user;
}
