//package com.cry.web_delivery.Entity.keys;
//
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//
//import java.io.Serializable;
//
//@Embeddable
//public class KeyCartItem implements Serializable {
//
//    @Column(name = "user_id")
//    private int userId;
//
//    @Column(name="food_id")
//    private int foodId;
//
//    public KeyCartItem(){}
//
//    public KeyCartItem( int userId, int foodId){
//        this.foodId =  foodId;
//        this.userId  =userId;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getFoodId() {
//        return foodId;
//    }
//
//    public void setFoodId(int foodId) {
//        this.foodId = foodId;
//    }
//}
