package ru.kopeyka.rginvisible.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.kopeyka.rginvisible.Rginvisible;
import ru.kopeyka.rginvisible.Utils.GetRegions;

import java.util.Arrays;
import java.util.List;

public class RginvisibleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int argumentsLen = args.length;

        if (argumentsLen <= 0) {
            if (!(sender.hasPermission("rginvisible.command") || sender.hasPermission("rginvisible.*"))) {
                sender.sendMessage("§c[RGInvisible] Недостаточно прав!");
                return true;
            }
            sender.sendMessage(String.format("§6[RGInvisible] §7Список команд плагина:\n§6/%s reload §7- перезагрузка конфига\n§6/%s regions §7- просмотр регионов", label, label));
            return true;
        }
        switch (args[0]) {
            case "reload":
                if (!(sender.hasPermission("rginvisible.reload") || sender.hasPermission("rginvisible.*"))) {
                    sender.sendMessage("§c[RGInvisible] Недостаточно прав!");
                    break;
                }
                Rginvisible.getInstance().reloadConfig();
                Rginvisible.duration = Rginvisible.getInstance().getConfig().getInt("effectDuration");
                Rginvisible.particles = Rginvisible.getInstance().getConfig().getBoolean("allowParticles");
                Rginvisible.joinMessage = Rginvisible.getInstance().getConfig().getString("joinMessage");
                Rginvisible.leaveMessage = Rginvisible.getInstance().getConfig().getString("leaveMessage");
                sender.sendMessage("§a[RGInvisible] Config success reloaded!");
                break;
            case "regions":
                if (!(sender.hasPermission("rginvisible.regions") || sender.hasPermission("rginvisible.*"))) {
                    sender.sendMessage("§c[RGInvisible] Недостаточно прав!");
                    break;
                }
                String regions_string = "";
                List<?> regions = GetRegions.getRegions();
                for (int i = 0; i < regions.size(); i++) {
                    if (i == 0) {
                        regions_string = regions_string + regions.get(i);
                    } else {
                        regions_string = regions_string + ", " + regions.get(i);
                    }
                }
                sender.sendMessage(String.format("§6[RGInvisible] §7Список регионов:\n§7%s", regions_string));
                break;
            default:
                sender.sendMessage("§c[RGInvisible] Такой команды не существует!");
                break;
        }
        return true;
    }
}
