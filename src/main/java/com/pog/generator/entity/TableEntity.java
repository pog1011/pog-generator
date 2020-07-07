package com.pog.generator.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;


public class TableEntity implements Serializable {

    // 表的名字
    private String tableName;
    // 表的注释
    private String comment;
    // 表的主键
    private ColumnEntity primaryKey;
    // 转化java类名 user_info = UserInfo
    private String className;
    // 转化java类名 user_info = userInfo
    private String classname;
    // 表的列
    private List<ColumnEntity> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ColumnEntity getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnEntity primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<ColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }
}
