package org.xenotek.redisviewer.ui.viewer;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.util.List;

public class ListView extends RedisView {
    private List<String> data;

    public ListView(String key, Long ttl, List<String> data) {
        super(key, ttl);
        this.data = data;
    }

    public void render(StyledDocument doc) {
        try {
            SimpleAttributeSet textAttr = HeadingAttributeSet();
            doc.insertString(doc.getLength(), getKey() + "\n\n", textAttr);

            textAttr = NormalAttributeSet();
            doc.insertString(doc.getLength(), "Type: LIST\n\n", textAttr);
            doc.insertString(doc.getLength(), "Values:\n\n", textAttr);

            for (String s : data) {
                doc.insertString(doc.getLength(), "  " + s + "\n", textAttr);
            }

            doc.insertString(doc.getLength(), "\nTime to live: " + getTtl(), textAttr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
