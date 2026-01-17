import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-error-alert',
  imports: [CommonModule],
  templateUrl: './error-alert.html',
  styleUrl: './error-alert.css'
})
export class ErrorAlert {
  @Input() message = '';
  @Output() dismiss = new EventEmitter<void>();
}
