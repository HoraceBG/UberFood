package com.horaceb.newsreader.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * Represents a single news item
 * <p/>
 * Created by HoraceBG on 13/06/2015.
 */
@Root(name = "item", strict = false)
public class Article {

    @Element
    private String title;

    @Element
    private String link;

    @Path(value = "commentsUrl")
    @Element(name = "comments")
    private String commentsUrl;

    @Element
    private String pubDate;

    @Element(name = "encoded")
    private String content;

    @Element
    private String creator;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
