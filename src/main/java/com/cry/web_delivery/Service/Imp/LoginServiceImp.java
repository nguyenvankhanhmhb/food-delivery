package com.cry.web_delivery.Service.Imp;

import com.cry.web_delivery.dto.UserDto;
import com.cry.web_delivery.payload.request.SignupRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDto> getAllUser();
    boolean checkLogin(String username, String password );
    boolean addUser(SignupRequest signupRequest);
}
