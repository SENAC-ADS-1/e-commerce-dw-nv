import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutoAtualizarComponent } from './produto-atualizar.component';

describe('ProdutoAtualizarComponent', () => {
  let component: ProdutoAtualizarComponent;
  let fixture: ComponentFixture<ProdutoAtualizarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdutoAtualizarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdutoAtualizarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
