package org.xenotek.redisviewer.controller;

import org.xenotek.redisviewer.config.ServerConfig;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public class Server {
    private ServerConfig serverConfig;
    private Jedis client;
    private boolean isConnected;

    public Server() {
        this(new ServerConfig());
    }

    public Server(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        this.client = null;
        this.isConnected = false;
    }

    public boolean connect() {
        if (client == null)
            isConnected = false;

        if (isConnected())
            return false;

        System.out.println("connecting...");
        client = new Jedis(serverConfig.getHost(), serverConfig.getPort());
        client.connect();
        this.isConnected = true;

        return true;
    }

    public boolean close() {
        if (!isConnected() || client == null)
            return false;

        System.out.println("disconnecting...");
        client.close();
        client = null;
        this.isConnected = false;

        return true;
    }

    public Set<String> getKeys(String pattern) {
        if (pattern == null || pattern.length() == 0)
            pattern = "*";

        return client.keys(pattern);
    }

    public String get(String key) {
        return client.get(key);
    }

    public String getType(String key) {
        return client.type(key);
    }

    public Map<String,String> hgetAll(String key) {
        return client.hgetAll(key);
    }

    public Long ttl(String key) {
        return client.ttl(key);
    }

    public boolean isConnected() {
        return isConnected && client != null;
    }
}
