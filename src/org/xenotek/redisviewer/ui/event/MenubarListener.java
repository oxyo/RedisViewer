package org.xenotek.redisviewer.ui.event;

import java.util.EventListener;

public interface MenubarListener extends EventListener {
    void menubarAction(MenubarEvent e);
}
