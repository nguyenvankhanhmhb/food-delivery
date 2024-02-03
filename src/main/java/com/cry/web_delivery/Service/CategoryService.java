package com.cry.web_delivery.Service;

import com.cry.web_delivery.Entity.Category;
import com.cry.web_delivery.Entity.Food;
import com.cry.web_delivery.Repository.CategoryRepository;
import com.cry.web_delivery.Service.Imp.CategoryServiceImp;
import com.cry.web_delivery.dto.CategoryDto;
import com.cry.web_delivery.dto.MenuDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

//    @Autowired
//    private RedisTemplate redisTemplate;

    private Gson gson =new Gson();


    @Override
    public List<CategoryDto> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0,3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        List<CategoryDto> listCategoryDtos = new ArrayList<>();

        for (Category data : listCategory){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(data.getNameCate());

            List<MenuDto> menuDtos = new ArrayList<>();

            for (Food dataFood : data.getListFood()){
                MenuDto menuDto = new MenuDto();
                menuDto.setTitle(dataFood.getTitle());
                menuDto.setFreeship(dataFood.isFreeShip());
                menuDto.setImage(dataFood.getImg());

                menuDtos.add(menuDto);
            }
            categoryDto.setMenus(menuDtos);
            listCategoryDtos.add(categoryDto);
        }
//String dataJson = gson.toJson(listCategoryDtos);
//        redisTemplate.opsForValue().set("listCategory", dataJson);

        return listCategoryDtos ;
    }
}
