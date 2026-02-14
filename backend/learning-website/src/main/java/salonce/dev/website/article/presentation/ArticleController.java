package salonce.dev.website.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salonce.dev.website.account.infrastructure.security.AccountPrincipal;
import salonce.dev.website.article.application.ArticleService;
import salonce.dev.website.article.presentation.dtos.ArticleCreateRequest;
import salonce.dev.website.article.presentation.dtos.ArticleViewResponse;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PreAuthorize("permitAll()")
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleViewResponse> getArticleResponse(@AuthenticationPrincipal AccountPrincipal principal, @PathVariable Long id){
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/api/articles/slug/{slug}")
    public ResponseEntity<ArticleViewResponse> getArticleResponse(@AuthenticationPrincipal AccountPrincipal principal, @PathVariable String slug){
        return ResponseEntity.ok(articleService.getArticle(slug));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/api/articles")
    public ResponseEntity<Page<ArticleViewResponse>> getAllArticles(Pageable pageable){
        return ResponseEntity.ok(articleService.getAllArticles(pageable));
    }

    @PreAuthorize("hasAuthority('article:create')")
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleViewResponse> saveArticle(@AuthenticationPrincipal AccountPrincipal principal, @RequestBody ArticleCreateRequest articleCreateRequest){
        return ResponseEntity.ok(articleService.saveArticle(principal, articleCreateRequest));
    }

    @PreAuthorize("hasAuthority('article:update')")
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<ArticleViewResponse> PatchArticle(@RequestBody ArticleCreateRequest articleCreateRequest, @PathVariable Long id){
        return ResponseEntity.ok(articleService.patchArticle(articleCreateRequest, id));
    }

    @PreAuthorize("hasAuthority('article:delete')")
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }




}
