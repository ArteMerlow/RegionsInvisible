package ru.kopeyka.rginvisible.LoggerUtils;

import ru.kopeyka.rginvisible.Rginvisible;

public class GetLogger {
    public static void logInfo(String text) {
        Rginvisible.getInstance().getLogger().info(text);
    }

    public static void logWarn(String text) {
        Rginvisible.getInstance().getLogger().warning(text);
    }
}
