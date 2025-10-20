package com.omeracar.carventory.controller.impl;

import com.omeracar.carventory.controller.IRestCustomerController;
import com.omeracar.carventory.controller.RestBaseController;
import com.omeracar.carventory.controller.RootEntity;
import com.omeracar.carventory.dto.DtoCustomer;
import com.omeracar.carventory.dto.DtoCustomerIU;
import com.omeracar.carventory.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

    @Autowired
    private ICustomerService iCustomerService;


    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(iCustomerService.saveCustomer(dtoCustomerIU));
    }
}
