package org.xenotek.redisviewer.ui.viewer;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.util.Set;

public class SetView extends RedisView {
    private Set<String> data;

    public SetView(String key, Long ttl, Set<String> data) {
        super(key, ttl);
        this.data = data;
    }

    public void render(StyledDocument doc) {
        try {
            SimpleAttributeSet textAttr = HeadingAttributeSet();
            doc.insertString(doc.getLength(), getKey() + "\n\n", textAttr);

            textAttr = NormalAttributeSet();
            doc.insertString(doc.getLength(), "Type: SET\n\n", textAttr);
            doc.insertString(doc.getLength(), "Values:\n\n", textAttr);

            for (String s : data) {
                doc.insertString(doc.getLength(), "  " + s + "\n", textAttr);
            }

            doc.insertString(doc.getLength(), "\nTime to live: " + getTtl(), textAttr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getData() {
        return data;
    }

    public void setData(Set<String> data) {
        this.data = data;
    }
}
