package com.omeracar.carventory.controller.impl;

import com.omeracar.carventory.controller.IRestGalleristController;
import com.omeracar.carventory.controller.RestBaseController;
import com.omeracar.carventory.controller.RootEntity;
import com.omeracar.carventory.dto.DtoGallerist;
import com.omeracar.carventory.dto.DtoGalleristIU;
import com.omeracar.carventory.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController extends RestBaseController implements IRestGalleristController {

    @Autowired
    private IGalleristService iGalleristService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(iGalleristService.saveGallerist(dtoGalleristIU));
    }
}
