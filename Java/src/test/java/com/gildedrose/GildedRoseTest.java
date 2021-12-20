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
}
