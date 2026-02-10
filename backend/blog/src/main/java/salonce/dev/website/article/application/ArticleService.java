package salonce.dev.website.article.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import salonce.dev.website.account.application.AccountService;
import salonce.dev.website.account.domain.Account;
import salonce.dev.website.account.infrastructure.security.AccountPrincipal;
import salonce.dev.website.article.application.exceptions.ArticleNotFound;
import salonce.dev.website.article.domain.Article;
import salonce.dev.website.article.infrastructure.ArticleRepository;
import salonce.dev.website.article.presentation.ArticleMapper;
import salonce.dev.website.article.presentation.dtos.ArticleCreateRequest;
import salonce.dev.website.article.presentation.dtos.ArticleViewResponse;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final AccountService accountService;
    private final ArticleRepository articleRepository;

    @Transactional
    public Page<ArticleViewResponse> getAllArticles(Pageable pageable){
        return articleRepository.findAll(pageable).map(ArticleMapper::toArticleResponse);
    }

    @Transactional
    public ArticleViewResponse getArticle(String slug){
        Article article = articleRepository.findBySlug(slug).orElseThrow(ArticleNotFound::new);
        return ArticleMapper.toArticleResponse(article);
    }


    @Transactional
    public ArticleViewResponse getArticle(Long id){
        Article article = articleRepository.findById(id).orElseThrow(ArticleNotFound::new);
        return ArticleMapper.toArticleResponse(article);
    }

    @Transactional
    public ArticleViewResponse saveArticle(AccountPrincipal principal, ArticleCreateRequest articleCreateRequest){
        Account account = accountService.findAccount(principal.id());
        Article article = new Article(articleCreateRequest.title(), generateSlug(articleCreateRequest.title()), articleCreateRequest.content(), account);
        return ArticleMapper.toArticleResponse(articleRepository.save(article));
    }

    @Transactional
    public ArticleViewResponse patchArticle(ArticleCreateRequest articleCreateRequest, Long articleId){
        Article article = articleRepository.findById(articleId).orElseThrow(ArticleNotFound::new);

        if (articleCreateRequest.title() != null) article.setTitle(articleCreateRequest.title());
        if (articleCreateRequest.content() != null) article.setContent(articleCreateRequest.content());

        return ArticleMapper.toArticleResponse(articleRepository.save(article));
    }

    @Transactional
    public void deleteArticle(Long articleId){
        Article article = articleRepository.findById(articleId).orElseThrow(ArticleNotFound::new);
        articleRepository.delete(article);
    }

    // util
    private String generateSlug(String title) {
        return title.toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }
}
