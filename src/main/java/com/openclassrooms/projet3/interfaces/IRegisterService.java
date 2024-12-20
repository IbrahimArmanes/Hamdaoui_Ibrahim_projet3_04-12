package com.openclassrooms.projet3.interfaces;

import com.openclassrooms.projet3.model.RegisterRequest;
import com.openclassrooms.projet3.model.RegisterResponse;

public interface IRegisterService {
    RegisterResponse register(RegisterRequest request);
}
