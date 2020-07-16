package com.firstline.worldbankanalytics.repository;

import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import org.springframework.data.jpa.domain.Specification;

public class EconomicPolicySpecifications {

    public static Specification<EconomicPolicy> hasCountryName(String countryName) {
        return (economicPolicy, cq, cb) -> countryName == null ? null : cb.equal(economicPolicy.get("countryName"), countryName);
    }

    public static Specification<EconomicPolicy> hasTime(Integer time) {
        return (economicPolicy, cq, cb) -> time == null ? null : cb.equal(economicPolicy.get("time"), time);
    }

    public static Specification<EconomicPolicy> hasTimeTo(Integer time) {
        return (economicPolicy, cq, cb) -> time == null ? null : cb.lessThan(economicPolicy.get("time"), time);
    }

    public static Specification<EconomicPolicy> hasTimeFrom(Integer time) {
        return (economicPolicy, cq, cb) -> time == null ? null : cb.greaterThan(economicPolicy.get("time"), time);
    }

    public static Specification<EconomicPolicy> hasGdpGrowthAnnualTo(Double gdpGrowthAnnual) {
        return (economicPolicy, cq, cb) -> gdpGrowthAnnual == null ? null : cb.lessThan(economicPolicy.get("gdpGrowthAnnual"), gdpGrowthAnnual);
    }

    public static Specification<EconomicPolicy> hasGdpGrowthAnnualFrom(Double gdpGrowthAnnual) {
        return (economicPolicy, cq, cb) -> gdpGrowthAnnual == null ? null : cb.greaterThan(economicPolicy.get("gdpGrowthAnnual"), gdpGrowthAnnual);
    }
}
