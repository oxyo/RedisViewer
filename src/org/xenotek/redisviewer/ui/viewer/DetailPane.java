package org.xenotek.redisviewer.ui.viewer;

import javax.swing.*;
import java.awt.*;

public class DetailPane extends JPanel {
    private JTextPane contentArea;

    public DetailPane() {
        contentArea = new JTextPane();
        contentArea.setEditable(false);
        contentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPane = new JScrollPane(contentArea);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void showDetails(RedisView redisView) {
        contentArea.setText(null);
        redisView.render(contentArea.getStyledDocument());
    }
}