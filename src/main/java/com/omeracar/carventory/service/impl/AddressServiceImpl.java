package com.omeracar.carventory.service.impl;

import com.omeracar.carventory.dto.DtoAddress;
import com.omeracar.carventory.dto.DtoAddressIU;
import com.omeracar.carventory.exception.BaseException;
import com.omeracar.carventory.exception.ErrorMessage;
import com.omeracar.carventory.exception.MessageType;
import com.omeracar.carventory.model.Address;
import com.omeracar.carventory.repository.AddressRepository;
import com.omeracar.carventory.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU){
        Address address=new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU,address);
        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress=new DtoAddress();
        Address savedAddress=addressRepository.save(createAddress(dtoAddressIU));
        BeanUtils.copyProperties(savedAddress,dtoAddress);
        return dtoAddress;

    }
}
