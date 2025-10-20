package com.omeracar.carventory.controller.impl;

import com.omeracar.carventory.controller.IRestAddresController;
import com.omeracar.carventory.controller.RestBaseController;
import com.omeracar.carventory.controller.RootEntity;
import com.omeracar.carventory.dto.DtoAddress;
import com.omeracar.carventory.dto.DtoAddressIU;
import com.omeracar.carventory.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddresController {

    @Autowired
    private IAddressService iAddressService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return RootEntity.ok(iAddressService.saveAddress(dtoAddressIU));
    }
}
