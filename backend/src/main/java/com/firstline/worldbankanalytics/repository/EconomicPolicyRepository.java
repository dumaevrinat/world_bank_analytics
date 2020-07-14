package com.firstline.worldbankanalytics.repository;

import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomicPolicyRepository extends JpaRepository<EconomicPolicy, Long> {
}
