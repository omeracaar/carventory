package com.omeracar.carventory.controller;


import com.omeracar.carventory.dto.DtoCustomer;
import com.omeracar.carventory.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
