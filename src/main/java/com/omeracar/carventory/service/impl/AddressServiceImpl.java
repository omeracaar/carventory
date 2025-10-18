package com.omeracar.carventory.service;

import com.omeracar.carventory.exception.BaseException;
import com.omeracar.carventory.exception.ErrorMessage;
import com.omeracar.carventory.exception.MessageType;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements IAddressService{

    public void test(){
        //throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,null));
    }
}
