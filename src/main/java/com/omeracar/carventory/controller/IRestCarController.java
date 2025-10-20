package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.DtoCar;
import com.omeracar.carventory.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
