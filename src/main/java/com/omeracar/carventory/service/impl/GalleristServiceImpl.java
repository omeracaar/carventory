package com.omeracar.carventory.service.impl;

import com.omeracar.carventory.dto.DtoAddress;
import com.omeracar.carventory.dto.DtoGallerist;
import com.omeracar.carventory.dto.DtoGalleristIU;
import com.omeracar.carventory.exception.BaseException;
import com.omeracar.carventory.exception.ErrorMessage;
import com.omeracar.carventory.exception.MessageType;
import com.omeracar.carventory.model.Address;
import com.omeracar.carventory.model.Gallerist;
import com.omeracar.carventory.repository.AddressRepository;
import com.omeracar.carventory.repository.GalleristRepository;
import com.omeracar.carventory.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU){
        Optional<Address> optionalAddress=addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist=new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU,gallerist);
        gallerist.setAddress(optionalAddress.get());

        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist=new DtoGallerist();
        DtoAddress dtoAddress= new DtoAddress();
        Gallerist savedGallerist=galleristRepository.save(createGallerist(dtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist,dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(),dtoAddress);

        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }
}
