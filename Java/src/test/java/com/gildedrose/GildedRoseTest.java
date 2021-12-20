package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void test_decrease_quality_decrements(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.decrease_quality(app.items[0], 1);
        // Assert
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void test_decrease_quality_decreases_by_parameter(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.decrease_quality(app.items[0], 5);
        // Assert
        assertEquals(5, app.items[0].quality);
    }

    @Test
    void test_decrease_sell_in_decrements(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.decrease_sell_in(app.items[0], 1);
        // Assert
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void test_decrease_sell_in_decreases_by_parameter(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.decrease_sell_in(app.items[0], 5);
        // Assert
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void test_decrease_quality_if_expired_decreases_by_parameter(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 0, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.decrease_quality_if_expired(app.items[0], 2);
        // Assert
        assertEquals(8, app.items[0].quality);
    }

    // Normal Items
    @Test
    void test_normal_item_loses_quality(){
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void test_normal_item_expires_and_loses_double_quantity() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 1, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void test_normal_item_doesnt_go_under_0_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 0) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_normal_item_sell_in_decreases() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void test_normal_item_sell_in_doesnt_go_under_0() {
        // Arrange
        Item[] items = new Item[] { new Item("New Item", 0, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(0, app.items[0].sellIn);
    }

    // Aging Increases Quality
    @Test
    void test_aging_item_loses_sell_time() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void test_aging_item_loses_quality_when_sell_time_expired() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void test_aging_item_increases_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 10, 2) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void test_aging_item_quality_doesnt_go_over_50() {
        // Arrange
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(50, app.items[0].quality);
    }

    // Legendary items
    @Test
    void test_legendary_item_doesnt_lose_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void test_legendary_item_doesnt_lose_sell_in() {
        // Arrange
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(10, app.items[0].sellIn);
    }

    // Conjured items
    @Test
    void test_conjured_item_loses_sell_time() {
        // Arrange
        Item[] items = new Item[] { new Item("Conjured Biscuits", 10, 10) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void test_conjured_item_loses_double_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Conjured Biscuits", 10, 30) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(28, app.items[0].quality);
    }

    @Test
    void test_conjured_item_loses_quadruple_quality_when_expired() {
        // Arrange
        Item[] items = new Item[] { new Item("Conjured Biscuits", 0, 30) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(26, app.items[0].quality);
    }

    // Backstage Passes
    @Test
    void test_backstage_item_not_near_expiry_increases_quality() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 30, 5) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_backstage_item_expiring_in_10_days_increases_quality_by_2() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(7, app.items[0].quality);
    }

    @Test
    void test_backstage_item_expiring_in_5_days_increases_quality_by_3() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void test_backstage_item_quality_0_when_expired() {
        // Arrange
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5) };
        GildedRose app = new GildedRose(items);
        // Act
        app.updateQuality();
        // Assert
        assertEquals(0, app.items[0].quality);
    }
}
