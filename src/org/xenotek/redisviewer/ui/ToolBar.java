package org.xenotek.redisviewer.ui;

import org.xenotek.redisviewer.ui.event.ToolbarEvent;
import org.xenotek.redisviewer.ui.event.ToolbarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel {
    private JButton connectButton;
    private ToolbarListener toolbarListener;

    public ToolBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createEtchedBorder());

        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (toolbarListener != null)
                    toolbarListener.toolbarAction(ToolbarEvent.CONNECT_CLICKED);
            }
        });

        add(connectButton);
    }

    public void setConnectListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }
}
