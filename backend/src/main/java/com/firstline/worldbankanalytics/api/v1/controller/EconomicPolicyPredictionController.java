package com.firstline.worldbankanalytics.api.v1.controller;

import com.firstline.worldbankanalytics.api.v1.dto.EconomicPolicyPredictionDTO;
import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import com.firstline.worldbankanalytics.entity.EconomicPolicyPrediction;
import com.firstline.worldbankanalytics.service.EconomicPolicyPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/economicPolicyPrediction")
public class EconomicPolicyPredictionController {

    private final EconomicPolicyPredictionService economicPolicyPredictionService;

    @Autowired
    public EconomicPolicyPredictionController(EconomicPolicyPredictionService economicPolicyPredictionService) {
        this.economicPolicyPredictionService = economicPolicyPredictionService;
    }

    @GetMapping(value = "/get")
    public EconomicPolicyPredictionDTO getEconomicPolicy(@RequestParam Long id) {
        EconomicPolicyPrediction policy = economicPolicyPredictionService.getEconomicPolicyPrediction(id);
        return new EconomicPolicyPredictionDTO(policy);
    }

    @PostMapping(value = "/add")
    public EconomicPolicyPredictionDTO addEconomicPolicy(@RequestBody EconomicPolicyPredictionDTO policy) {
        EconomicPolicyPrediction addedPolicy = economicPolicyPredictionService.addEconomicPolicyPrediction(policy.toEntity());
        return new EconomicPolicyPredictionDTO(addedPolicy);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<Object> deleteEconomicPolicy(@RequestParam Long id) {
        economicPolicyPredictionService.deleteEconomicPolicyPrediction(id);

        return ResponseEntity.ok().build();
    }


}
