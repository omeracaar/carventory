package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.DtoGallerist;
import com.omeracar.carventory.dto.DtoGalleristCar;
import com.omeracar.carventory.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
