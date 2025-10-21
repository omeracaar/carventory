package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.DtoGalleristCar;
import com.omeracar.carventory.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
