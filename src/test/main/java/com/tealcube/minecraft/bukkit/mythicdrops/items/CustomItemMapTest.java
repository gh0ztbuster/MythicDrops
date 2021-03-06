package com.tealcube.minecraft.bukkit.mythicdrops.items;

/*
 * #%L
 * MythicDrops
 * %%
 * Copyright (C) 2013 - 2015 TealCube
 * %%
 * # % L
 * 
 * %%
 * 
 * %%
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 * # L %
 * #L%
 */


import com.tealcube.minecraft.bukkit.mythicdrops.api.items.CustomItem;

import org.junit.Assert;
import org.junit.Test;

public class CustomItemMapTest {

    @Test
    public void testGetRandom() throws Exception {
        CustomItemMap customItemMap = CustomItemMap.getInstance();

        customItemMap.put("foo", new CustomItemImp("foo").ci());
        customItemMap.put("bar", new CustomItemImp("bar").ci());
        customItemMap.put("foobar", new CustomItemImp("foobar").ci());

        int[] results = new int[3];
        int numOfRuns = 1000;
        for (int i = 0; i < numOfRuns; i++) {
            CustomItem ci = customItemMap.getRandom();
            Assert.assertNotNull(ci);
            if ("foo".equals(ci.getName())) {
                results[0]++;
            } else if ("bar".equals(ci.getName())) {
                results[1]++;
            } else if ("foobar".equals(ci.getName())) {
                results[2]++;
            } else {
                Assert.fail("Unexpected value");
            }
        }

        Assert.assertTrue(results[0] > 300);
        Assert.assertTrue(results[1] > 300);
        Assert.assertTrue(results[2] > 300);
    }

    @Test
    public void testGetRandomWithChance() throws Exception {
        CustomItemMap customItemMap = CustomItemMap.getInstance();

        customItemMap.put("foo", new CustomItemImp("foo", 0.25).ci());
        customItemMap.put("bar", new CustomItemImp("bar", 0.50).ci());
        customItemMap.put("foobar", new CustomItemImp("foobar", 0.25).ci());

        int[] results = new int[3];
        int numOfRuns = 1000;
        for (int i = 0; i < numOfRuns; i++) {
            CustomItem ci = customItemMap.getRandomWithChance();
            Assert.assertNotNull(ci);
            if ("foo".equals(ci.getName())) {
                results[0]++;
            } else if ("bar".equals(ci.getName())) {
                results[1]++;
            } else if ("foobar".equals(ci.getName())) {
                results[2]++;
            } else {
                Assert.fail("Unexpected value");
            }
        }

        Assert.assertTrue(results[0] > 200);
        Assert.assertTrue(results[1] > 400);
        Assert.assertTrue(results[2] > 200);
    }

    class CustomItemImp {

        private CustomItem ci = null;

        protected CustomItemImp(String name) {
            ci = new CustomItemBuilder(name).build();
        }

        protected CustomItemImp(String name, double chance) {
            ci = new CustomItemBuilder(name).withChanceToBeGivenToMonster(chance).build();
        }

        protected CustomItem ci() {
            return ci;
        }

    }
}
