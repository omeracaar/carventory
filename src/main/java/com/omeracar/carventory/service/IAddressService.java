package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.DtoAddress;
import com.omeracar.carventory.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

}
