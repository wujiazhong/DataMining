package com.datamining.data;

/**
 * Created by wudi on 2016/1/23.
 */
public class BaseRecord {

    private Boolean decisionAttr;

    public BaseRecord(Boolean decisionAttr) {
        this.decisionAttr = decisionAttr;
    }

    public Boolean getDecisionAttr() {
        return decisionAttr;
    }

    public void setDecisionAttr(Boolean decisionAttr) {
        this.decisionAttr = decisionAttr;
    }
}
