package com.firstline.worldbankanalytics.api.v1.dto;

import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import com.firstline.worldbankanalytics.entity.EconomicPolicyPrediction;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EconomicPolicyPredictionDTO {
    private Long id;
    private String countryName;
    private int time;
    private double gdpGrowthAnnual;

    public EconomicPolicyPredictionDTO(EconomicPolicyPrediction economicPolicyPrediction) {
        this.id = economicPolicyPrediction.getId();
        this.countryName = economicPolicyPrediction.getCountryName();
        this.time = economicPolicyPrediction.getTime();
        this.gdpGrowthAnnual = economicPolicyPrediction.getGdpGrowthAnnual();
    }

    public EconomicPolicyPrediction toEntity() {
        EconomicPolicyPrediction economicPolicyPrediction = new EconomicPolicyPrediction();

        economicPolicyPrediction.setId(this.getId());
        economicPolicyPrediction.setCountryName(this.getCountryName());
        economicPolicyPrediction.setTime(this.getTime());
        economicPolicyPrediction.setGdpGrowthAnnual(this.getGdpGrowthAnnual());

        return economicPolicyPrediction;
    }
}
