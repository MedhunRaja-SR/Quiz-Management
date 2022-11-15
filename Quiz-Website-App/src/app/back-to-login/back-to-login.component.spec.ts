import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackToLoginComponent } from './back-to-login.component';

describe('BackToLoginComponent', () => {
  let component: BackToLoginComponent;
  let fixture: ComponentFixture<BackToLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BackToLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BackToLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
