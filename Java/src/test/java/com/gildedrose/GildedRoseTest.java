package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0, ItemType.none, 50, 0) };

        assertEquals("foo", items[0].getName());
    }

    @Test
    void test_decrease_quality_decrements(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10, ItemType.none, 50, 0) };
        // Act
        items[0].decrease_quality(1);
        // Assert
        assertEquals(9, items[0].getQuality());
    }

    @Test
    void test_decrease_quality_decreases_by_parameter(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10, ItemType.none, 50, 0) };
        // Act
        items[0].decrease_quality(5);
        // Assert
        assertEquals(5, items[0].getQuality());
    }

    @Test
    void test_decrease_sell_in_decrements(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10, ItemType.none, 50, 0) };
        // Act
        items[0].decrease_sell_in(1);
        // Assert
        assertEquals(9, items[0].getSellIn());
    }

    @Test
    void test_decrease_sell_in_decreases_by_parameter(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10, ItemType.none, 50, 0) };
        // Act
        items[0].decrease_sell_in(5);
        // Assert
        assertEquals(5, items[0].getSellIn());
    }

    @Test
    void test_decrease_quality_if_expired_decreases_by_parameter(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 0, 10, ItemType.none, 50, 0) };
        // Act
        items[0].decrease_quality_if_expired(2);
        // Assert
        assertEquals(8, items[0].getQuality());
    }

    // Normal Items
    @Test
    void test_normal_item_loses_quality(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10, ItemType.none, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(9, app.items.get(0).getQuality());
    }

    @Test
    void test_normal_item_expires_and_loses_double_quantity() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 1, 10, ItemType.none, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(8, app.items.get(0).getQuality());
    }

    @Test
    void test_normal_item_doesnt_go_under_0_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 0, ItemType.none, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(0, app.items.get(0).getQuality());
    }

    @Test
    void test_normal_item_sell_in_decreases() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10, ItemType.none, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(9, app.items.get(0).getSellIn());
    }

    @Test
    void test_normal_item_sell_in_goes_under_0() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 0, 10, ItemType.none, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(-1, app.items.get(0).getSellIn());
    }

    // Aging Increases Quality
    @Test
    void test_aging_item_loses_sell_time() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10, ItemType.aging, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(9, app.items.get(0).getSellIn());
    }

    @Test
    void test_aging_item_loses_quality_when_sell_time_expired() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 0, 10, ItemType.aging, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(9, app.items.get(0).getQuality());
    }

    @Test
    void test_aging_item_increases_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 10, 2, ItemType.aging, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(3, app.items.get(0).getQuality());
    }

    @Test
    void test_aging_item_quality_doesnt_go_over_50() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50, ItemType.aging, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(50, app.items.get(0).getQuality());
    }

    // Legendary items
    @Test
    void test_legendary_item_doesnt_lose_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80, ItemType.legendary, 80, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(80, app.items.get(0).getQuality());
    }

    @Test
    void test_legendary_item_doesnt_lose_sell_in() {
        // Arrange
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80, ItemType.legendary, 80, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(10, app.items.get(0).getSellIn());
    }

    // Conjured items
    @Test
    void test_conjured_item_loses_sell_time() {
        // Arrange
        Item[] items = new Item[] { new Item("Conjured Biscuits", 10, 10, ItemType.conjured, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(9, app.items.get(0).getSellIn());
    }

    @Test
    void test_conjured_item_loses_double_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Conjured Biscuits", 10, 30, ItemType.conjured, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(28, app.items.get(0).getQuality());
    }

    @Test
    void test_conjured_item_loses_quadruple_quality_when_expired() {
        // Arrange
        Item[] items = new Item[] { new Item("Conjured Biscuits", 0, 30, ItemType.conjured, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(26, app.items.get(0).getQuality());
    }

    // Backstage Passes
    @Test
    void test_backstage_item_not_near_expiry_increases_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 30, 5, ItemType.backstagepass, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(6, app.items.get(0).getQuality());
    }

    @Test
    void test_backstage_item_expiring_in_10_days_increases_quality_by_2() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5, ItemType.backstagepass, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(7, app.items.get(0).getQuality());
    }

    @Test
    void test_backstage_item_expiring_in_5_days_increases_quality_by_3() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5, ItemType.backstagepass, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(8, app.items.get(0).getQuality());
    }

    @Test
    void test_backstage_item_quality_0_when_expired() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5, ItemType.backstagepass, 50, 0) };
        GildedRose app = new GildedRose();
        for(Item item : items){
            app.AddItem(item);
        }
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(0, app.items.get(0).getQuality());
    }

    // Items
    @Test
    void test_get_item_name(){
        // Arrange
        Item item = new Item("New Item", 10, 10, ItemType.none, 50, 0);
        // Act
        
        // Assert
        assertEquals("New Item", item.getName());
    }

    @Test
    void test_get_item_quality(){
        // Arrange
        Item item = new Item("New Item", 10, 10, ItemType.none, 50, 0);
        // Act
        
        // Assert
        assertEquals(10, item.getQuality());
    }

    @Test
    void test_set_item_quality(){
        // Arrange
        Item item = new Item("New Item", 10, 10, ItemType.none, 50, 0);
        // Act
        item.setQuality(5);
        // Assert
        assertEquals(5, item.getQuality());
    }

    @Test
    void test_get_item_sell_in(){
        // Arrange
        Item item = new Item("New Item", 10, 10, ItemType.none, 50, 0);
        // Act
        
        // Assert
        assertEquals(10, item.getSellIn());
    }

    @Test
    void test_set_item_sell_in(){
        // Arrange
        Item item = new Item("New Item", 10, 10, ItemType.none, 50, 0);
        // Act
        item.setSellIn(5);
        // Assert
        assertEquals(5, item.getSellIn());
    }

    // Shop
    @Test
    void test_normal_item_removed_from_shop_when_quality_0() {
        // Arrange
        Item item = new Item("New Item", 0, 0, ItemType.none, 50, 0) ;

        GildedRose app = new GildedRose();
        app.AddItem(item);
        item.setShop(app);
        // Act
        app.UpdateItems();
        // Assert
        assertEquals(0, app.items.size());
    }
}
