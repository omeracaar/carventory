package com.omeracar.carventory.controller.impl;

import com.omeracar.carventory.controller.IRestAuthenticationController;
import com.omeracar.carventory.controller.RestBaseController;
import com.omeracar.carventory.controller.RootEntity;
import com.omeracar.carventory.dto.AuthRequest;
import com.omeracar.carventory.dto.AuthResponse;
import com.omeracar.carventory.dto.DtoUser;
import com.omeracar.carventory.dto.RefreshTokenRequest;
import com.omeracar.carventory.service.impl.AuthenticateServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationImpl extends RestBaseController implements IRestAuthenticationController{

    @Autowired
    private AuthenticateServiceImpl authenticateService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
        return ok(authenticateService.register(input));
    }

    @PostMapping("/authenticate")//security configdeki ile aynı yazilmali!!
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
        return ok(authenticateService.authenticate(input));

    }

    @PostMapping("/refreshToken")//security configdeki ile aynı yazilmali!!
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
        return ok(authenticateService.refreshToken(input));

    }

}
