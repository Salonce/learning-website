import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticleService } from '../../../core/article-service/article-service';
import { Article } from '../../../core/models/article';
import { Page } from '../../../core/models/page';
import { AsyncPipe } from '@angular/common';
import { Pagination } from '../../../shared/pagination/pagination';
import { ArticleAdminList } from '../article-admin-list/article-admin-list';

@Component({
  selector: 'app-article-management-page',
  imports: [AsyncPipe, Pagination, ArticleAdminList],
  templateUrl: './article-management-page.html',
  styleUrl: './article-management-page.css'
})
export class ArticleManagementPage {
  $articlesPage: Observable<Page<Article>>;

  constructor(private articleService : ArticleService){
    this.$articlesPage = articleService.getArticles();
  }

  loadPage(pageNumber: number) {
    this.$articlesPage = this.articleService.getArticles(pageNumber);
  }
}
