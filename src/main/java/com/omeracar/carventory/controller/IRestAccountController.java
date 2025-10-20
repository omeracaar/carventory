package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.DtoAccount;
import com.omeracar.carventory.dto.DtoAccountIU;

public interface IRestAccountController {

    public RootEntity<DtoAccount> savedAccount(DtoAccountIU dtoAccountIU);
}
