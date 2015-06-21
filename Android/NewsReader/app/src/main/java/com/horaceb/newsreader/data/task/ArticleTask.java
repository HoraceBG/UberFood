package com.horaceb.newsreader.data.task;

import android.os.AsyncTask;

import com.horaceb.newsreader.data.model.RSS;
import com.horaceb.newsreader.data.requests.ArticleService;
import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by HoraceBG on 14/06/2015.
 */
public class ArticleTask extends AsyncTask<Void, Void, RSS> {

    public ArticleResultListener listener;

    public interface ArticleResultListener {
        void handleSuccess(final RSS articles);

        void handleFailure(final String errorMessage);
    }

    @Override
    protected RSS doInBackground(Void... params) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ArticleService.API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new SimpleXMLConverter())
                .build();

        ArticleService service = adapter.create(ArticleService.class);
        return service.getArticles();
    }

    @Override
    protected void onPostExecute(RSS articles) {
        super.onPostExecute(articles);
        listener.handleSuccess(articles);
    }
}
