package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.DtoCustomer;
import com.omeracar.carventory.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
