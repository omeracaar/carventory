package com.omeracar.carventory.service.impl;

import com.omeracar.carventory.dto.*;
import com.omeracar.carventory.enums.CarStatusType;
import com.omeracar.carventory.exception.BaseException;
import com.omeracar.carventory.exception.ErrorMessage;
import com.omeracar.carventory.exception.MessageType;
import com.omeracar.carventory.model.Car;
import com.omeracar.carventory.model.Customer;
import com.omeracar.carventory.model.SaledCar;
import com.omeracar.carventory.repository.CarRepository;
import com.omeracar.carventory.repository.CustomerRepository;
import com.omeracar.carventory.repository.GalleristRepository;
import com.omeracar.carventory.repository.SaledCarRepository;
import com.omeracar.carventory.service.ICurrencyRatesService;
import com.omeracar.carventory.service.ISaledCarService;
import com.omeracar.carventory.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaledCarService implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService iCurrencyRatesService;


    public BigDecimal converCustomerAmountToUSD(Customer customer) {

        CurrencyRatesResponse currencyRatesResponse = iCurrencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal custumerUsdAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

        return custumerUsdAmount;

    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
        }

        BigDecimal customerUsdAmount = converCustomerAmountToUSD(optCustomer.get());
        if (customerUsdAmount.compareTo(optCar.get().getPrice()) == 0 || customerUsdAmount.compareTo(optCar.get().getPrice()) > 0) {
            return true;
        }
        return false;

    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    public boolean checkCarStatus(Long carId){
        Optional<Car> optCar=carRepository.findById(carId);
        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED)){
            return false;
        }
        return true;
    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car){
        BigDecimal customerUSDAmount=converCustomerAmountToUSD(customer);
        BigDecimal remainingCustomerUSDAmount=customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse=iCurrencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
        BigDecimal usd=new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        return remainingCustomerUSDAmount.multiply(usd);
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
        }

        if (!checkCarStatus(dtoSaledCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED,dtoSaledCarIU.getCarId().toString()));
        }


        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

        Car car=savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer=savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer,car));
        customerRepository.save(customer);



        return toDto(savedSaledCar);

    }

    public DtoSaledCar toDto(SaledCar saledCar){
        DtoSaledCar dtoSaledCar=new DtoSaledCar();
        DtoCustomer dtoCustomer=new DtoCustomer();
        DtoGallerist dtoGallerist=new DtoGallerist();
        DtoCar dtoCar=new DtoCar();

        BeanUtils.copyProperties(saledCar,dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(),dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(),dtoCar);

        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setDtoCar(dtoCar);
        return dtoSaledCar;
    }


}
