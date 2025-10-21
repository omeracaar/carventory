package com.omeracar.carventory.service.impl;

import com.omeracar.carventory.dto.CurrencyRatesResponse;
import com.omeracar.carventory.exception.BaseException;
import com.omeracar.carventory.exception.ErrorMessage;
import com.omeracar.carventory.exception.MessageType;
import com.omeracar.carventory.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesImpl implements ICurrencyRatesService {


    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootURL="https://evds2.tcmb.gov.tr/service/evds/";
        String series="TP.DK.USD.A";
        String type="json";

        String endpoint=rootURL+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;
        //https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A&startDate=20-10-2025&endDate=20-10-2025&type=json

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Key","hxnt4FDHop");

        HttpEntity<?> httpEntity=new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate=new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> response=restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
            });
            if (response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED,e.getMessage()));
        }

        return null;

    }
}
