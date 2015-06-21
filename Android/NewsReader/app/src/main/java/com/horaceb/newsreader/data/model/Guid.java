package com.horaceb.newsreader.data.model;

import org.simpleframework.xml.Element;

public class Guid {

    @Element
    private String content;

    @Element
    private boolean isPermaLink;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsPermaLink() {
        return isPermaLink;
    }

    public void setIsPermaLink(boolean isPermaLink) {
        this.isPermaLink = isPermaLink;
    }
}
