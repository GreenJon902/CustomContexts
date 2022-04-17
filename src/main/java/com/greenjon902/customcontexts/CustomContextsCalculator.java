package com.greenjon902.customcontexts;

import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CustomContextsCalculator implements ContextCalculator<Player> {
    @Override
    public void calculate(@NonNull Player target, @NonNull ContextConsumer consumer) {
    }

    @Override
    public @NonNull ContextSet estimatePotentialContexts() {
        return ContextCalculator.super.estimatePotentialContexts();
    }
}
