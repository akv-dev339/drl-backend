package com.drl.drlwebsite.controller;

import com.drl.drlwebsite.entity.Article;
import com.drl.drlwebsite.service.ArticleService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@CrossOrigin
public class ArticleController {

    private final ArticleService articleService;

    // ✅ Create Article under Volume
    @PostMapping("/volume/{volumeId}")
    public Article createArticle(@PathVariable Long volumeId,
                                 @RequestBody Article article) {

        return articleService.createArticle(volumeId, article);
    }

    // ✅ Get Articles by Volume
    @GetMapping("/volume/{volumeId}")
    public List<Article> getArticlesByVolume(@PathVariable Long volumeId) {

        return articleService.getArticlesByVolume(volumeId);
    }

    // ✅ Get Article by ID
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {

        return articleService.getArticleById(id);
    }

    // ✅ Update Article
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id,
                                 @RequestBody Article article) {

        return articleService.updateArticle(id, article);
    }
    @PostMapping("/{id}/upload-pdf")
public Article uploadPdf(@PathVariable Long id,
                         @RequestParam("file") MultipartFile file) throws IOException {
                             System.out.println(SecurityContextHolder.getContext().getAuthentication());

    Article article = articleService.getArticleById(id);

    String pdfUrl = articleService.uploadPdf(file);

    article.setPdfUrl(pdfUrl);

   return articleService.saveArticle(article);

}

    // ✅ Delete Article
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {

        articleService.deleteArticle(id);
    }
}
