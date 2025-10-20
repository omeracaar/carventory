package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.AuthRequest;
import com.omeracar.carventory.dto.AuthResponse;
import com.omeracar.carventory.dto.DtoUser;
import com.omeracar.carventory.dto.RefreshTokenRequest;

public interface IAuthenticateService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}
