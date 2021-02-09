package com.github.dietcola.shardott.commands;

import com.github.maxopoly.artemis.NameAPI;
import java.util.List;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vg.civcraft.mc.civmodcore.command.CivCommand;
import vg.civcraft.mc.civmodcore.command.StandaloneCommand;


//tpno diet_cola
@CivCommand(id = "tpno")
public class TeleportDeny extends StandaloneCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        String targetPlayer = args[0];
        UUID targetUUID = NameAPI.getUUID(targetPlayer);
        if (targetUUID == null) {
            player.sendMessage(ChatColor.RED + "The player " + targetPlayer + " does not exist.");
            return true;
        }
        //TODO: Remove Request using ShardOTTZeus
        player.sendMessage(ChatColor.GREEN + "You declined the request of " + targetPlayer + ".");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
