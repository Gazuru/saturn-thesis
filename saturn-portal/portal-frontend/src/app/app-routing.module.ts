import {inject, NgModule} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterModule,
  RouterStateSnapshot,
  Routes
} from '@angular/router';
import {KeycloakService} from "./auth/services/keycloak.service";
import {SemesterComponent} from "./shared/components/semester/semester.component";
import {WelcomeComponent} from "./shared/components/welcome/welcome.component";
import {SubjectsComponent} from "./shared/components/subjects/subjects.component";
import {SubjectRegistrationComponent} from "./shared/components/subject-registration/subject-registration.component";
import {TimeTableComponent} from "./shared/components/time-table/time-table.component";
import {AveragesComponent} from "./shared/components/averages/averages.component";
import {ExamRegistrationComponent} from "./shared/components/exam-registration/exam-registration.component";
import {GradingComponent} from "./shared/components/grading/grading.component";
import {RequestsComponent} from "./shared/components/requests/requests.component";
import {InviteComponent} from "./shared/components/invite/invite.component";
import {MonetaryComponent} from "./shared/components/monetary/monetary.component";

const isLoggedInGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(KeycloakService).isAuthenticated ? true : inject(Router).createUrlTree(['/']);
}

const isStudentGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(KeycloakService).isStudent ? true : inject(Router).createUrlTree(['/']);
}

const isTeacherGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(KeycloakService).isTeacher ? true : inject(Router).createUrlTree(['/']);
}

const isManagerGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(KeycloakService).isManager ? true : inject(Router).createUrlTree(['/']);
}

const routes: Routes = [{
  path: 'welcome',
  component: WelcomeComponent,
  canActivate: [isLoggedInGuard]
},
  {
    path: 'semesters',
    component: SemesterComponent,
    canActivate: [isLoggedInGuard, isStudentGuard]
  },
  {
    path: 'subjects',
    component: SubjectsComponent,
    canActivate: [isLoggedInGuard, isTeacherGuard]
  },
  {
    path: 'subject-registration',
    component: SubjectRegistrationComponent,
    canActivate: [isLoggedInGuard, isStudentGuard]
  },
  {
    path: 'timetable',
    component: TimeTableComponent,
    canActivate: [isLoggedInGuard, isTeacherGuard || isStudentGuard]
  },
  {
    path: 'averages',
    component: AveragesComponent,
    canActivate: [isLoggedInGuard, isStudentGuard]
  },
  {
    path: 'exam-registration',
    component: ExamRegistrationComponent,
    canActivate: [isLoggedInGuard, isStudentGuard]
  },
  {
    path: 'grading',
    component: GradingComponent,
    canActivate: [isLoggedInGuard, isTeacherGuard]
  },
  {
    path: 'requests',
    component: RequestsComponent,
    canActivate: [isLoggedInGuard, isStudentGuard || isManagerGuard]
  },
  {
    path: 'invite',
    component: InviteComponent,
    canActivate: [isLoggedInGuard, isManagerGuard]
  },
  {
    path: 'monetary',
    component: MonetaryComponent,
    canActivate: [isLoggedInGuard, isStudentGuard || isManagerGuard]
  },];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


