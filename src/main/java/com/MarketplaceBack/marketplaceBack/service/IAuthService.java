package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.DTO.Login;
import com.MarketplaceBack.marketplaceBack.models.DTO.ResponseDTO;
import com.MarketplaceBack.marketplaceBack.models.Usuario;

import java.util.HashMap;

public interface IAuthService {
    public HashMap<String,String> login(Login login) throws Exception;
    public ResponseDTO register(Usuario usuario) throws Exception;
}
