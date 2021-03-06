package com.tealcube.minecraft.bukkit.mythicdrops.socketting;

/*
 * #%L
 * MythicDrops
 * %%
 * Copyright (C) 2013 - 2015 TealCube
 * %%
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 * #L%
 */


import com.tealcube.minecraft.bukkit.mythicdrops.MythicDropsPlugin;
import com.tealcube.minecraft.bukkit.mythicdrops.api.socketting.EffectTarget;
import com.tealcube.minecraft.bukkit.mythicdrops.api.socketting.SocketEffect;

import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;

public final class SocketParticleEffect implements SocketEffect {

    private static final int MS_PER_TICK = 50;
    private final Effect particleEffect;
    private final int intensity;
    private final int duration;
    private final int radius;
    private final EffectTarget effectTarget;
    private final boolean affectsWielder;
    private final boolean affectsTarget;

    public SocketParticleEffect(Effect particleEffect, int intensity, int duration,
                                int radius, EffectTarget effectTarget, boolean affectsWielder,
                                boolean affectsTarget) {
        this.particleEffect = particleEffect;
        this.intensity = intensity;
        this.duration = duration;
        this.radius = radius;
        this.effectTarget = effectTarget;
        this.affectsWielder = affectsWielder;
        this.affectsTarget = affectsTarget;
    }

    public Effect getParticleEffect() {
        return particleEffect;
    }

    @Override
    public int getIntensity() {
        return intensity;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public EffectTarget getEffectTarget() {
        return effectTarget;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public boolean isAffectsWielder() {
        return affectsWielder;
    }

    @Override
    public boolean isAffectsTarget() {
        return affectsTarget;
    }

    @Override
    public void apply(final LivingEntity target) {
        if (particleEffect == null) {
            return;
        }
        for (int i = 0; i < duration; i++) {
            Bukkit.getScheduler()
                .scheduleSyncDelayedTask(MythicDropsPlugin.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        target.getWorld()
                            .playEffect(target.getEyeLocation(), particleEffect, RandomUtils.nextInt(4));
                    }
                }, i * 10L);
        }
    }

    @Override
    public int hashCode() {
        int result = particleEffect != null ? particleEffect.hashCode() : 0;
        result = 31 * result + intensity;
        result = 31 * result + duration;
        result = 31 * result + radius;
        result = 31 * result + (effectTarget != null ? effectTarget.hashCode() : 0);
        result = 31 * result + (affectsWielder ? 1 : 0);
        result = 31 * result + (affectsTarget ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SocketParticleEffect that = (SocketParticleEffect) o;

        if (affectsTarget != that.affectsTarget) {
            return false;
        }
        if (affectsWielder != that.affectsWielder) {
            return false;
        }
        if (duration != that.duration) {
            return false;
        }
        if (intensity != that.intensity) {
            return false;
        }
        if (radius != that.radius) {
            return false;
        }
        if (effectTarget != that.effectTarget) {
            return false;
        }
        return particleEffect == that.particleEffect;
    }
}
