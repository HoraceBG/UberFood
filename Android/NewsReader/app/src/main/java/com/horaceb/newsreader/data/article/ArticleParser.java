package com.horaceb.newsreader.data.article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import static com.horaceb.newsreader.data.HTMLConstants.IMG_TAG;
import static com.horaceb.newsreader.data.HTMLConstants.SRC_TAG;

/**
 * Utility class to etract parts of an article into
 * usable parts for display.
 * <p/>
 * Created by HoraceBG on 18/06/2015.
 */
public class ArticleParser {

    private final String articleContent;
    private final Document document;

    public ArticleParser(final String articleContent) {
        this.articleContent = articleContent;
        this.document = Jsoup.parse(articleContent, "", Parser.xmlParser());
    }

    public String getHeroImageUrl() {
        Element heroImage = document.select(IMG_TAG).first();
        return heroImage.attributes().get(SRC_TAG);
    }

    public String removeHeroImageFromArticle() {
        Document newDoc = document;
        newDoc.select(IMG_TAG).first().remove();
        return newDoc.toString();
    }


}
