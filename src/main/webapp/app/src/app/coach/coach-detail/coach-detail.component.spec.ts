import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachDetailComponent } from './coach-detail.component';

describe('CoachDetailComponent', () => {
  let component: CoachDetailComponent;
  let fixture: ComponentFixture<CoachDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoachDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});