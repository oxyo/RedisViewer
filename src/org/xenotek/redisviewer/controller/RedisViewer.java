package org.xenotek.redisviewer.controller;

import org.xenotek.redisviewer.ui.viewer.DetailPane;
import org.xenotek.redisviewer.ui.ServerBrowser;
import org.xenotek.redisviewer.ui.StatusBar;
import org.xenotek.redisviewer.ui.ToolBar;
import org.xenotek.redisviewer.ui.event.ToolbarEvent;
import org.xenotek.redisviewer.ui.event.ToolbarListener;
import org.xenotek.redisviewer.ui.event.BrowserListener;
import org.xenotek.redisviewer.ui.viewer.HashView;
import org.xenotek.redisviewer.ui.viewer.RedisView;
import org.xenotek.redisviewer.ui.viewer.StringView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class RedisViewer extends JFrame {
    private ToolBar toolBar;
    private ServerBrowser serverBrowser;
    private DetailPane detailPane;
    private StatusBar statusBar;
    private Server server;

    public RedisViewer(String title, int width, int height) {
        BrowserHandler searchHandler = new BrowserHandler();
        ToolbarHandler toolbarHandler = new ToolbarHandler();

        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        toolBar = new ToolBar();
        serverBrowser = new ServerBrowser();
        detailPane = new DetailPane();
        statusBar = new StatusBar();
        server = new Server();

        serverBrowser.setBrowserListener(searchHandler);
        toolBar.setConnectListener(toolbarHandler);

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
            Long ttl = server.ttl(selectedKey);

            switch (server.getType(selectedKey)) {
                case RedisView.VIEW_HASH:
                    detailPane.showDetails(new HashView(selectedKey, ttl, server.hgetAll(selectedKey)));
                    break;
                case RedisView.VIEW_STRING:
                    detailPane.showDetails(new StringView(selectedKey, ttl, server.get(selectedKey)));
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
