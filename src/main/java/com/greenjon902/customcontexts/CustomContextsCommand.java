package com.greenjon902.customcontexts;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomContextsCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> items = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("customcontexts.add")) {
                items.add("add");
            }
            if (sender.hasPermission("customcontexts.remove")) {
                items.add("remove");
            }
        }
        return items;
    }
}
