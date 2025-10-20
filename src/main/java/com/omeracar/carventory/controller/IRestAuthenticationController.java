package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.AuthRequest;
import com.omeracar.carventory.dto.AuthResponse;
import com.omeracar.carventory.dto.DtoUser;
import com.omeracar.carventory.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

}
