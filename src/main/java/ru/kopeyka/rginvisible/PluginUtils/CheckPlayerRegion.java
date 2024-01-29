package ru.kopeyka.rginvisible.PluginUtils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.antlr.v4.runtime.misc.NotNull;
import org.bukkit.entity.Player;

public class CheckPlayerRegion {
    public static String isPlayerInRegion(@NotNull Player player){
        com.sk89q.worldedit.entity.Player guardPlayer = BukkitAdapter.adapt(player);
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(guardPlayer.getWorld());
        if (regionManager == null) return "null";
        ApplicableRegionSet resultSet = regionManager.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()).toVector().toBlockPoint());
        for (ProtectedRegion region : resultSet) {
            return region.getId();
        }
        return "null";
    }
}
