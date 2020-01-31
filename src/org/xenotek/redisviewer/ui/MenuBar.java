package org.xenotek.redisviewer.ui;

import org.xenotek.redisviewer.ui.event.MenubarEvent;
import org.xenotek.redisviewer.ui.event.MenubarListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuItem exitItem;
    private JMenuItem configItem;
    private MenubarListener menubarListener;

    public MenuBar() {
        super();

        buildFileMenu();
        buildEditMenu();
    }

    private void buildFileMenu() {
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(exitItem);

        add(fileMenu);
    }

    private void buildEditMenu() {
        configItem = new JMenuItem("Server Config");
        configItem.setMnemonic(KeyEvent.VK_C);
        configItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMenubarEvent(MenubarEvent.CONFIG_CLICKED);
            }
        });

        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(configItem);

        add(editMenu);
    }

    public void setMenubarListener(MenubarListener menubarListener) {
        this.menubarListener = menubarListener;
    }

    private void sendMenubarEvent(MenubarEvent e) {
        if (menubarListener != null)
            menubarListener.menubarAction(e);
    }
}
