package org.xenotek.redisviewer.ui.viewer;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class StringView extends RedisView {
    private String data;

    public StringView(String key, Long ttl, String data) {
        super(key, ttl);
        this.data = data;
    }

    @Override
    public void render(StyledDocument doc) {
        try {
            SimpleAttributeSet textAttr = HeadingAttributeSet();
            doc.insertString(doc.getLength(), getKey() + "\n\n", textAttr);

            textAttr = NormalAttributeSet();
            doc.insertString(doc.getLength(), "Type: STRING\n\n", textAttr);
            doc.insertString(doc.getLength(), String.format("Value:\n\n  %s\n\n", getData()), textAttr);
            doc.insertString(doc.getLength(), "Time to live: " + getTtl(), textAttr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
