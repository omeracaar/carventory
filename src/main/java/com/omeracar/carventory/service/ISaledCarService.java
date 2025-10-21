package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.DtoSaledCar;
import com.omeracar.carventory.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);

}
