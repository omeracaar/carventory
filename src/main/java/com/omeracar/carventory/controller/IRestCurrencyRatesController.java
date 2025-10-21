package com.omeracar.carventory.controller;

import com.omeracar.carventory.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
