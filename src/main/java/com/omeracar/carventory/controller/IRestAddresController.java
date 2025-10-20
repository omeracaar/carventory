package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.DtoAddress;
import com.omeracar.carventory.dto.DtoAddressIU;

public interface IRestAddresController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

}
