package com.datamining.bayes.algorithm;

import com.datamining.data.BaseRecord;
import com.datamining.data.HumanAttrRecord;

import java.lang.reflect.Field;
import java.util.*;

public class DataInfo {
    private Map<Boolean, Integer> result;
    private Map<Field, Map<Object, Map<Boolean, Integer>>> posteriorProbability = new HashMap<>();

    public DataInfo(List<BaseRecord> records) {
        result = getAttrResult(records);

        Field[] fields = HumanAttrRecord.class.getDeclaredFields();
        for (Field field : fields) {
            Map<Object, Map<Boolean, Integer>> count4Values = new HashMap<>();
            int tmp;
            field.setAccessible(true);
            for (BaseRecord record : records) {
                try {
                    Object value = field.get(record);
                    if (!count4Values.keySet().contains(value)) {
                        Map<Boolean, Integer> map = new HashMap<>();
                        map.put(true, 0);
                        map.put(false, 0);
                        count4Values.put(value, map);
                    }
                    Map<Boolean, Integer> fieldMap = count4Values.get(value);
                    tmp = record.getDecisionAttr() ? fieldMap.get(true) : fieldMap.get(false);
                    fieldMap.put(record.getDecisionAttr(), ++tmp);
                    count4Values.put(value, fieldMap);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            posteriorProbability.put(field, count4Values);
        }

    }

    public boolean test(HumanAttrRecord toTestRecord) {
        Field[] fields = HumanAttrRecord.class.getDeclaredFields();
        double posProb = 1D;
        double negProb = 1D;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(toTestRecord);
                Map<Object, Map<Boolean, Integer>> count4Values = posteriorProbability.get(field);

                int fieldPosCount = count4Values.get(value).get(true);
                int totPosCount = result.get(true);
                posProb *= ((double)fieldPosCount / (double)totPosCount);

                int fieldNegCount = count4Values.get(value).get(false);
                int totNegCount = result.get(false);
                negProb *= ((double)fieldNegCount / (double)totNegCount);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return posProb >= negProb;
    }


    private Map<Boolean, Integer> getAttrResult(List<BaseRecord> records) {
        Map<Boolean, Integer> result = new TreeMap<>();
        int positive = 0;
        int negative = 0;
        for (BaseRecord record : records) {
            if (record.getDecisionAttr()) positive++;
            else negative++;
        }
        result.put(true, positive);
        result.put(false, negative);

        return result;
    }
}
