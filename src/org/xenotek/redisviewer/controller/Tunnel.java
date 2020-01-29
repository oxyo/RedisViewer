package org.xenotek.redisviewer.controller;

import org.xenotek.redisviewer.config.TunnelConfig;

public class Tunnel {
    private TunnelConfig tunnelConfig;

    public Tunnel(TunnelConfig tunnelConfig) {
        this.tunnelConfig = tunnelConfig;
    }

    public boolean connect() {
        return false;
    }

    public boolean close() {
        return false;
    }
}
