package org.xenotek.redisviewer.ui.viewer;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public abstract class RedisView {
    private String key;
    private Long ttl;

    private static final int DEFAULT_HEADING_SIZE = 16;
    private static final int DEFAULT_TEXT_SIZE = 13;

    public static final String VIEW_NONE   = "none";
    public static final String VIEW_STRING = "string";
    public static final String VIEW_LIST   = "list";
    public static final String VIEW_HASH   = "hash";
    public static final String VIEW_SET    = "set";

    public RedisView() {
        this(null, Long.valueOf(-1));
    }

    public RedisView(String key, Long ttl) {
        this.key = key;
        this.ttl = ttl;
    }

    protected static SimpleAttributeSet HeadingAttributeSet() {
        return HeadingAttributeSet(RedisView.DEFAULT_HEADING_SIZE);
    }

    protected static SimpleAttributeSet HeadingAttributeSet(int fontSize) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();

        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setFontFamily(attributeSet, "monospaced");
        StyleConstants.setFontSize(attributeSet, fontSize);

        return attributeSet;
    }

    protected static SimpleAttributeSet NormalAttributeSet() {
        return NormalAttributeSet(RedisView.DEFAULT_TEXT_SIZE);
    }

    protected static SimpleAttributeSet NormalAttributeSet(int fontSize) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();

        StyleConstants.setBold(attributeSet, false);
        StyleConstants.setFontFamily(attributeSet, "monospaced");
        StyleConstants.setFontSize(attributeSet, fontSize);

        return attributeSet;
    }

    public abstract void render(StyledDocument doc);

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
