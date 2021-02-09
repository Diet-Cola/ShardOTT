package com.github.dietcola.shardott.zeus;

import com.github.maxopoly.zeus.plugin.ZeusPlugin;

public final class ShardOTTZeus extends ZeusPlugin {

    private static ShardOTTZeus plugin;
    private RequestManager requestManager;

    public static ShardOTTZeus getPlugin() {
        return plugin;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

    @Override
    public boolean onEnable() {
        plugin = this;
        requestManager = new RequestManager();
        return true;
    }

    @Override
    public void onDisable() {

    }
}
