package com.cry.web_delivery.Service.Imp;

import com.cry.web_delivery.dto.RestaurantDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {

    boolean insertRestauurant(MultipartFile file,String title,String subTitle,
                              String description,
                               boolean is_freeship,
                             String address, String open_date);
    List<RestaurantDto> getHomePageRestaurant();
    RestaurantDto getDetailRestaurant(int id);

    void deleteRestaurant(int id);

}

