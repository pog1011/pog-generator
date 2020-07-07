package com.pog.generator.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pog.generator.entity.ColumnEntity;
import com.pog.generator.entity.TableEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GeneratorDao{

    List<TableEntity> queryTable(@Param("databaseName")String databaseName);

    TableEntity queryTableInfo(@Param("databaseName")String databaseName, @Param("tableName")String tableName);

    List<ColumnEntity> queryColumn(@Param("databaseName")String databaseName, @Param("tableName")String tableName);

    List<String> queryDatabse();

}
