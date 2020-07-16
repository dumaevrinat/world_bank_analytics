package com.firstline.worldbankanalytics.service;

import com.firstline.worldbankanalytics.entity.EconomicPolicy;
import com.firstline.worldbankanalytics.exception.NoSuchEconomicPolicyException;
import com.firstline.worldbankanalytics.repository.EconomicPolicyFilter;
import com.firstline.worldbankanalytics.repository.EconomicPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.firstline.worldbankanalytics.repository.EconomicPolicySpecifications.*;

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

    public List<EconomicPolicy> getEconomicPolicy(EconomicPolicyFilter filter) {
        if (filter == null) {
            filter = new EconomicPolicyFilter();
        }

        Specification<EconomicPolicy> specification = Specification
                .where(hasCountryName(filter.getCountryName()))
                .and(hasTimeFrom(filter.getTimeFrom()))
                .and(hasTimeTo(filter.getTimeTo()))
                .and(hasGdpGrowthAnnualFrom(filter.getGdpGrowthAnnualFrom()))
                .and(hasGdpGrowthAnnualTo(filter.getGdpGrowthAnnualTo()));

        return economicPolicyRepository.findAll(specification);
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
