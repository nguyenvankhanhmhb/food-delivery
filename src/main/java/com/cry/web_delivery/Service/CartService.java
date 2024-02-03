//package com.cry.web_delivery.Service;
//
//import com.cry.web_delivery.Repository.CartRepository;
//import com.cry.web_delivery.Service.Imp.CartServiceImp;
//import com.cry.web_delivery.dto.ItemDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CartService implements CartServiceImp {
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Override
//    public ItemDto addItem(String id, ItemDto itemDto) {
//        try {
//            cartRepository.addToCart(id, itemDto);
//            return itemDto;
//        } catch (Exception e) {
//            System.out.println("Error addItem Cart" +e.getMessage());
//        }
//        return itemDto;
//    }
//
//    @Override
//    public List getCart(String id) {
//        return cartRepository.getCart(id);
//    }
//}
