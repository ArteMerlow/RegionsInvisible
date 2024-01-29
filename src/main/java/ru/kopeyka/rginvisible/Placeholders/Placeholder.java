package ru.kopeyka.rginvisible.Placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ru.kopeyka.rginvisible.PluginUtils.CheckPlayerRegion;
import ru.kopeyka.rginvisible.Rginvisible;

public class Placeholder extends PlaceholderExpansion {
    private Rginvisible plugin;

    public Placeholder(Rginvisible plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "rgi";
    }

    @Override
    public String getAuthor() {
        return "KoPeYkA_";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("region")) {
            return CheckPlayerRegion.isPlayerInRegion((Player) player);
        }

        return null;
    }
}
