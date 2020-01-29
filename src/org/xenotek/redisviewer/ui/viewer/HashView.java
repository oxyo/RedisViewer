package org.xenotek.redisviewer.ui.viewer;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.util.Map;

public class HashView extends RedisView {
    private Map<String,String> data;

    public HashView(String key, Long ttl, Map<String,String> data) {
        super(key, ttl);
        this.data = data;
    }

    public void render(StyledDocument doc) {
        try {
            SimpleAttributeSet textAttr = HeadingAttributeSet();
            doc.insertString(doc.getLength(), getKey() + "\n\n", textAttr);

            textAttr = NormalAttributeSet();
            doc.insertString(doc.getLength(), "Type: HASH\n\n", textAttr);

            StringBuilder row = new StringBuilder(128);
            for (String k : data.keySet()) {
                row.setLength(0);
                row.append(".................... ");
                row.replace(0, k.length()+1, k+" ");
                row.append(data.get(k) + "\n");
                doc.insertString(doc.getLength(), row.toString(), textAttr);
            }

            doc.insertString(doc.getLength(), "\nTime to live: " + getTtl(), textAttr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
