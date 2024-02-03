package com.cry.web_delivery.Service;

import com.cry.web_delivery.Entity.Food;
import com.cry.web_delivery.Entity.MenuRestaurant;
import com.cry.web_delivery.Entity.RatingRestaurant;
import com.cry.web_delivery.Entity.Restaurant;
import com.cry.web_delivery.Repository.RestaurantRepository;
import com.cry.web_delivery.Service.Imp.FileStorageServiceImp;
import com.cry.web_delivery.Service.Imp.RestaurantServiceImp;
import com.cry.web_delivery.dto.CategoryDto;
import com.cry.web_delivery.dto.MenuDto;
import com.cry.web_delivery.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantServie implements RestaurantServiceImp {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FileStorageServiceImp fileStorageServiceImp;

    @Override
    public boolean insertRestauurant(MultipartFile file, String title, String subTitle, String description, boolean is_freeship, String address, String open_date) {
        boolean isInsertIsuccess = false;
        try {
            boolean isSaveFileSuccess = fileStorageServiceImp.saveFile(file);
            if (isSaveFileSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubTitle(subTitle);
                restaurant.setAddress(address);
                restaurant.setDesc(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(is_freeship);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date openDate = simpleDateFormat.parse(open_date);
                restaurant.setOpenDate(openDate);

                restaurantRepository.save(restaurant);
                isInsertIsuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Error insert retaurant: " + e.getMessage());
        }
        return isInsertIsuccess;
    }

    @Override
    public List<RestaurantDto> getHomePageRestaurant() {

        PageRequest pageRequest = PageRequest.of(0,6);
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);

        for(Restaurant data : listData){
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setId(data.getId());
            restaurantDto.setImage(data.getImage());
            restaurantDto.setTitle(data.getTitle());
            restaurantDto.setSubTitle(data.getSubTitle());
            restaurantDto.setFressShip(data.isFreeship());
            restaurantDto.setRating(calculatorRating(data.getListRatingRestaurnat()));

            restaurantDtos.add(restaurantDto);
        }
        return restaurantDtos;
    }

    @Override
    public RestaurantDto getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDto restaurantDto = new RestaurantDto();

        if(restaurant.isPresent()){

            Restaurant data = restaurant.get();// tat option tra ve restaurantntity
            List<CategoryDto> categoryDtoList = new ArrayList<>();


            restaurantDto.setTitle(data.getTitle());
            restaurantDto.setSubTitle(data.getSubTitle());
            restaurantDto.setImage(data.getImage());
            restaurantDto.setRating(calculatorRating(data.getListRatingRestaurnat()));
            restaurantDto.setFressShip(data.isFreeship());
            restaurantDto.setOpenDate(data.getOpenDate());
            restaurantDto.setDesc(data.getDesc());
//category
            for (MenuRestaurant menuRestaurant : data.getListMenuRestaurants()){
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setName(menuRestaurant.getCategory().getNameCate());
//menu
                List<MenuDto> menuDtoList = new ArrayList<>();
                for(Food food:menuRestaurant.getCategory().getListFood()){
                    MenuDto menuDto = new MenuDto();
                    menuDto.setTitle(food.getTitle());
                    menuDto.setFreeship(food.isFreeShip());
                    menuDto.setImage(food.getImg());
                    menuDto.setDesc(food.getDesc());
                    menuDto.setPrice(food.getPrice());
                    menuDto.setId(food.getId());
                    menuDtoList.add(menuDto);

                }
                categoryDto.setMenus(menuDtoList);
                categoryDtoList.add(categoryDto);
            }

            restaurantDto.setCategorys(categoryDtoList);
        }
        return restaurantDto;
    }

    @Override
    public void deleteRestaurant(int id) {
        this.restaurantRepository.deleteById(id);
    }


    private double calculatorRating(Set<RatingRestaurant> listRating){
        double totalPoint= 0;
        for(RatingRestaurant data : listRating){
            totalPoint += data.getRatePoint();
        }
        return totalPoint/listRating.size();
    }
}
