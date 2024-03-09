import {Component, OnInit} from '@angular/core';
import {DummyService, User} from "../modules/generated";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'saturn-portal-frontend';
  user$?: Observable<User>;
  user?: User;

  constructor(protected readonly dummyService: DummyService) {
  }

  ngOnInit() {
    this.user$ = this.dummyService.getTest();
  }

  onClick(){
    this.user$?.subscribe(data => this.user = data);
  }
}
