package ru.kopeyka.rginvisible.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.kopeyka.rginvisible.PluginUtils.CheckPlayerRegion;
import ru.kopeyka.rginvisible.Rginvisible;
import ru.kopeyka.rginvisible.Utils.GetRegions;

import java.util.List;

public class PlayerMoveEvent implements Listener {
    @EventHandler
    public static void onMove(org.bukkit.event.player.PlayerMoveEvent e) {
        List<?> regions = GetRegions.getRegions();
        Player player = e.getPlayer();
        List<?> ignorePlayers = Rginvisible.getInstance().getConfig().getList("ignorePlayers");
        if (ignorePlayers.contains(player.getName())) {
            return;
        }
        if (regions.contains(CheckPlayerRegion.isPlayerInRegion(player))) {
            Rginvisible.invPlayers.add(e.getPlayer().getName());
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Rginvisible.duration, 1, Rginvisible.particles));
            if (!Rginvisible.invPlayersMessages.contains(player.getName())) {
                Rginvisible.invPlayersMessages.add(player.getName());
                if (!(Rginvisible.joinMessage == null || Rginvisible.joinMessage.trim().isEmpty())) {
                    String jMessage = Rginvisible.joinMessage.replace("%p", player.getName());
                    jMessage = jMessage.replace("%r", CheckPlayerRegion.isPlayerInRegion(player));
                    player.sendMessage(jMessage);
                }
            }
        } else {
            try {
                if (Rginvisible.invPlayers.contains(e.getPlayer().getName())) {
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    Rginvisible.invPlayers.remove(player.getName());
                    if (Rginvisible.invPlayersMessages.contains(player.getName())) {
                        Rginvisible.invPlayersMessages.remove(player.getName());
                        if (!(Rginvisible.leaveMessage == null || Rginvisible.leaveMessage.trim().isEmpty())) {
                            String lMessage = Rginvisible.leaveMessage.replace("%p", player.getName());
                            lMessage = lMessage.replace("%r", CheckPlayerRegion.isPlayerInRegion(player));
                            player.sendMessage(lMessage);
                        }
                    }
                }
            } catch (Exception exception) {
                //pass
            }
        }
    }
}
