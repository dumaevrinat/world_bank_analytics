package com.firstline.worldbankanalytics.api.v1.dto;

import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EconomicPolicyDTO {
    private Long id;
    private String countryName;
    private int time;
    private double gdpGrowthAnnual;

    public EconomicPolicyDTO(EconomicPolicy economicPolicy) {
        this.id = economicPolicy.getId();
        this.countryName = economicPolicy.getCountryName();
        this.time = economicPolicy.getTime();
        this.gdpGrowthAnnual = economicPolicy.getGdpGrowthAnnual();
    }

    public EconomicPolicy toEntity() {
        EconomicPolicy economicPolicy = new EconomicPolicy();

        economicPolicy.setId(this.getId());
        economicPolicy.setCountryName(this.getCountryName());
        economicPolicy.setTime(this.getTime());
        economicPolicy.setGdpGrowthAnnual(this.getGdpGrowthAnnual());

        return economicPolicy;
    }
}
