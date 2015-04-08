package com.tealcube.minecraft.bukkit.mythicdrops.crafting;

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
import com.tealcube.minecraft.bukkit.mythicdrops.api.MythicDrops;
import com.tealcube.minecraft.bukkit.mythicdrops.socketting.SocketGem;
import com.tealcube.minecraft.bukkit.mythicdrops.utils.SocketGemUtil;
import com.tealcube.minecraft.bukkit.mythicdrops.utils.StringUtil;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class CraftingListener implements Listener {

    private MythicDrops plugin;

    public CraftingListener(MythicDrops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemCraftEvent(CraftItemEvent event) {
        String replaceString = plugin.getSockettingSettings().getSocketGemName().replace('&',
                                                                                         '\u00A7')
            .replace("\u00A7\u00A7", "&").replaceAll("%(?s)(.*?)%", "").replaceAll("\\s+", " ").trim();
        String[] splitString = ChatColor.stripColor(replaceString).split(" ");
        for (ItemStack is : event.getInventory().getMatrix()) {
            if (is == null) {
                continue;
            }
            if (is.getType() == Material.AIR) {
                continue;
            }
            if (!is.hasItemMeta()) {
                continue;
            }
            ItemMeta im = is.getItemMeta();
            if (!im.hasDisplayName()) {
                continue;
            }
            String displayName = im.getDisplayName();
            String colorlessName = ChatColor.stripColor(displayName);

            for (String s : splitString) {
                if (colorlessName.contains(s)) {
                    colorlessName = colorlessName.replace(s, "");
                }
            }

            colorlessName = colorlessName.replaceAll("\\s+", " ").trim();

            SocketGem socketGem = SocketGemUtil.getSocketGemFromName(colorlessName);
            if (socketGem == null) {
                continue;
            }
            String otherName = StringUtil.replaceArgs(MythicDropsPlugin
                                                          .getInstance()
                                                          .getSockettingSettings()
                                                          .getSocketGemName(),
                                                      new String[][]{
                                                          {"%socketgem%",
                                                           socketGem
                                                               .getName()}}
            )
                .replace('&', '\u00A7')
                .replace("\u00A7\u00A7", "&");
            if (displayName.equals(otherName)) {
                event.setCancelled(true);
                return;
            }
        }
    }

}
