package com.github.dietcola.shardott.zeus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RequestManager {

    private final Map<UUID, List<UUID>> requests;

    public RequestManager() {
        this.requests = new HashMap<>();
    }

    public void addRequest(UUID targetPlayer, UUID sender) {
        this.requests.computeIfAbsent(targetPlayer, u -> new ArrayList<>()).add(sender);
    }

    public List<UUID> getRequests(UUID player) {
        return this.requests.getOrDefault(player, Collections.emptyList());
    }

    public void removeRequest(UUID targetPlayer, UUID sender) {
        List<UUID> list = this.requests.get(targetPlayer);
        if (list == null) {
            return;
        }
        list.remove(sender);
    }
}
