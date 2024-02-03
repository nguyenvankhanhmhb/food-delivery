//package com.cry.web_delivery.Entity;
//
//import com.cry.web_delivery.dto.ItemDto;
//import org.springframework.data.redis.core.RedisHash;
//
//import java.io.Serializable;
//import java.util.List;
//
//
//@RedisHash("cart")
//public class CartRedisEntity implements Serializable {
//    private int userID;
//    private List<ItemDto> items;
//    private float price;
//
//    public int getUserID() {
//        return userID;
//    }
//
//    public List<ItemDto> getItems() {
//        return items;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//    public void calculatorCartPrice(){
//        float price = 0;
//        for (ItemDto item : this.getItems()){
//            price += item.getPrice()* item.getQuantity();
//        }
//        this.price = price;
//    }
//
//}
