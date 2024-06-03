import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaControleComponent } from './lista-controle.component';

describe('ListaControleComponent', () => {
  let component: ListaControleComponent;
  let fixture: ComponentFixture<ListaControleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaControleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListaControleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
