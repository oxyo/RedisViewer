package org.xenotek.redisviewer.controller;

import org.xenotek.redisviewer.ui.MenuBar;
import org.xenotek.redisviewer.ui.event.*;
import org.xenotek.redisviewer.ui.viewer.*;
import org.xenotek.redisviewer.ui.ServerBrowser;
import org.xenotek.redisviewer.ui.StatusBar;
import org.xenotek.redisviewer.ui.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class RedisViewer extends JFrame {
    private MenuBar menuBar;
    private ToolBar toolBar;
    private ServerBrowser serverBrowser;
    private DetailPane detailPane;
    private StatusBar statusBar;
    private Server server;

    public RedisViewer(String title, int width, int height) {
        BrowserHandler searchHandler = new BrowserHandler();
        MenubarHandler menubarHandler = new MenubarHandler();
        ToolbarHandler toolbarHandler = new ToolbarHandler();

        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new MenuBar();
        toolBar = new ToolBar();
        serverBrowser = new ServerBrowser();
        detailPane = new DetailPane();
        statusBar = new StatusBar();
        server = new Server();

        serverBrowser.setBrowserListener(searchHandler);
        menuBar.setMenubarListener(menubarHandler);
        toolBar.setToolbarListener(toolbarHandler);

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.PAGE_START);
        add(serverBrowser, BorderLayout.LINE_START);
        add(detailPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.PAGE_END);

        setVisible(true);
    }

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings","on");
        new RedisViewer("RedisViewer", 700, 500);
    }

    private class BrowserHandler implements BrowserListener {
        @Override
        public void searchPerformed(String query) {
            Set<String> keys = server.getKeys(query);
            serverBrowser.refreshList(keys);
        }

        @Override
        public void selectionChanged(String selectedKey) {
            String type = server.getType(selectedKey);
            Long ttl = server.ttl(selectedKey);

            switch (type) {
                case RedisView.VIEW_HASH:
                    detailPane.showDetails(new HashView(selectedKey, ttl, server.hgetAll(selectedKey)));
                    break;
                case RedisView.VIEW_LIST:
                    detailPane.showDetails(new ListView(selectedKey, ttl, server.lrange(selectedKey, 0, -1)));
                    break;
                case RedisView.VIEW_SET:
                    detailPane.showDetails(new SetView(selectedKey, ttl, server.smembers(selectedKey)));
                    break;
                case RedisView.VIEW_STRING:
                    detailPane.showDetails(new StringView(selectedKey, ttl, server.get(selectedKey)));
                    break;
                case RedisView.VIEW_NONE:
                    // FIXME: key does not exist - show alert dialog
                    break;
                default:
                    System.err.println(
                        String.format("unhandled type '%s' for selected key '%s'\n", type, selectedKey));
                    break;
            }
        }
    }

    private class MenubarHandler implements MenubarListener {
        @Override
        public void menubarAction(MenubarEvent e) {
            switch (e) {
                case CONFIG_CLICKED:
                    System.out.println("TODO: display server config");
                    break;
            }
        }
    }

    private class ToolbarHandler implements ToolbarListener {
        @Override
        public void toolbarAction(ToolbarEvent e) {
            switch (e) {
                case CONNECT_CLICKED:
                    if (server.isConnected())
                        server.close();
                    else
                        server.connect();
                    break;
            }
        }
    }
}
