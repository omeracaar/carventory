package com.omeracar.carventory.service.impl;

import com.omeracar.carventory.dto.DtoCar;
import com.omeracar.carventory.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
