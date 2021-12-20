package com.gildedrose;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20, ItemType.none, 50, 0), //
                new Item("Aged Brie", 2, 0, ItemType.none, 50, 0), //
                new Item("Elixir of the Mongoose", 5, 7, ItemType.none, 50, 0), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80, ItemType.none, 50, 0), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80, ItemType.none, 50, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20, ItemType.none, 50, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49, ItemType.none, 50, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49, ItemType.none, 50, 0),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6, ItemType.none, 50, 0) };

        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.UpdateItems();
        }
    }

}
