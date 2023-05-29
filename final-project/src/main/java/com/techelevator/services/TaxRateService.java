package com.techelevator.services;

import com.techelevator.model.TaxRate;

public interface TaxRateService {

    TaxRate getTaxRate(String stateCode);
}
