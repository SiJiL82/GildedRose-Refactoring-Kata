# -*- coding: utf-8 -*-


class GildedRose(object):

    def __init__(self, items):
        self.items = items

    # Define special items
    quality_increases_with_age = ["Aged Brie"]
    backstage_passes = ["Backstage passes to a TAFKAL80ETC concert"]
    legendary_items = ["Sulfuras, Hand of Ragnaros"]
    conjured_items = ["Conjured Biscuits"]

    # Thresholds
    max_quality = 50
    min_quality = 0
    min_sell_in = 0

    def increase_quality(self, item, amount):
        item.quality = min(item.quality + amount, self.max_quality)

    def decrease_quality(self, item, amount):
        item.quality = max(item.quality - amount, self.min_quality)

    def decrease_sell_in(self, item, amount):
        item.sell_in = max(item.sell_in - amount, self.min_sell_in)

    def decrease_quality_if_expired(self, item, degrade_amount):
        if item.sell_in == 0:
            self.decrease_quality(item, degrade_amount)

    def update_quality_increasing_with_age(self, item):
        self.decrease_sell_in(item, 1)
        if item.sell_in == 0:
            self.decrease_quality(item, 1)
        else:
            self.increase_quality(item, 1)

    def update_quality_backstage_passes(self, item):
        self.decrease_sell_in(item, 1)
        if item.sell_in == 0:
            item.quality = 0
        else: 
            self.increase_quality(item, 1)
            if item.sell_in < 11:
                self.increase_quality(item, 1)
            if item.sell_in < 6:
                self.increase_quality(item, 1)

    def update_quality_conjured_items(self, item):
        self.decrease_sell_in(item, 1)
        self.decrease_quality(item, 2)
        self.decrease_quality_if_expired(item, 2)

    def update_quality_normal_item(self, item):
        self.decrease_quality(item, 1)
        self.decrease_sell_in(item, 1)
        self.decrease_quality_if_expired(item, 1)

    def update_quality(self):
        # Loop through items
        for item in self.items:
            if item.name in self.quality_increases_with_age:
                self.update_quality_increasing_with_age(item)
                continue
            if item.name in self.backstage_passes:
                self.update_quality_backstage_passes(item)
                continue
            if item.name in self.legendary_items:
                continue
            if item.name in self.conjured_items:
                self.update_quality_conjured_items(item)
                continue

            # all special items dealt with.
            self.update_quality_normal_item(item)
            


class Item:
    def __init__(self, name, sell_in, quality):
        self.name = name
        self.sell_in = sell_in
        self.quality = quality

    def __repr__(self):
        return "%s, %s, %s" % (self.name, self.sell_in, self.quality)
