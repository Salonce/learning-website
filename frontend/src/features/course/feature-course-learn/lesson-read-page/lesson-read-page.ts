import { Component, OnDestroy, OnInit } from '@angular/core';
import { LessonService } from '../../services/lesson-service/lesson-service';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { Lesson } from '../../models/lesson';
import { ContentBlock, TextBlock } from '../../models/content-block';
import { CommonModule } from '@angular/common';
import { combineLatest } from 'rxjs/internal/observable/combineLatest';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { Subject } from 'rxjs/internal/Subject';

@Component({
  selector: 'app-lesson-read-page',
  imports: [CommonModule],
  templateUrl: './lesson-read-page.html',
  styleUrl: './lesson-read-page.css'
})
export class LessonReadPage implements OnInit, OnDestroy {
  
  courseSlug!: string;
  lessonSlug!: string;
  lesson!: Lesson;
  
  private destroy$ = new Subject<void>();  // ✅ Destroy signal

  constructor(
    private lessonService: LessonService, 
    private route: ActivatedRoute, 
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    combineLatest([
      this.route.parent!.params,
      this.route.params
    ])
    .pipe(takeUntil(this.destroy$))  // ✅ Unsubscribe when destroy$ emits
    .subscribe(([parentParams, currentParams]) => {
      this.courseSlug = parentParams['courseSlug'];
      this.lessonSlug = currentParams['lessonSlug'];

      console.log('Current course slug:', this.courseSlug);
      console.log('Current lesson slug:', this.lessonSlug);
      
      this.loadArticle();
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();      // ✅ Emit destroy signal
    this.destroy$.complete();  // ✅ Complete the subject
  }

  loadArticle() {
    this.lessonService.getLessonBySlugs(this.courseSlug, this.lessonSlug)
      .pipe(takeUntil(this.destroy$))  // ✅ Also cancel the API call
      .subscribe({
        next: (lesson: Lesson) => {
          this.lesson = lesson;
        },
        error: (err) => {
          console.error('Failed to load lesson:', err);
        }
      });
  }

  isTextBlock(block: ContentBlock): block is TextBlock {
    return block.type === 'TEXT';
  }

  getSafeHtml(html: string): SafeHtml {
    return this.sanitizer.bypassSecurityTrustHtml(html);
  }
}