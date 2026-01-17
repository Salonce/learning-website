import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-course-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './course-form.html',
  styleUrl: './course-form.css'
})
export class CourseForm {
  @Input() isSubmitting = false;
  @Output() courseSubmit = new EventEmitter<string>();
  @Output() cancel = new EventEmitter<void>();

  courseName = signal('');

  onSubmit() {
    const name = this.courseName().trim();
    if (name) {
      this.courseSubmit.emit(name);
      this.courseName.set('');
    }
  }

  onCancel() {
    this.courseName.set('');
    this.cancel.emit();
  }
}
