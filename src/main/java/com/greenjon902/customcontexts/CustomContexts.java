package com.greenjon902.customcontexts;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomContexts extends JavaPlugin {
    Logger logger = getLogger();
    CustomContextCalculator customContextCalculator;

    LuckPerms luckPermsApi;

    @Override
    public void onEnable() {
        logger.info("Starting CustomContexts...");

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPermsApi = provider.getProvider();
            logger.info("Found API");
        } else {
            logger.warning("CustomContexts could not fine LuckPerms");
            return;
        }

        customContextCalculator = new CustomContextCalculator();
        luckPermsApi.getContextManager().registerCalculator(customContextCalculator);
    }

    @Override
    public void onDisable() {
        luckPermsApi.getContextManager().unregisterCalculator(customContextCalculator);
    }
}