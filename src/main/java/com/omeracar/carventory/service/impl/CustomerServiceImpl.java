package com.omeracar.carventory.service.impl;

import com.omeracar.carventory.dto.DtoAccount;
import com.omeracar.carventory.dto.DtoAddress;
import com.omeracar.carventory.dto.DtoCustomer;
import com.omeracar.carventory.dto.DtoCustomerIU;
import com.omeracar.carventory.exception.BaseException;
import com.omeracar.carventory.exception.ErrorMessage;
import com.omeracar.carventory.exception.MessageType;
import com.omeracar.carventory.model.Account;
import com.omeracar.carventory.model.Address;
import com.omeracar.carventory.model.Customer;
import com.omeracar.carventory.repository.AccountRepository;
import com.omeracar.carventory.repository.AddressRepository;
import com.omeracar.carventory.repository.CustomerRepository;
import com.omeracar.carventory.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){
        Optional<Address> optionalAddress=addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()));
        }

        Optional<Account> optionalAccount=accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()));
        }

        Customer customer=new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU,customer);

        customer.setAddress(optionalAddress.get());
        customer.setAccount(optionalAccount.get());
        return customer;
    }


    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer=new DtoCustomer();
        DtoAddress dtoAddress=new DtoAddress();
        DtoAccount dtoAccount=new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer,dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        return dtoCustomer;
    }
}
