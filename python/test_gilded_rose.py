# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose


class GildedRoseTest(unittest.TestCase):
    def test_item_name_matches(self):
        # Arrange
        items = [Item("fixme", 0, 0)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual("fixme", items[0].name)

    def test_decrease_quality_decrements(self):
        # Arrange
        items = [Item("New Item", 0, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.decrease_quality(items[0], 1)
        # Assert
        self.assertEqual(9, items[0].quality)

    def test_decrease_quality_decreases_by_parameter(self):
        # Arrange
        items = [Item("New Item", 0, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.decrease_quality(items[0], 5)
        # Assert
        self.assertEqual(5, items[0].quality)

    def test_decrease_sell_in_decrements(self):
        # Arrange
        items = [Item("New Item", 10, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.decrease_sell_in(items[0], 1)
        # Assert
        self.assertEqual(9, items[0].sell_in)

    def test_decrease_sell_in_decreases_by_parameter(self):
        # Arrange
        items = [Item("New Item", 10, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.decrease_sell_in(items[0], 5)
        # Assert
        self.assertEqual(5, items[0].sell_in)

    def test_decrease_quality_if_expired_decreases_by_parameter(self):
        # Arrange
        items = [Item("New Item", 0, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.decrease_quality_if_expired(items[0], 2)
        # Assert
        self.assertEqual(8, items[0].quality)

    # Normal Items
    def test_normal_item_loses_quality(self):
        # Arrange
        items = [Item("NewItem", 10, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(9, items[0].quality)

    def test_normal_item_expires_and_loses_double_quantity(self):
        # Arrange
        items = [Item("NewItem", 1, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(8, items[0].quality)

    def test_normal_item_doesnt_go_under_0_quality(self):
        # Arrange
        items = [Item("NewItem", 10, 0)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(0, items[0].quality)

    def test_normal_item_sell_in_decreases(self):
        # Arrange
        items = [Item("NewItem", 10, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(9, items[0].sell_in)

    def test_normal_item_sell_in_doesnt_go_under_0(self):
        # Arrange
        items = [Item("NewItem", 0, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(0, items[0].sell_in)

    # Aging Increases Quality
    def test_aging_item_loses_sell_time(self):
        # Arrange
        items = [Item("Aged Brie", 10, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(9, items[0].sell_in)

    def test_aging_item_loses_quality_when_sell_time_expired(self):
        # Arrange
        items = [Item("Aged Brie", 0, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(9, items[0].quality)

    def test_aging_item_increases_quality(self):
        # Arrange
        items = [Item("Aged Brie", 10, 2)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(3, items[0].quality)

    def test_aging_item_quality_doesnt_go_over_50(self):
        # Arrange
        items = [Item("Aged Brie", 10, 50)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(50, items[0].quality)

    # Legendary items
    def test_legendary_item_doesnt_lose_quality(self):
        # Arrange
        items = [Item("Sulfuras, Hand of Ragnaros", 0, 80)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(80, items[0].quality)
    
    def test_legendary_item_doesnt_lose_sell_in(self):
        # Arrange
        items = [Item("Sulfuras, Hand of Ragnaros", 10, 80)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(10, items[0].sell_in)

    # Conjured items
    def test_conjured_item_loses_sell_time(self):
        # Arrange
        items = [Item("Conjured Biscuits", 10, 10)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(9, items[0].sell_in)

    def test_conjured_item_loses_double_quality(self):
        # Arrange
        items = [Item("Conjured Biscuits", 10, 30)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(28, items[0].quality)

    def test_conjured_item_loses_quadruple_quality_when_expired(self):
        # Arrange
        items = [Item("Conjured Biscuits", 0, 30)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(26, items[0].quality)

    # Backstage Passes
    def test_backstage_item_not_near_expiry_increases_quality(self):
        # Arrange
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 30, 5)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(6, items[0].quality)
    
    def test_backstage_item_expiring_in_10_days_increases_quality_by_2(self):
        # Arrange
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 10, 5)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(7, items[0].quality)

    def test_backstage_item_expiring_in_2_days_increases_quality_by_3(self):
        # Arrange
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 2, 5)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(8, items[0].quality)

    def test_backstage_item_quality_0_when_expired(self):
        # Arrange
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 0, 5)]
        gilded_rose = GildedRose(items)
        # Act
        gilded_rose.update_quality()
        # Assert
        self.assertEqual(0, items[0].quality)

        
if __name__ == '__main__':
    unittest.main()
