import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesOfSubjectDialogComponent } from './courses-of-subject-dialog.component';

describe('CoursesOfSubjectDialogComponent', () => {
  let component: CoursesOfSubjectDialogComponent;
  let fixture: ComponentFixture<CoursesOfSubjectDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoursesOfSubjectDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CoursesOfSubjectDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
