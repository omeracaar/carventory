package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.DtoGallerist;
import com.omeracar.carventory.dto.DtoGalleristIU;
import com.omeracar.carventory.model.Gallerist;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
