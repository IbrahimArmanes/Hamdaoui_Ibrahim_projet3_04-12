package com.openclassrooms.projet3.interfaces;

import com.openclassrooms.projet3.dto.UserDTO;

public interface IUserService {
    UserDTO getUserByEmail(String email);
    UserDTO getUserById(Long id);
}
