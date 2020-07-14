package com.firstline.worldbankanalytics.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "economic_policy_prediction")
public class EconomicPolicyPrediction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "time")
    private int time;

    @Column(name = "gdp_growth_annual")
    private double gdpGrowthAnnual;
}
