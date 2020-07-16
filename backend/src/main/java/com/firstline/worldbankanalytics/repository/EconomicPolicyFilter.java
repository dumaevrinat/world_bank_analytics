package com.firstline.worldbankanalytics.repository;

import lombok.Data;

@Data
public class EconomicPolicyFilter {
    private String countryName;
    private Integer timeFrom;
    private Integer timeTo;
    private Double gdpGrowthAnnualFrom;
    private Double gdpGrowthAnnualTo;
}
