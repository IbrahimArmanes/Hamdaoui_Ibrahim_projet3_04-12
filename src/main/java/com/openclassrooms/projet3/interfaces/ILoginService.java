package com.openclassrooms.projet3.interfaces;

import com.openclassrooms.projet3.model.LoginRequest;
import com.openclassrooms.projet3.model.LoginResponse;

public interface ILoginService {
    LoginResponse login(LoginRequest request);
}
