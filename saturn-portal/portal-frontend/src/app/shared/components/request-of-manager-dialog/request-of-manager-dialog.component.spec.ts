import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestOfManagerDialogComponent } from './request-of-manager-dialog.component';

describe('RequestOfManagerDialogComponent', () => {
  let component: RequestOfManagerDialogComponent;
  let fixture: ComponentFixture<RequestOfManagerDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequestOfManagerDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RequestOfManagerDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
