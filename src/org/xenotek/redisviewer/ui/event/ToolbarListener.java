package org.xenotek.redisviewer.ui.event;

import java.util.EventListener;

public interface ToolbarListener extends EventListener {
    void toolbarAction(ToolbarEvent e);
}
