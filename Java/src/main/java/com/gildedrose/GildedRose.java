package com.gildedrose;
import java.util.Set;
import java.util.ArrayList;
class GildedRose {
    ArrayList<Item> items;

    public GildedRose() {
        this.items = new ArrayList<Item>();
    }

    public void AddItem(Item item){
        items.add(item);
    }

    public void RemoveItem(Item item){
        int index = items.indexOf(item);
        if(index >= 0){
            items.remove(index);
        }
    }

    public void UpdateItems(){
        for(int i = 0; i < items.size(); i++){
            items.get(i).update();
        }
    }

    public void UpdateItemStatus(Item item){
        if(item.getQuality() == 0){
            RemoveItem(item);
            return;
        }
        if(item.has_expired()){
            item.setPrice(item.getPrice() / 2);
        }
    }


    public void PrintItems(){
        for(Item item : items){
            item.PrintItemDetails();
        }
        System.out.println("------------------------------");
    }
}