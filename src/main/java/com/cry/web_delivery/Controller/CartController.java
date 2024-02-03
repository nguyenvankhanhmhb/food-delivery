//package com.cry.web_delivery.Controller;
//
////import com.cry.web_delivery.Service.Imp.CartServiceImp;
//import com.cry.web_delivery.dto.ItemDto;
//import com.cry.web_delivery.payload.ResponseData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//
//    @Autowired
//    private CartServiceImp cartServiceImp;
//
//    @PostMapping("/add")
//    public ResponseEntity<?> addItemToCart(@RequestBody ItemDto item) {
//        String email = SecurityContextHolder.getContext()
//                .getAuthentication().getName();
//        ResponseData responseSuccess = new ResponseData();
//        responseSuccess.setStatus(HttpStatus.OK.value());
//        responseSuccess.setData(cartServiceImp.addItem(email,item));
//        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
//    }
//
//    @GetMapping("/view-cart")
//    public ResponseEntity<?> viewCart() {
//        String email = SecurityContextHolder.getContext()
//                .getAuthentication().getName();
//        ResponseData responseSuccess = new ResponseData();
//        responseSuccess.setStatus(HttpStatus.OK.value());
//        responseSuccess.setData(cartServiceImp.getCart(email));
//        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
//    }
//}
