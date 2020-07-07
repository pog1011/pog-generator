package com.pog.generator.entity;

import lombok.Data;

import java.util.List;

@Data
public class GeneratorEntity {

    private String databaseName;
    private List<TableEntity> tableList;
    private String author;
    private String email;
    private String packageName;
    private String moduleName;
    private String tablePrefix;

}
