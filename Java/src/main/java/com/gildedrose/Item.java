package com.gildedrose;
import java.text.DecimalFormat;
public class Item {

    private String name;
    private int sellIn;
    private int quality;
    private ItemType type;
    private double price;
    private int maxQuality;
    private int minSellIn;

    private GildedRose shop;

    public Item(){

    }

    public Item(String name, int sellIn, int quality, ItemType type, int maxQuality, int minSellIn) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        if(type != null){
            this.type = type;
        }
        this.maxQuality = maxQuality;
        this.minSellIn = minSellIn;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String value){
        this.name = value;
    }

    public int getSellIn() {
        return this.sellIn;
    }

    public void setSellIn(int value){
        this.sellIn = value;
    }

    public int getQuality(){
        return this.quality;
    }

    public void setQuality(int value){
        this.quality = value;
    }

    public int getMaxQuality(){
        return this.maxQuality;
    }

    public void setMaxQuality(int value){
        this.maxQuality = value;
    }

    public ItemType getItemType(){
        return this.type;
    }

    public void setItemType(ItemType value){
        this.type = value;
    }

    public int getMinSellIn(){
        return this.minSellIn;
    }

    public void setMinSellIn(int value){
        this.minSellIn = value;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double value){
        this.price = value;
    }

    public void increase_quality(int amount){
        setQuality(Math.min(getQuality() + amount, maxQuality));
    }

    public void decrease_quality(int amount){
        setQuality(Math.max(getQuality() - amount, 0));
    }

    public void decrease_sell_in(int amount){
        setSellIn(getSellIn() - amount);
    }

    public boolean has_expired(){
        if(sellIn <=  minSellIn){

            return true;
        } else {
            return false;
        }
    }

    public void decrease_quality_if_expired(int amount){
        if(has_expired()){
            decrease_quality(amount);
        }
    }

    private void update_quality_aging(){
        decrease_sell_in(1);
        if (has_expired()){
            decrease_quality(1);
        } else {
            increase_quality(1);
        }
        notifyShop();
    }

    private void update_quality_backstage_passes(){
        decrease_sell_in(1);
        if(has_expired()){
            setQuality(0);
        } else {
            increase_quality(1);
            if(getSellIn() <= 10){
                increase_quality(1);
            }
            if(getSellIn() <= 5){
                increase_quality(1);
            }
        }
        notifyShop();
    }

    private void update_quality_conjured_items(){
        decrease_sell_in(1);
        decrease_quality(2);
        decrease_quality_if_expired(2);
        notifyShop();
    }

    private void update_quality_normal_item(){
        decrease_sell_in(1);
        decrease_quality(1);
        decrease_quality_if_expired(1);
        notifyShop();
    }

    public void setShop(GildedRose shop){
        this.shop = shop;
    }

    private void notifyShop(){
        if(shop != null){
            shop.UpdateItemStatus(this);
        }
    }

    public void PrintItemDetails(){
        DecimalFormat f = new DecimalFormat("##.00");
        String print = this.name;
        print += " £" + f.format(price);
        print += " - expires: ";
        switch(type){
            case legendary :
                print += "never!";
                break;
            default :
                print += sellIn;
                print += " day";
                if(sellIn != 1){
                    print += "s";
                }
                break;
        }
        print += ". Quality: " + quality;
        System.out.println(print);
    }

    public void update(){
        switch(type){
            case aging:
                update_quality_aging();
                break;
            case backstagepass :
                update_quality_backstage_passes();
                break;
            case conjured : 
                update_quality_conjured_items();
                break;
            case legendary :
                break;
            case none :
                update_quality_normal_item();
                break;
            default : 
                break;
        }
        
    }
}

