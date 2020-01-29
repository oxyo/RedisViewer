package org.xenotek.redisviewer.ui;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private String message;
    private JLabel statusLabel;

    public StatusBar() {
        message = "Offline";

        statusLabel = new JLabel(message);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createEtchedBorder());

        add(statusLabel);
    }
}
