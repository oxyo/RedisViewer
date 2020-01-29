package org.xenotek.redisviewer.ui.event;

import java.util.EventListener;

public interface BrowserListener extends EventListener {
    void searchPerformed(String query);
    void selectionChanged(String selectedKey);
}
