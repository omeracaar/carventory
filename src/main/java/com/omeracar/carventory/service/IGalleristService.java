package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.DtoGallerist;
import com.omeracar.carventory.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
