package com.cry.web_delivery.Service;

import com.cry.web_delivery.Entity.Category;
import com.cry.web_delivery.Entity.Food;
import com.cry.web_delivery.Entity.Restaurant;
import com.cry.web_delivery.Repository.FoodRepository;
import com.cry.web_delivery.Service.Imp.FileStorageServiceImp;
import com.cry.web_delivery.Service.Imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements MenuServiceImp {

    @Autowired
    private FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public boolean createMenu(MultipartFile file, String title,
                              String is_freeship, String timme_ship,
                              double price, int cate_id) {
        boolean isInsertIsuccess = false;
        try {
            boolean isSaveFileSuccess = fileStorageServiceImp.saveFile(file);
            if (isSaveFileSuccess) {
               Food food = new Food();
               food.setTitle(title);
               food.setImg(file.getOriginalFilename());
               food.setTimeShip(timme_ship);
               food.setPrice(price);

               Category category = new Category();
               category.setId(cate_id);

               food.setCategory(category);

               foodRepository.save(food);

                isInsertIsuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Error insert retaurant: " + e.getMessage());
        }
        return isInsertIsuccess;
    }
}
