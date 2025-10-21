package com.omeracar.carventory.controller.impl;

import com.omeracar.carventory.controller.IRestGalleristCarController;
import com.omeracar.carventory.controller.RestBaseController;
import com.omeracar.carventory.controller.RootEntity;
import com.omeracar.carventory.dto.DtoGalleristCar;
import com.omeracar.carventory.dto.DtoGalleristCarIU;
import com.omeracar.carventory.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarController extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService iGalleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(iGalleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }
}
