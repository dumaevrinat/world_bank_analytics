package com.firstline.worldbankanalytics.repository;

import com.firstline.worldbankanalytics.entity.EconomicPolicyPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomicPolicyPredictionRepository extends JpaRepository<EconomicPolicyPrediction, Long> {
}
