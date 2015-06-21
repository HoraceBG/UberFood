package com.horaceb.newsreader.data.loader;

import android.content.Context;

import java.util.List;

import com.horaceb.newsreader.data.model.Article;
import com.horaceb.newsreader.data.requests.ArticleService;
import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by HoraceBG on 13/06/2015.
 */
public class ArticleLoader extends AbstractLoader<Article> {

    public ArticleLoader(Context context) {
        super(context);
    }

    @Override
    public List<Article> loadInBackground() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ArticleService.API_URL)
                .setConverter(new SimpleXMLConverter())
                .build();

        ArticleService service = adapter.create(ArticleService.class);
        return service.getBlogArticles();
    }
}
