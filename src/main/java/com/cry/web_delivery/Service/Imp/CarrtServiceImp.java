package com.cry.web_delivery.Service.Imp;

import com.cry.web_delivery.payload.request.CartRequest;
import com.cry.web_delivery.payload.response.CartResponse;

import java.util.List;

public interface CarrtServiceImp {
    boolean insertProductIntoCart(CartRequest cartRequest);
  List<CartResponse>  getCart(int idUser);

}
