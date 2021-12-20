package com.gildedrose;

public class ItemBuilder {
    Item newItem = new Item();

    public ItemBuilder withName(String value){
        newItem.setName(value);
        return this;
    }
    public ItemBuilder withSellIn(int value){
        newItem.setSellIn(value);
        return this;
    }
    public ItemBuilder withQuality(int value){
        newItem.setQuality(value);
        return this;
    }
    public ItemBuilder withMaxQuality(int value){
        newItem.setMaxQuality(value);
        return this;
    }
    public ItemBuilder withItemType(ItemType value){
        newItem.setItemType(value);
        if(value == ItemType.legendary){
            newItem.setMaxQuality(80);
            newItem.setQuality(80);
        }
        return this;
    }
    public ItemBuilder withMinSellIn(int value){
        newItem.setMinSellIn(value);
        return this;
    }
    public ItemBuilder withPrice(double value){
        newItem.setPrice(value);
        return this;
    }
    public Item build(){
        return newItem;
    }

}
