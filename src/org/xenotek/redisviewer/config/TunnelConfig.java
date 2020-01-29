package org.xenotek.redisviewer.config;

public class TunnelConfig {
    private String host;
    private String forwardHost;
    private int port;
    private int forwardPort;
    private String username;
    private String password;
    private String publicKey;
    private AuthMethod authMethod;
    private boolean isConnected;

    public static enum AuthMethod {
        PASSWORD,
        PUBLIC_KEY
    };

    public TunnelConfig() {
        this.host = "localhost";
        this.port = 22;
        this.username = null;
        this.password = null;
        this.authMethod = AuthMethod.PASSWORD;
    }

    public boolean connect() {
        return false;
    }

    public boolean close() {
        return false;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public AuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(AuthMethod authMethod) {
        this.authMethod = authMethod;
    }
}
