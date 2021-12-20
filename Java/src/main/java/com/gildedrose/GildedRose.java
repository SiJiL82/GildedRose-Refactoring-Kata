package com.gildedrose;
import java.util.Set;

class GildedRose {
    Item[] items;

    // Define special items
    Set<String> quality_increases_with_age = Set.of("Aged Brie");
    Set<String> backstage_passes = Set.of("Backstage passes to a TAFKAL80ETC concert");
    Set<String> legendary_items = Set.of("Sulfuras, Hand of Ragnaros");
    Set<String> conjured_items = Set.of("Conjured Biscuits");

    // Define thresholds
    int max_quality = 50;
    int min_quality = 0;
    int min_sell_in = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void increase_quality(Item item, int amount){
        item.quality = Math.min(item.quality + amount, max_quality);
    }

    public void decrease_quality(Item item, int amount){
        item.quality = Math.max(item.quality - amount, min_quality);
    }

    public void decrease_sell_in(Item item, int amount){
        item.sellIn = Math.max(item.sellIn - amount, min_sell_in);
    }

    public void decrease_quality_if_expired(Item item, int degrade_amount){
        if(item.sellIn == 0){
            decrease_quality(item, degrade_amount);
        }
    }

    public void update_quality_increasing_with_age(Item item){
        decrease_sell_in(item, 1);
        if (item.sellIn == 0){
            decrease_quality(item, 1);
        } else {
            increase_quality(item, 1);
        }
    }

    public void update_quality_backstage_passes(Item item){
        decrease_sell_in(item, 1);
        if(item.sellIn == 0){
            item.quality = 0;
        } else {
            increase_quality(item, 1);
            if(item.sellIn <= 10){
                increase_quality(item, 1);
            }
            if(item.sellIn <= 5){
                increase_quality(item, 1);
            }
        }
    }

    public void update_quality_conjured_items(Item item){
        decrease_sell_in(item, 1);
        decrease_quality(item, 2);
        decrease_quality_if_expired(item, 2);
    }

    public void update_quality_normal_item(Item item){
        decrease_sell_in(item, 1);
        decrease_quality(item, 1);
        decrease_quality_if_expired(item, 1);
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if(quality_increases_with_age.contains(item.name)){
                update_quality_increasing_with_age(item);
                continue;
            }
            if(backstage_passes.contains(item.name)){
                update_quality_backstage_passes(item);
                continue;
            }
            if(legendary_items.contains(item.name)){
                continue;
            }
            if(conjured_items.contains(item.name)) {
                update_quality_conjured_items(item);
                continue;
            }
            update_quality_normal_item(item);
        }
    }
}