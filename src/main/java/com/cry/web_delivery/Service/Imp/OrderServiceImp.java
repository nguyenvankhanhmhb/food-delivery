package com.cry.web_delivery.Service.Imp;

import com.cry.web_delivery.payload.request.OrderRequest;

public interface OrderServiceImp {
    boolean insertOrder(OrderRequest orderRequest);

}
