package com.github.dietcola.shardott.commands;

import com.github.dietcola.shardott.ShardOTT;
import com.github.maxopoly.artemis.NameAPI;
import java.util.List;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vg.civcraft.mc.civmodcore.command.CivCommand;
import vg.civcraft.mc.civmodcore.command.StandaloneCommand;

@CivCommand(id = "ott")
public class TeleportRequest extends StandaloneCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!ShardOTT.getInstance().getAllowOTT(player.getUniqueId())) {
            return true;
        }

        String targetPlayer = args[0];
        UUID targetUUID = NameAPI.getUUID(targetPlayer);
        if (targetUUID == null) {
            return true;
        }
        //TODO: Add Request using ShardOTTZeus
        player.sendMessage(ChatColor.GREEN + "You sent a teleport request to " + targetPlayer);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
