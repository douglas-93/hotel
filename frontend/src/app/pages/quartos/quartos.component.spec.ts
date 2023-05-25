import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuartosComponent } from './quartos.component';

describe('QuartosComponent', () => {
  let component: QuartosComponent;
  let fixture: ComponentFixture<QuartosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuartosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuartosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
