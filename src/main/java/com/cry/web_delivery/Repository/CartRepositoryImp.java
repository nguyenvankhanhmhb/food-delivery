//package com.cry.web_delivery.Repository;
//
//import com.cry.web_delivery.dto.ItemDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class CartRepositoryImp implements CartRepository{
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Override
//    public void addToCart(String id, Object item) {
//        ItemDto itemToAdd = (ItemDto) item;
//        List<ItemDto> currenItems = getCart(id);
//        int index= 0;
//        for(ItemDto currenItem :  currenItems){
//            if(itemToAdd.getProductId() == currenItem.getProductId()){
//                int qty = itemToAdd.getQuantity();
//                itemToAdd.setQuantity(qty + currenItem.getQuantity());
//                currenItems.set(index, itemToAdd);
//                Long indexRedis = redisTemplate.opsForList().indexOf(id, currenItem);
//                redisTemplate.opsForList().set(id, indexRedis, itemToAdd);
//                return;
//            }
//            redisTemplate.opsForList().rightPush(id, item);
//        }
//    }
//
//    @Override
//    public List getCart(String id) {
//        Long end = redisTemplate.opsForList().size(id);
//        return redisTemplate.opsForList().range(id, 0, end-1);
//    }
//}
