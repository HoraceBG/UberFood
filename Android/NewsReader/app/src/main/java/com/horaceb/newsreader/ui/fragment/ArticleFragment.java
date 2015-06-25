package com.horaceb.newsreader.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.horaceb.newsreader.R;
import com.horaceb.newsreader.data.model.RSS;
import com.horaceb.newsreader.data.task.ArticleTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.horaceb.newsreader.data.HTMLConstants.HTML_MIME_TYPE;
import static com.horaceb.newsreader.data.HTMLConstants.IMG_TAG;
import static com.horaceb.newsreader.data.HTMLConstants.UTF_8;


/**
 * Displays the content of a news article.
 */
public class ArticleFragment extends BaseFragment implements ArticleTask.ArticleResultListener {

    @InjectView(R.id.article_view)
    WebView articleText;

    public ArticleFragment() {
        // Default empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.inject(this, view);
        WebSettings settings = articleText.getSettings();
        settings.setDefaultTextEncodingName(UTF_8);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setTextZoom(300);

        settings.setJavaScriptEnabled(true);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArticleTask task = new ArticleTask();
        task.listener = this;
        task.execute();
    }

    public static Fragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    protected void onSaveState(Bundle outState) {

    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {

    }

    @Override
    public void handleSuccess(RSS articles) {
        String content = articles.getChannel().getArticleList().get(4).getContent();
        Document document = Jsoup.parse(content, "", Parser.xmlParser());
        Element heroImage = document.select(IMG_TAG).first();
        document.select(IMG_TAG).first().remove();
        String html = document.toString();

        articleText.loadDataWithBaseURL(null, html, HTML_MIME_TYPE, UTF_8, null);
    }

    @Override
    public void handleFailure(String errorMessage) {

    }
}
