package com.datamining.bayes.test;

import com.datamining.bayes.algorithm.DataInfo;
import com.datamining.data.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] arr) {
        List<BaseRecord> trainingRecords = new ArrayList<>();
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
        HumanAttrRecord record13 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.MEDIUM,false, EmCreditRate.EXCELLENT,false);

        List<BaseRecord> testRecords = new ArrayList<>();
        HumanAttrRecord record14 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.HIGH,false, EmCreditRate.FAIR,false);
        HumanAttrRecord record15 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmIncome.MEDIUM,true, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record16 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record17 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.HIGH,true, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record18 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,false, EmCreditRate.EXCELLENT,false);
        HumanAttrRecord record19 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.FAIR,false);
        HumanAttrRecord record20 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,true, EmCreditRate.FAIR,false);

        trainingRecords.add(record0);
        trainingRecords.add(record1);
        trainingRecords.add(record2);
        trainingRecords.add(record3);
        trainingRecords.add(record4);
        trainingRecords.add(record5);
        trainingRecords.add(record6);
        trainingRecords.add(record7);
        trainingRecords.add(record8);
        trainingRecords.add(record9);
        trainingRecords.add(record10);
        trainingRecords.add(record11);
        trainingRecords.add(record12);
        trainingRecords.add(record13);
        testRecords.add(record14);
        testRecords.add(record15);
        testRecords.add(record16);
        testRecords.add(record17);
        testRecords.add(record18);
        testRecords.add(record19);
        testRecords.add(record20);

        DataInfo info = new DataInfo(trainingRecords);
        for (BaseRecord record : testRecords) {
            System.out.println(info.test((HumanAttrRecord)record));
        }

    }
}
