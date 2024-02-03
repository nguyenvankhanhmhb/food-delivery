package com.cry.web_delivery.Service;

import com.cry.web_delivery.Entity.Users;
import com.cry.web_delivery.Repository.UserRepository;
import com.cry.web_delivery.Service.Imp.UserSeviceImp;
import com.cry.web_delivery.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserSeviceImp {
    @Autowired
    UserRepository userRepository;


    @Override
    public List<UserDto> getAlluser() {

        List<Users>  listUser = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (Users user: listUser){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserName(user.getUserName());
            userDto.setFullName(user.getFullName());
            userDto.setPassword(userDto.getPassword());

            userDtoList.add(userDto);


        }
        return userDtoList;
    }
}
