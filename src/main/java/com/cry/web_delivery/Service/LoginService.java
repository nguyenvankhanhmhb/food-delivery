package com.cry.web_delivery.Service;

import com.cry.web_delivery.Entity.Roles;
import com.cry.web_delivery.Entity.Users;
import com.cry.web_delivery.Repository.UserRepository;
import com.cry.web_delivery.Service.Imp.LoginServiceImp;
import com.cry.web_delivery.Service.Imp.UserSeviceImp;
import com.cry.web_delivery.dto.UserDto;
import com.cry.web_delivery.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;



    @Override
    public List<UserDto> getAllUser() {
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
    @Override
    public boolean checkLogin(String username, String password) {
        List<Users> listUser = userRepository.findByUserNameAndPassword(username,password);
        return listUser.size()>0;

    }

    @Override
    public boolean addUser(SignupRequest signupRequest) {

        Roles roles = new Roles();
        roles.setId(signupRequest.getRoleId());

        Users users = new Users();
        users.setFullName(signupRequest.getFullName());
        users.setUserName(signupRequest.getEmail());
        users.setPassword(signupRequest.getPassword());
        users.setRoles(roles);
        try{
            userRepository.save(users);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
