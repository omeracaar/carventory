package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.DtoAccount;
import com.omeracar.carventory.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
