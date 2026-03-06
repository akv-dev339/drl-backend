package com.drl.drlwebsite.service;

import com.drl.drlwebsite.entity.Article;
import com.drl.drlwebsite.entity.Volume;
import com.drl.drlwebsite.repository.ArticleRepository;
import com.drl.drlwebsite.repository.VolumeRepository;

import lombok.RequiredArgsConstructor;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final VolumeRepository volumeRepository;
    private final Cloudinary cloudinary;

    // ✅ Create Article
    public Article createArticle(Long volumeId, Article article) {

        Volume volume = volumeRepository.findById(volumeId)
                .orElseThrow(() -> new RuntimeException("Volume not found"));

        article.setVolume(volume);

        return articleRepository.save(article);
    }

    // ✅ Get Articles by Volume
    public List<Article> getArticlesByVolume(Long volumeId) {
        return articleRepository.findByVolumeId(volumeId);
    }

    // ✅ Get Article by ID
    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    // ✅ Update Article
    public Article updateArticle(Long id, Article updatedArticle) {

        Article article = getArticleById(id);

        article.setTitle(updatedArticle.getTitle());
        article.setAuthors(updatedArticle.getAuthors());
        article.setArticleType(updatedArticle.getArticleType());
        article.setPages(updatedArticle.getPages());
        article.setAbstractText(updatedArticle.getAbstractText());
        article.setPdfUrl(updatedArticle.getPdfUrl());

        return articleRepository.save(article);
    }

     // DELETE ARTICLE + CLOUDINARY FILE
    public void deleteArticle(Long id) {

        Article article = getArticleById(id);

        if (article.getPdfUrl() != null) {

            try {

                String pdfUrl = article.getPdfUrl();

                String publicId = pdfUrl
                        .substring(pdfUrl.indexOf("articles/"))
                        .replace(".pdf", "");

                cloudinary.uploader().destroy(
                        publicId,
                        ObjectUtils.asMap("resource_type", "raw")
                );

            } catch (Exception e) {

                System.out.println("Cloudinary delete failed: " + e.getMessage());

            }
        }

        articleRepository.delete(article);
    }

    // ✅ Upload PDF to Cloudinary
    public String uploadPdf(MultipartFile file) throws IOException {

        if (!"application/pdf".equals(file.getContentType())) {
            throw new RuntimeException("Only PDF files allowed");
        }

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "raw",
                        "folder", "articles"
                )
        );

        return uploadResult.get("secure_url").toString();
    }

    // ✅ Save Article
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}

