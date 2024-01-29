package ru.kopeyka.rginvisible;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.kopeyka.rginvisible.Commands.RginvisibleCommand;
import ru.kopeyka.rginvisible.Events.PlayerMoveEvent;
import ru.kopeyka.rginvisible.LoggerUtils.GetLogger;
import ru.kopeyka.rginvisible.Placeholders.Placeholder;
import ru.kopeyka.rginvisible.Utils.GetRegions;

import java.util.ArrayList;
import java.util.List;

public final class Rginvisible extends JavaPlugin {
    static Rginvisible instance;
    public static ArrayList<String> invPlayers = new ArrayList<String>();
    public static int duration;
    public static boolean particles;
    public static String joinMessage;
    public static String leaveMessage;
    public static ArrayList<String> invPlayersMessages = new ArrayList<String>();

    @Override
    public void onEnable() {
        instance = this;
        duration = getConfig().getInt("effectDuration");
        particles = getConfig().getBoolean("allowParticles");
        joinMessage = getConfig().getString("joinMessage");
        leaveMessage = getConfig().getString("leaveMessage");
        GetLogger.logInfo("Plugin started!");
        saveDefaultConfig();
        getCommand("rginvisible").setExecutor(new RginvisibleCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerMoveEvent(), this);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder(this).register();
        }
    }

    public static Rginvisible getInstance() {return instance;}
}
