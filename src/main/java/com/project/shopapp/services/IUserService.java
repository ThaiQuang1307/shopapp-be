package com.project.shopapp.services;

import com.project.shopapp.dtos.UserDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.UserModel;

public interface IUserService {
    // dang ky
    UserModel createUser(UserDTO userDTO) throws Exception;

    // dang nhap
    String login(String phoneNumber, String password) throws Exception;
}
