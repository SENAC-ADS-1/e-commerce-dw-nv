import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteAtualizarSenhaComponent } from './cliente-atualizar-senha.component';

describe('ClienteAtualizarSenhaComponent', () => {
  let component: ClienteAtualizarSenhaComponent;
  let fixture: ComponentFixture<ClienteAtualizarSenhaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClienteAtualizarSenhaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClienteAtualizarSenhaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
