package com.horaceb.newsreader.data.article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 * Utility class to etract parts of an article into
 * usable parts for display.
 *
 * Created by HoraceBG on 18/06/2015.
 */
public class ArticleParser {

    private final String articleContent;
    private final Document document;

    public ArticleParser(String articleContent) {
        this.articleContent = articleContent;
        this.document = Jsoup.parse(articleContent, "", Parser.xmlParser());
    }

    public String getHeroImageUrl(final String articleText) {
        Element heroImage = document.select("img").first();
        return heroImage.attributes().get("src");
    }
}
