import { Component, Input } from '@angular/core';
import { Diretorio } from '../../models/diretorio.models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-diretorio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './diretorio.component.html',
})
export class DiretorioComponent {
  @Input() diretorio!: Diretorio;
  isExpanded = false;

  toggleExpand(): void {
    this.isExpanded = !this.isExpanded;
  }

}
