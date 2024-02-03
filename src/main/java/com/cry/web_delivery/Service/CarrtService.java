package com.cry.web_delivery.Service;

import com.cry.web_delivery.Entity.CartEntity;
import com.cry.web_delivery.Entity.Food;
import com.cry.web_delivery.Entity.Users;
import com.cry.web_delivery.Repository.CarrtRepositoty;
import com.cry.web_delivery.Repository.FoodRepository;
import com.cry.web_delivery.Repository.UserRepository;
import com.cry.web_delivery.Service.Imp.CarrtServiceImp;
import com.cry.web_delivery.payload.request.CartRequest;
import com.cry.web_delivery.payload.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarrtService implements CarrtServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CarrtRepositoty carrtRepositoty;

    @Override
    public boolean insertProductIntoCart(CartRequest cartRequest) {

        CartEntity cartEntity = new CartEntity();
        Optional<Food> food = foodRepository.findById(cartRequest.getFoodId());
        Optional<Users> users = userRepository.findById(cartRequest.getIdUser());

        if (food.isPresent() && users.isPresent()) {
            cartEntity.setFood(food.get());
            cartEntity.setUsers(users.get());
            cartEntity.setQuantity(cartRequest.getQuantity());

            try {
                carrtRepositoty.save(cartEntity);
                return true;
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
                return false;

            }

        }
        return false;
    }

    @Override
    public List<CartResponse> getCart(int idUser) {
        List<CartEntity> cartEntities = carrtRepositoty.findAll();
        List<CartResponse> cartResponses = new ArrayList<>();
        for (CartEntity c : cartEntities){
            if(c.getUsers().getId() == idUser){
                CartResponse cartTemp = new CartResponse();
                cartTemp.setCart(c.getId());
                cartTemp.setQuantity(c.getQuantity());
                cartTemp.setNameFood(c.getFood().getName());


                Food food = c.getFood();
                if(food != null){
                    cartTemp.setPrice(food.getPrice());
                    cartTemp.setImage(food.getImg());
                }


                cartResponses.add(cartTemp);

            }
        }
        return cartResponses;
    }
}


