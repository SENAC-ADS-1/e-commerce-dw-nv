import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteAtualizarComponent } from './cliente-atualizar.component';

describe('ClienteAtualizarComponent', () => {
  let component: ClienteAtualizarComponent;
  let fixture: ComponentFixture<ClienteAtualizarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClienteAtualizarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClienteAtualizarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
