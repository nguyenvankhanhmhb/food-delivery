package com.cry.web_delivery.Controller;

import com.cry.web_delivery.Service.Imp.CarrtServiceImp;
import com.cry.web_delivery.payload.request.CartRequest;
import com.cry.web_delivery.payload.ResponseData;
import com.cry.web_delivery.payload.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CarrtController {

    @Autowired
    private CarrtServiceImp carrtServiceImp;

    @PostMapping("")
    public ResponseEntity<?> insertToCart(@RequestBody CartRequest cartRequest){
        boolean isSuccess = carrtServiceImp.insertProductIntoCart(cartRequest);
        ResponseData responseData = new ResponseData();
        responseData.setData(isSuccess ? "Insert Successly": " Insert Failed ");
        responseData.setDesc("Insert Cart Success");
        responseData.setStatus(200);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getCarts(@PathVariable int idUser){
        List<CartResponse> cartResponseList = carrtServiceImp.getCart(idUser);
        ResponseData responseData = new ResponseData();
        responseData.setData(cartResponseList);
        responseData.setDesc("get cart info User ID");
        responseData.setStatus(200);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
