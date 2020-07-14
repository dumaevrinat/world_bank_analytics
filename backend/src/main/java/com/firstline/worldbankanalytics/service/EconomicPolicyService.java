package com.firstline.worldbankanalytics.service;

import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import com.firstline.worldbankanalytics.exception.NoSuchEconomicPolicyException;
import com.firstline.worldbankanalytics.repository.EconomicPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EconomicPolicyService {
    private final EconomicPolicyRepository economicPolicyRepository;

    @Autowired
    public EconomicPolicyService(EconomicPolicyRepository economicPolicyRepository) {
        this.economicPolicyRepository = economicPolicyRepository;
    }

    public EconomicPolicy getEconomicPolicy(Long id) {
        return economicPolicyRepository.findById(id).orElseThrow(NoSuchEconomicPolicyException::new);
    }

    public void deleteEconomicPolicy(Long id) {
        if (economicPolicyRepository.existsById(id)) {
            economicPolicyRepository.deleteById(id);
        }
    }

    public EconomicPolicy addEconomicPolicy(EconomicPolicy economicPolicy) {
        return economicPolicyRepository.save(economicPolicy);
    }
}
