package com.cry.web_delivery.Service;

import com.cry.web_delivery.Entity.*;
import com.cry.web_delivery.Entity.keys.KeyOrderItem;
import com.cry.web_delivery.Repository.OrderItemReppository;
import com.cry.web_delivery.Repository.OrderRepository;
import com.cry.web_delivery.Service.Imp.OrderServiceImp;
import com.cry.web_delivery.payload.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    private OrderRepository orderRepository;

@Autowired
private OrderItemReppository orderItemReppository;

    @Override
    public boolean insertOrder(OrderRequest orderRequest) {


        try {
            Users users = new Users();
            users.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getRestId());


            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);
//phải insert trước thì mới nó có id của orderItem
            orderRepository.save(orders);

            List<OrderItem> items = new ArrayList<>();
            for (int idFood : orderRequest.getFoodIds()) {

                Food food = new Food();
                food.setId(idFood);


                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(), idFood);
                orderItem.setKeyOrderItem(keyOrderItem);

                items.add(orderItem);

            }
            orderItemReppository.saveAll(items);
            return true;
        } catch (Exception e) {
            System.out.println("Error Insert order: " +e.getLocalizedMessage()  );
            return false;
        }

    }
}