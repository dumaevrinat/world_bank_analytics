package com.firstline.worldbankanalytics.api.v1.controller;

import com.firstline.worldbankanalytics.api.v1.dto.EconomicPolicyDTO;
import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import com.firstline.worldbankanalytics.service.EconomicPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/economicPolicy")
public class EconomicPolicyController {

    private final EconomicPolicyService economicPolicyService;

    @Autowired
    public EconomicPolicyController(EconomicPolicyService economicPolicyService) {
        this.economicPolicyService = economicPolicyService;
    }

    @GetMapping(value = "/get")
    public EconomicPolicyDTO getEconomicPolicy(@RequestParam Long id) {
        EconomicPolicy economicPolicy = economicPolicyService.getEconomicPolicy(id);
        return new EconomicPolicyDTO(economicPolicy);
    }

    @PostMapping(value = "/add")
    public EconomicPolicyDTO addEconomicPolicy(@RequestBody EconomicPolicyDTO economicPolicy) {
        EconomicPolicy addedPolicy = economicPolicyService.addEconomicPolicy(economicPolicy.toEntity());
        return new EconomicPolicyDTO(addedPolicy);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<Object> deleteEconomicPolicy(@RequestParam Long id) {
        economicPolicyService.deleteEconomicPolicy(id);

        return ResponseEntity.ok().build();
    }

}
