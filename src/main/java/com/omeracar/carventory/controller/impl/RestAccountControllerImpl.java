package com.omeracar.carventory.controller.impl;

import com.omeracar.carventory.controller.IRestAccountController;
import com.omeracar.carventory.controller.RestBaseController;
import com.omeracar.carventory.controller.RootEntity;
import com.omeracar.carventory.dto.DtoAccount;
import com.omeracar.carventory.dto.DtoAccountIU;
import com.omeracar.carventory.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService iAccountService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> savedAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return RootEntity.ok(iAccountService.saveAccount(dtoAccountIU));
    }
}
