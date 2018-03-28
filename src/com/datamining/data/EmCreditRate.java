
package com.datamining.data;

/**
 * Created by wudi10 on 2016/1/22.
 */

public enum  EmCreditRate {

    EXCELLENT(1, "优秀"),
    FAIR(2,"正常");

    private final Integer level;
    private final String desc;
    private EmCreditRate(Integer level, String desc) {this.level = level;this.desc = desc;}

    public Integer getLevel(){return this.level;}

}