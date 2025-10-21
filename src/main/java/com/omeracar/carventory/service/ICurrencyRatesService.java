package com.omeracar.carventory.service;

import com.omeracar.carventory.dto.CurrencyRatesResponse;
import lombok.Data;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}
