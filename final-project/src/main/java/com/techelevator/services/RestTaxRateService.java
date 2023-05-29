package com.techelevator.services;

import com.techelevator.model.TaxRate;
import org.springframework.web.client.RestTemplate;

public class RestTaxRateService implements TaxRateService{

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public TaxRate getTaxRate(String stateCode) {
        return restTemplate.getForObject("https://teapi.netlify.app/api/statetax?state=" + stateCode, TaxRate.class);
    }
}
