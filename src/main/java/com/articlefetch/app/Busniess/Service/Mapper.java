package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.*;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import com.articlefetch.app.DataAccess.ModelDomain.CategoryEntity;
import com.articlefetch.app.DataAccess.ModelDomain.StarredCategoriesEntity;
import org.apache.catalina.filters.ExpiresFilter;

import java.io.IOException;
import java.io.InputStream;

public final class Mapper {

    public static AccountEntity from (AccountCreate account) {
        return new AccountEntity().create(account.getId(), account.getFirst_name(), account.getLast_name(),
                account.getUsername(), account.getPassword(), account.getEmail(), account.getPath(),
                account.getStatus());
    }

    public static Account from (AccountEntity account) throws IOException {
         return new Account(account.getUsername(), account.getPassword(), account.getFirst_name(),
                 account.getLast_name(), account.getEmail(), account.getAccount_id(),
                 getImageAsByteArray(account.getPath()), account.getPath(), account.getStatus());
    }

    public static ArticleEntity from (Article article) {
        return new ArticleEntity().create(article.getId(), article.getArticle_name(), article.getAuthors(),
                article.getArticle_site(), article.getFk_account_id(), article.getFk_categories_id());
    }

    public static Article from (ArticleEntity article) throws IOException {
        return new Article(article.getStaredArticles_id(), article.getArticleName(), article.getArticleAuthors(),
                article.getArticleSite(), article.getFK_account_id(), article.getFK_categories_id());
    }

    public static CategoryEntity from (Category category) {
        return new CategoryEntity().create(category.getId(), category.getName(), category.getDescription());
    }

    public static Category from (CategoryEntity category) throws IOException {
        return new Category(category.getId(), category.getCategoryName(), category.getDescription());
    }

    public static StarredCategoriesEntity from (StarredCategories category) {
        return new StarredCategoriesEntity().create(category.getId(), category.getStarred_categories_name(),
                category.getFk_account_id(), category.getFk_categories_id());
    }

    public static StarredCategories from (StarredCategoriesEntity category) throws IOException {
        return new StarredCategories(category.getStarred_categories_id(), category.getCategories_name(),
                category.getFK_account_id(), category.getStarred_categories_id());
    }

    private static byte[] getImageAsByteArray(String path) throws IOException {
        try {
            String fullPath = "/Images" + path;
            InputStream inputStream = Mapper.class
                    .getResourceAsStream(fullPath);
            return inputStream.readAllBytes();
        } catch (Exception e) {
            // If there is not a valid path we will return null
            // TODO: Better Error Handling!
            return null;
        }
    }
}
