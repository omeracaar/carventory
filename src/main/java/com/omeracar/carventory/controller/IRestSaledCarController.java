package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.DtoSaledCar;
import com.omeracar.carventory.dto.DtoSaledCarIU;
import com.omeracar.carventory.model.SaledCar;

public interface IRestSaledCarController {

    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
