package com.greenjon902.customcontexts;

import com.sun.tools.jdeprscan.scan.Scan;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public final class CustomContexts extends JavaPlugin {
    Logger logger = getLogger();
    CustomContextsCalculator customContextCalculator;

    public LuckPerms luckPermsApi;

    public ArrayList<String> contexts;

    File contextsFile = new File(getDataFolder(), "contexts.txt").getAbsoluteFile();

    @Override
    public void onEnable() {
        logger.info("Starting CustomContexts...");

        if (!contextsFile.exists()) {
            try {
                contextsFile.mkdirs();
                contextsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Scanner scanner = new Scanner(contextsFile.getPath());
        scanner.useDelimiter(",");

        while (scanner.hasNext()) {
            String value = scanner.next();
            contexts.add(value);
        }

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPermsApi = provider.getProvider();
            logger.info("Found API");
        } else {
            logger.warning("CustomContexts could not fine LuckPerms");
            return;
        }

        customContextCalculator = new CustomContextsCalculator();
        luckPermsApi.getContextManager().registerCalculator(customContextCalculator);

        Objects.requireNonNull(getCommand("customcontexts")).setExecutor(new CustomContextsCommand());
    }

    @Override
    public void onDisable() {
        try {
            contextsFile.mkdirs();

            StringBuilder stringBuilder = new StringBuilder();
            for (String context : contexts) {
                stringBuilder.append(context);
                stringBuilder.append(",");
            }

            FileWriter writer = new FileWriter(contextsFile);
            writer.write(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        luckPermsApi.getContextManager().unregisterCalculator(customContextCalculator);
    }
}
