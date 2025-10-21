package com.omeracar.carventory.controller.impl;

import com.omeracar.carventory.controller.IRestCurrencyRatesController;
import com.omeracar.carventory.controller.RestBaseController;
import com.omeracar.carventory.controller.RootEntity;
import com.omeracar.carventory.dto.CurrencyRatesResponse;
import com.omeracar.carventory.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/currency-rates")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {

    @Autowired
    private ICurrencyRatesService iCurrencyRatesService;

    @GetMapping("/")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(
            @RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
        return ok(iCurrencyRatesService.getCurrencyRates(startDate,endDate));
    }
}
