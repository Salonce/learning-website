import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ArticleService } from '../../../../core/article-service/article-service';
import { NewArticle } from '../../../../core/models/new-article';
import { QuillModule } from 'ngx-quill';
import { QuillEditor } from '../../components/quill-editor/quill-editor';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-article-new-page',
  imports: [QuillModule, FormsModule, QuillEditor, CommonModule],
  templateUrl: './article-new-page.html',
  styleUrl: './article-new-page.css'
})
export class ArticleNewPage {

  constructor(
    private articleService: ArticleService,
    private router: Router
  ) {}

  article: NewArticle = {
    title: '',
    content: ''
  };

  showConfirmation = signal<boolean>(false);

  onSubmit() {
    // Show confirmation dialog instead of directly submitting
    this.showConfirmation.set(true);
  }

  cancelPublish() {
    this.showConfirmation.set(false);
  }

  confirmPublish() {
    console.log('Article object:', this.article);
    console.log('Content value:', this.article.content);
    
    this.articleService.postArticle(this.article).subscribe({
      next: (res) => {
        console.log('New Article:', res);
        this.showConfirmation.set(false);
        // Optionally navigate to the article list or show success message
        this.router.navigate(['/dashboard/articles']);
      },
      error: (err) => {
        console.error('Failed to publish article:', err);
        this.showConfirmation.set(false);
      }
    });
  }

}