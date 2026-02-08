import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-quill-editor',
  imports: [QuillModule, FormsModule],
  templateUrl: './quill-editor.html',
  styleUrl: './quill-editor.css'
})
export class QuillEditor {

  @Input() content: string = '';
  @Output() contentChange = new EventEmitter<string>();

  onContentChange(value: string) {
    this.content = value;
    this.contentChange.emit(value);
  }

  editorModules = {
    toolbar: [
      ['bold', 'italic', 'underline', 'strike'],
      [{ 'color': [
        '#000000', '#451a03', '#92400e', '#d97706', '#f59e0b', // Browns & Ambers
        '#111827', '#374151', '#4b5563' // Grays
      ] }, { 'background': [] }], 
      ['blockquote', 'table'], // Added table icon
      [{ 'header': [1, 2, 3, false] }],
      [{ 'list': 'ordered'}, { 'list': 'bullet' }],
      ['link', 'image'],
      ['clean']
    ],
    table: true, // Enables table interaction
  };
}
