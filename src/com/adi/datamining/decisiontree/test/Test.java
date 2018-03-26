package com.adi.datamining.decisiontree.test;

import com.adi.datamining.decisiontree.algorithm.BaseAttrSelector;
import com.adi.datamining.decisiontree.algorithm.DecisionTree;
import com.adi.datamining.decisiontree.algorithm.IAttrSelector;
import com.adi.datamining.decisiontree.common.TreeNode;
import com.adi.datamining.decisiontree.data.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wudi on 2016/1/23.
 */
public class Test {
    public static void main(String[] arr) {
        List<BaseRecord> records = new ArrayList<BaseRecord>();
        HumanAttrRecord record0 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.HIGH,false, EmCreditRate.FAIR,false);
        HumanAttrRecord record1 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.HIGH,false, EmCreditRate.EXCELLENT,false);
        HumanAttrRecord record2 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmIncome.HIGH,false, EmCreditRate.FAIR,true);
        HumanAttrRecord record3 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.MEDIUM,false, EmCreditRate.FAIR,true);
        HumanAttrRecord record4 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,true, EmCreditRate.FAIR,true);
        HumanAttrRecord record5 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,true, EmCreditRate.EXCELLENT,false);
        HumanAttrRecord record6 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmIncome.LOW,true, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record7 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,false, EmCreditRate.FAIR,false);
        HumanAttrRecord record8 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.LOW,true, EmCreditRate.FAIR,true);
        HumanAttrRecord record9 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.MEDIUM,true, EmCreditRate.FAIR,true);
        HumanAttrRecord record10 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,true, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record11 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmIncome.MEDIUM,false, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record12 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmIncome.HIGH,true, EmCreditRate.FAIR,true);
        HumanAttrRecord record13 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.EXCELLENT,false);
//        HumanAttrRecord record14 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,false, EmCreditRate.FAIR,false);
//        HumanAttrRecord record15 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmIncome.MEDIUM,true, EmCreditRate.EXCELLENT,true);
//        HumanAttrRecord record16 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.EXCELLENT,true);
//        HumanAttrRecord record17 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.HIGH,true, EmCreditRate.EXCELLENT,true);
//        HumanAttrRecord record18 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,false, EmCreditRate.EXCELLENT,false);
//        HumanAttrRecord record19 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.FAIR,false);

        records.add(record0);
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);
        records.add(record6);
        records.add(record7);
        records.add(record8);
        records.add(record9);
        records.add(record10);
        records.add(record11);
        records.add(record12);
        records.add(record13);
//        records.add(record14);
//        records.add(record15);
//        records.add(record16);
//        records.add(record17);
//        records.add(record18);
//        records.add(record19);


        Set<Field> fieldSet = new HashSet<Field>();
        Field[] fields = HumanAttrRecord.class.getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().equals("decisionAttr")) continue;
            fieldSet.add(field);
        }

        IAttrSelector selector = new BaseAttrSelector();
        DecisionTree decisionTree = new DecisionTree(selector);
        TreeNode root = decisionTree.createTree(records,fieldSet);
        if(null != root) {
            root.print(0);
        }
    }
}
