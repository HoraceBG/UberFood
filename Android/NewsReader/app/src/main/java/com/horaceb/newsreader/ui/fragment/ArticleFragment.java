package com.horaceb.newsreader.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horaceb.newsreader.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import data.model.RSS;
import data.task.ArticleTask;


/**
 * Displays the content of a news article.
 */
public class ArticleFragment extends BaseFragment implements ArticleTask.ArticleResultListener {

    @InjectView(R.id.article_text)
    TextView articleText;

    public ArticleFragment() {
        // Default empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.inject(this, view);
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
        Element heroImage = document.select("img").first();
        document.select("img").first().remove();
        String s = document.toString();
        articleText.setText(Html.fromHtml(s));
    }

    @Override
    public void handleFailure(String errorMessage) {

    }
}
