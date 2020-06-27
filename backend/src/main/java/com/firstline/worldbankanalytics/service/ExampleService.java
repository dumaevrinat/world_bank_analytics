package com.firstline.worldbankanalytics.service;

import com.firstline.worldbankanalytics.api.v1.dto.ExampleDto;
import com.firstline.worldbankanalytics.entity.ExampleEntity;
import com.firstline.worldbankanalytics.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {
    private final ExampleRepository exampleRepository;

    @Autowired
    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public ExampleDto getExample(long id){
        ExampleEntity exampleEntity = exampleRepository.getEntityById(id);

        ExampleDto exampleDto = new ExampleDto();

        exampleDto.setId(exampleEntity.getId());
        exampleDto.setExampleName(exampleEntity.getExampleName());

        return exampleDto;
    }
}
