package com.pog.generator.service;

import com.pog.generator.common.QueryParams;
import com.pog.generator.common.ResultPage;
import com.pog.generator.dao.GeneratorDao;
import com.pog.generator.entity.ColumnEntity;
import com.pog.generator.entity.GeneratorEntity;
import com.pog.generator.entity.TableEntity;
import com.pog.generator.utils.GeneratorUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;


@Service
public class GeneratorService {

    @Autowired
    GeneratorDao generatorDao;

    public List<TableEntity> queryTableList(String databaseName){
        List<TableEntity> tableEntities = generatorDao.queryTable(databaseName);
        return tableEntities;
    }

    public List<String> fetchDatabaseName(){
        List<String> strings = generatorDao.queryDatabse();
        return strings;
    }

    public List<ColumnEntity> queryColumnList(String databaseName, String tableName){
        List<ColumnEntity> columnEntities = generatorDao.queryColumn(databaseName, tableName);
        return columnEntities;
    }

    public TableEntity queryTableInfo(String databaseName, String tableName){
        TableEntity tableEntity = generatorDao.queryTableInfo(databaseName, tableName);
        return tableEntity;
    }

    public byte[] generatorCode(GeneratorEntity generatorEntity){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

        String databaseName = generatorEntity.getDatabaseName();

        for (TableEntity tableEntity : generatorEntity.getTableList()) {

            List<ColumnEntity> columnEntityList = queryColumnList(databaseName, tableEntity.getTableName());

            GeneratorUtil.generatorCode(generatorEntity,tableEntity, columnEntityList, zipOutputStream);

        }

        IOUtils.closeQuietly(zipOutputStream);
        return byteArrayOutputStream.toByteArray();

    }

}
