package com.tealcube.minecraft.bukkit.mythicdrops.api.socketting;

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


public enum EffectTarget {
    SELF("SELF"), OTHER("OTHER"), NONE("NONE"), AREA("AREA"), AURA("AURA");
    private final String name;

    private EffectTarget(String name) {
        this.name = name;
    }

    public static EffectTarget getFromName(String name) {
        for (EffectTarget gt : EffectTarget.values()) {
            if (gt.getName().equalsIgnoreCase(name)) {
                return gt;
            }
        }
        return EffectTarget.NONE;
    }

    public String getName() {
        return name;
    }
}
