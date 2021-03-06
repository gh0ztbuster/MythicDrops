package com.tealcube.minecraft.bukkit.mythicdrops.settings;

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


import com.tealcube.minecraft.bukkit.mythicdrops.api.settings.IdentifyingSettings;

import java.util.ArrayList;
import java.util.List;

public final class MythicIdentifyingSettings implements IdentifyingSettings {

    private String identityTomeName;
    private List<String> identityTomeLore;
    private String unidentifiedItemName;
    private List<String> unidentifiedItemLore;

    public MythicIdentifyingSettings() {
        identityTomeLore = new ArrayList<>();
        unidentifiedItemLore = new ArrayList<>();
    }

    @Override
    public String getIdentityTomeName() {
        return identityTomeName;
    }

    public void setIdentityTomeName(String identityTomeName) {
        this.identityTomeName = identityTomeName;
    }

    @Override
    public List<String> getIdentityTomeLore() {
        return identityTomeLore;
    }

    public void setIdentityTomeLore(List<String> identityTomeLore) {
        this.identityTomeLore = identityTomeLore;
    }

    @Override
    public String getUnidentifiedItemName() {
        return unidentifiedItemName;
    }

    public void setUnidentifiedItemName(String unidentifiedItemName) {
        this.unidentifiedItemName = unidentifiedItemName;
    }

    @Override
    public List<String> getUnidentifiedItemLore() {
        return unidentifiedItemLore;
    }

    public void setUnidentifiedItemLore(List<String> unidentifiedItemLore) {
        this.unidentifiedItemLore = unidentifiedItemLore;
    }

    @Override
    @Deprecated
    public boolean isEnabled() {
        return true;
    }

    @Deprecated
    public void setEnabled(boolean enabled) {
        // do nothing
    }

}
