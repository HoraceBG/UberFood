package com.horaceb.newsreader.data.requests;

import java.util.List;

import com.horaceb.newsreader.data.model.Article;
import com.horaceb.newsreader.data.model.RSS;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Fetches an article
 * Created by HoraceBG on 13/06/2015.
 */
public interface ArticleService {

    String API_URL = "http://feeds.feedburner.com";

    @GET("/arseblog")
    List<Article> getBlogArticles();

    @GET("/arseblog")
    RSS getArticles();

    @GET("/arseblognews")
    List<Article> getNewsArticles(Callback<List<Article>> callback);


}
