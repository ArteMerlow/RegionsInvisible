package ru.kopeyka.rginvisible.Utils;

import ru.kopeyka.rginvisible.Rginvisible;

import java.util.List;

public class GetRegions {
    public static List<?> getRegions() {
        return Rginvisible.getInstance().getConfig().getList("invisibleRegions");
    }
}
