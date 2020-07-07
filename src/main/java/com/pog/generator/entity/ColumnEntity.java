package com.pog.generator.entity;


import lombok.Data;

import java.io.Serializable;


public class ColumnEntity implements Serializable {

    // 每列的名字
    private String columnName;
    // 数据类型
    private String dataType;
    // 数据属性 eg: PRI UNI
    private String columnKey;
    // 列的注释
    private String comment;
    // 转化为java实例名 eg: user_name = UserName
    private String entityName;
    //转化为java实例名 eg: user_name = userName
    private String entityname;
    // 转化为java的类型
    private String entityType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityname() {
        return entityname;
    }

    public void setEntityname(String entityname) {
        this.entityname = entityname;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
