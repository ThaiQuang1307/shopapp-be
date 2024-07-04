package com.project.shopapp.services;

import com.project.shopapp.models.RoleModel;

import java.util.List;

public interface IRoleService {
    List<RoleModel> getRoles() throws Exception;
}
