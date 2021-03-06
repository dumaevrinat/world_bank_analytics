package com.firstline.worldbankanalytics.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "economic_policy")
public class EconomicPolicy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "time")
    private Integer time;

    @Column(name = "gdp_growth_annual")
    private double gdpGrowthAnnual;
}
