package com.cry.web_delivery.Controller;

import com.cry.web_delivery.Service.Imp.OrderServiceImp;
import com.cry.web_delivery.payload.ResponseData;
import com.cry.web_delivery.payload.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderServiceImp orderServiceImp;


    @PostMapping("")
    public ResponseEntity<?> getAllUser(@RequestBody OrderRequest orderRequest) {

        return new ResponseEntity<>(orderServiceImp.insertOrder(orderRequest),HttpStatus.OK);
    }
}
