package com.omeracar.carventory.service.impl;

import com.omeracar.carventory.dto.AuthRequest;
import com.omeracar.carventory.dto.AuthResponse;
import com.omeracar.carventory.dto.DtoUser;
import com.omeracar.carventory.dto.RefreshTokenRequest;
import com.omeracar.carventory.exception.BaseException;
import com.omeracar.carventory.exception.ErrorMessage;
import com.omeracar.carventory.exception.MessageType;
import com.omeracar.carventory.jwt.JWTService;
import com.omeracar.carventory.model.RefreshToken;
import com.omeracar.carventory.model.User;
import com.omeracar.carventory.repository.RefreshTokenRepository;
import com.omeracar.carventory.repository.UserRepository;
import com.omeracar.carventory.service.IAuthenticateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpriredDate(new Date(System.currentTimeMillis()+1000*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    private User createUser(AuthRequest input){
        User user =new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return user;
    }

    @Override
    public DtoUser register(AuthRequest input) {
        DtoUser dtoUser=new DtoUser();
        User savedUser=userRepository.save(createUser(input));
        BeanUtils.copyProperties(savedUser,dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(input.getUsername(),input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> opUser = userRepository.findByUsername(input.getUsername());

            String accesToken=jwtService.generateToken(opUser.get());

            RefreshToken savedRefreshToken=refreshTokenRepository.save(createRefreshToken(opUser.get()));

            return new AuthResponse(accesToken,savedRefreshToken.getRefreshToken());

        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND,e.getMessage()));
        }
    }

    public boolean isValidRefreshToken(Date expiredDate){
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {
        Optional<RefreshToken> optRefreshToken=refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
        if (optRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND,input.getRefreshToken()));
        }

        if (!isValidRefreshToken(optRefreshToken.get().getExpriredDate())){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED,input.getRefreshToken()));
        }
        User user=optRefreshToken.get().getUser();
        String accesToken=jwtService.generateToken(user);
        RefreshToken savedRefreshToken=refreshTokenRepository.save(createRefreshToken(user));

        return new AuthResponse(accesToken,savedRefreshToken.getRefreshToken());
    }
}
