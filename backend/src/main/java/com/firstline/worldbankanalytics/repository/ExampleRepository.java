package com.firstline.worldbankanalytics.repository;

import com.firstline.worldbankanalytics.entity.ExampleEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleRepository {

//    private final JdbcTemplate jdbcTemplate;
//
//    public ExampleRepository(@Qualifier("MySQLJdbcTemplate") JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public ExampleEntity getEntityById(long id) {
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.setId(1);
        exampleEntity.setExampleName("exampleName");
        
        return exampleEntity;
    }


}
