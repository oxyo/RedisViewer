package org.xenotek.redisviewer.config;

public class ServerConfig {
    private String host;
    private int port;
    private TunnelConfig tunnelConfig;
    private boolean useTunnel;

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 6379;

    public ServerConfig() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public ServerConfig(String host, int port) {
        this.host = host;
        this.port = port;
        this.tunnelConfig = null;
        this.useTunnel = false;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public TunnelConfig getTunnelConfig() {
        return tunnelConfig;
    }

    public void setTunnelConfig(TunnelConfig tunnelConfig) {
        this.tunnelConfig = tunnelConfig;
    }

    public boolean isUseTunnel() {
        return useTunnel;
    }

    public void setUseTunnel(boolean useTunnel) {
        this.useTunnel = useTunnel;
    }
}
