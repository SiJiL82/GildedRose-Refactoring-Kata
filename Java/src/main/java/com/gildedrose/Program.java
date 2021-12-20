package com.gildedrose;

public class Program {
    public static void main (String[] args) {
        System.out.print("\033[H\033[2J"); 

        GildedRose gildedRose = new GildedRose();

        ItemBuilder itemBuilder = new ItemBuilder()
            .withName("Aged Brie")
            .withSellIn(2)
            .withMinSellIn(0)
            .withQuality(2)
            .withMaxQuality(50)
            .withPrice(4.99)
            .withItemType(ItemType.aging);
        Item newItem = itemBuilder.build();
        gildedRose.AddItem(newItem);
        newItem.setShop(gildedRose);

        // itemBuilder = new ItemBuilder()
        //     .withName("Aged Cheddar")
        //     .withSellIn(30)
        //     .withMinSellIn(0)
        //     .withQuality(1)
        //     .withMaxQuality(50)
        //     .withItemType(ItemType.aging);
        // gildedRose.AddItem(itemBuilder.build());

        // itemBuilder = new ItemBuilder()
        //     .withName("Backstage passes to a TAFKAL80ETC concert")
        //     .withSellIn(3)
        //     .withMinSellIn(-1)
        //     .withQuality(2)
        //     .withMaxQuality(50)
        //     .withPrice(50.00)
        //     .withItemType(ItemType.backstagepass);
        // newItem = itemBuilder.build();
        // gildedRose.AddItem(newItem);
        // newItem.setShop(gildedRose);

        // itemBuilder = new ItemBuilder()
        //     .withName("Sulfuras, Hand of Ragnaros")
        //     .withSellIn(100)
        //     .withMinSellIn(0)
        //     .withPrice(9001)
        //     .withItemType(ItemType.legendary);
        // newItem = itemBuilder.build();
        // gildedRose.AddItem(newItem);
        // newItem.setShop(gildedRose);

        for(int i = 0; i < 10; i++){
            gildedRose.PrintItems();
            gildedRose.UpdateItems();
        }
    }
}
