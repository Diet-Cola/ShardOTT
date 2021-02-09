package com.github.dietcola.shardott;


import com.github.dietcola.shardott.listeners.ArtemisListener;
import com.github.dietcola.shardott.listeners.PlayerListener;
import java.util.UUID;
import vg.civcraft.mc.civmodcore.ACivMod;
import vg.civcraft.mc.civmodcore.playersettings.PlayerSettingAPI;
import vg.civcraft.mc.civmodcore.playersettings.impl.BooleanSetting;

public final class ShardOTT extends ACivMod {

    private static ShardOTT plugin;
    private BooleanSetting allowOTT;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        registerListeners();
        registerSetting();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static ShardOTT getInstance() {
        return plugin;
    }

    public boolean getAllowOTT(UUID uuid) {
        return allowOTT.getValue(uuid);
    }

    public void setAllowOTT(UUID uuid, boolean value) {
        allowOTT.setValue(uuid, value);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ArtemisListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private void registerSetting() {
        allowOTT = new BooleanSetting(this, true, "use_ott", "use_ott", "Allows a player to use /ott");
        PlayerSettingAPI.registerSetting(allowOTT, null);
    }
}
