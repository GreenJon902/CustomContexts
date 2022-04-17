package com.greenjon902.customcontexts;

import net.luckperms.api.context.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CustomContextsCalculator implements ContextCalculator<Player> {
    @Override
    public void calculate(@NonNull Player target, @NonNull ContextConsumer consumer) {

        for (String key : JavaPlugin.getPlugin(CustomContexts.class).contexts) {
            String value = JavaPlugin.getPlugin(CustomContexts.class).luckPermsApi.getUserManager().getUser(
                    target.getUniqueId()).getCachedData().getMetaData().getMetaValue("context." + key);
            if (value != null) {
                consumer.accept(key, value);
            }
        }

    }

    @Override
    public @NonNull ContextSet estimatePotentialContexts() {
        MutableContextSet set = MutableContextSet.create();
        for (String key : JavaPlugin.getPlugin(CustomContexts.class).contexts) {
            set.add(key, "");
        }
        return set;
    }
}
