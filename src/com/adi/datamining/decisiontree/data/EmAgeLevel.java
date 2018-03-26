package com.adi.datamining.decisiontree.data;

/**
 * Created by wudi10 on 2016/1/22.
 */
public enum EmAgeLevel {

    SENIOR(1, "高龄人"),
    MIDDLE_AGED(2,"中龄人"),
    YOUTH(3,"年轻人");

    private final Integer level;
    private final String desc;
    private EmAgeLevel(Integer level, String desc) {this.level = level;this.desc = desc;}

    public Integer getLevel(){return this.level;}
}
