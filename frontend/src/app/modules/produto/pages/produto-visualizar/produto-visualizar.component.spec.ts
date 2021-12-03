import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutoVisualizarComponent } from './produto-visualizar.component';

describe('ProdutoVisualizarComponent', () => {
  let component: ProdutoVisualizarComponent;
  let fixture: ComponentFixture<ProdutoVisualizarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdutoVisualizarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdutoVisualizarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
