import { Component } from '@angular/core';
import { Diretorio } from '../../../models/diretorio.models';
import { DiretorioService } from '../../../services/diretorio.service';
import { CommonModule } from '@angular/common';
import { DiretorioComponent } from '../diretorio.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-diretorio-list',
  standalone: true,
  templateUrl: './diretorio-list.component.html',
  imports: [CommonModule, DiretorioComponent, RouterOutlet],
})
export class DiretorioListComponent {

  diretorios: Diretorio[] = [];

  constructor(private diretorioService: DiretorioService) { }

  ngOnInit(): void {
    this.diretorioService.getDiretorios().subscribe((res: Diretorio[]) => {
      this.diretorios = res;
    });
  }
}
