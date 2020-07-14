package com.firstline.worldbankanalytics.service;

import com.firstline.worldbankanalytics.entity.EconomicPolicyPrediction;
import com.firstline.worldbankanalytics.exception.NoSuchEconomicPolicyPredictionException;
import com.firstline.worldbankanalytics.repository.EconomicPolicyPredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EconomicPolicyPredictionService {

    private final EconomicPolicyPredictionRepository economicPolicyPredictionRepository;

    @Autowired
    public EconomicPolicyPredictionService(EconomicPolicyPredictionRepository economicPolicyPredictionRepository) {
        this.economicPolicyPredictionRepository = economicPolicyPredictionRepository;
    }

    public EconomicPolicyPrediction getEconomicPolicyPrediction(Long id) {
        return economicPolicyPredictionRepository.findById(id).orElseThrow(NoSuchEconomicPolicyPredictionException::new);
    }

    public void deleteEconomicPolicyPrediction(Long id) {
        if (economicPolicyPredictionRepository.existsById(id)) {
            economicPolicyPredictionRepository.deleteById(id);
        }
    }

    public EconomicPolicyPrediction addEconomicPolicyPrediction(EconomicPolicyPrediction economicPolicyPrediction) {
        return economicPolicyPredictionRepository.save(economicPolicyPrediction);
    }
}
