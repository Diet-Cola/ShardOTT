package com.github.dietcola.shardott.commands;

import com.github.dietcola.shardott.ShardOTT;
import com.github.maxopoly.artemis.ArtemisPlugin;
import com.github.maxopoly.artemis.NameAPI;
import com.github.maxopoly.artemis.rabbit.outgoing.RequestPlayerLocation;
import com.github.maxopoly.artemis.rabbit.session.ALocationRequestSession;
import com.github.maxopoly.zeus.model.TransactionIdManager;
import com.github.maxopoly.zeus.model.ZeusLocation;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vg.civcraft.mc.civmodcore.command.CivCommand;
import vg.civcraft.mc.civmodcore.command.StandaloneCommand;


//tpyes awoo
@CivCommand(id = "tpyes")
public class TeleportAccept extends StandaloneCommand {

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
        setupLocationRequestForPlayer(targetUUID, l -> {
            teleportToLocation(((Player) sender).getUniqueId(), l);
            ShardOTT.getInstance().setAllowOTT(targetUUID, false);
        });
        player.sendMessage(ChatColor.GREEN + "Successfully teleported you to " + targetPlayer);
        //TODO: Remove Request using ShardOTTZeus
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }

    private void setupLocationRequestForPlayer(UUID player, Consumer<ZeusLocation> callback) {
        TransactionIdManager transIdMan = ArtemisPlugin.getInstance().getTransactionIdManager();
        String ticket = transIdMan.pullNewTicket();
        ALocationRequestSession session = new ALocationRequestSession(ArtemisPlugin.getInstance().getZeus(), ticket,
                player, callback);
        transIdMan.putSession(session);
        ArtemisPlugin.getInstance().getRabbitHandler().sendMessage(new RequestPlayerLocation(ticket, player));
    }

    private void teleportToLocation(UUID who, ZeusLocation loc) {
        ArtemisPlugin.getInstance().getTransitManager().sendTo(who, loc);
    }
}
