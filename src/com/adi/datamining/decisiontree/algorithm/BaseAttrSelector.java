package com.adi.datamining.decisiontree.algorithm;

import com.adi.datamining.decisiontree.data.BaseRecord;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by wudi10 on 2016/1/23.
 */
public class BaseAttrSelector implements IAttrSelector{
    /**
     * 通过记录集合与记录的属性集合，挑选出属性中增益度最大的属性
     * */
    @Override
    public Field selectID3(List<BaseRecord> records, Set<Field> atrrs){
        Field bestField = null;
        Double highestScore = 0D;
        Double setInfo = entropy(records);
        for(Field field : atrrs) {
            Double gainScore = setInfo - infoScore( records, field);
            if(gainScore > highestScore) {
                highestScore = gainScore;
                bestField = field;
            }
        }
        return bestField;
    }

    @Override
    public Field selectC45(List<BaseRecord> records, Set<Field> atrrs) {
        Field bestField = null;
        Double highestGainRate = 0D;
        Double averageScore = 0D;
        int passedScoreNum = 0;
        Double setInfo = entropy(records);
        for(Field field : atrrs) {
            Double gainScore = setInfo - infoScore( records, field);
            Double gainRate = gainScore / splitInfo(records, field);
            if(gainScore > averageScore && gainRate > highestGainRate) {
                highestGainRate = gainRate;
                bestField = field;

            }
            averageScore = (averageScore * passedScoreNum++ + gainScore) / passedScoreNum;
        }
        return bestField;
    }

    private Double splitInfo(List<BaseRecord> records, Field field) {
        double splitInfo = 0D;
        try {
            //1.求该属性每个值对于分类的正负样例个数，即有多少是true，多少个false;
            //key:存放该属性不同值,value:长度为2，存放该属性值对分类正负样例数
            Map<Object,List<Integer>> count4Values = new HashMap<Object,List<Integer>>();
            Integer size = records.size();
            field.setAccessible(true);
            for(BaseRecord record : records) {
                Object attrValue = field.get(record);
                List<Integer> countList = count4Values.get(attrValue);
                if(countList == null) {
                    countList = new ArrayList<Integer>(2);
                    countList.add(0,0);
                    countList.add(1,0);
                }
                if(record.getDecisionAttr()){
                    countList.set(0,countList.get(0) + 1);
                } else {
                    countList.set(1,countList.get(1) + 1);
                }
                count4Values.put(attrValue, countList);
            }

            //2.遍历map算出期望值
            for(Object key : count4Values.keySet()) {
                List<Integer> countList = count4Values.get(key);
                double positCount = countList.get(0);
                double negatCount = countList.get(1);
                if(positCount == 0 || negatCount == 0) //对于正负样例个数为0的情况，视为无效，对分类影响最大，分数为0;
                    continue;
                double valueCount = positCount + negatCount;
                splitInfo += valueCount/size * ( - log2N(valueCount / valueCount));
            }

        } catch (Exception ex) {
            System.out.println("method access exception");
        }
        return splitInfo;
    }

    /**
     * 根据记录列表求关于所求类的熵，此方法中要分的类是DecisionAttr
     * */
    private Double entropy(List<BaseRecord> records) {
        Double positCount = 0D;
        Double negatCount = 0D;
        for(BaseRecord record : records) {
            if(record.getDecisionAttr())
                ++positCount;
            else
                ++negatCount;
        }
        return - positCount/records.size()* log2N(positCount / records.size())
                - negatCount/records.size()* log2N(negatCount / records.size());

    }

    /**
     * log2(N), log 以2为底N的对数
     * */
    private Double log2N(Double d) {
        return Math.log(d) / Math.log(2.0);
    }

    /**求某个属性对于分类DecisionAttr的期望分数，公式见<数据挖掘概念与技术>中决策树那节*/
    private Double infoScore(List<BaseRecord> records, Field field) {
        Double infoScore = 0D;
        try {
            //1.求该属性每个值对于分类的正负样例个数，即有多少是true，多少个false;
            //key:存放该属性不同值,value:长度为2，存放该属性值对分类正负样例数
            Map<Object,List<Integer>> count4Values = new HashMap<Object,List<Integer>>();
            Integer size = records.size();
            field.setAccessible(true);
            for(BaseRecord record : records) {
                Object attrValue = field.get(record);
                List<Integer> countList = count4Values.get(attrValue);
                if(countList == null) {
                    countList = new ArrayList<Integer>(2);
                    countList.add(0,0);
                    countList.add(1,0);
                }
                if(record.getDecisionAttr()){
                    countList.set(0,countList.get(0) + 1);
                } else {
                    countList.set(1,countList.get(1) + 1);
                }
                count4Values.put(attrValue, countList);
            }

            //2.遍历map算出期望值
            for(Object key : count4Values.keySet()) {
                List<Integer> countList = count4Values.get(key);
                double positCount = countList.get(0);
                double negatCount = countList.get(1);
                if(positCount == 0 || negatCount == 0) //对于正负样例个数为0的情况，视为无效，对分类影响最大，分数为0;
                    continue;
                double valueCount = positCount + negatCount;
                infoScore += valueCount/size * ( - (positCount/valueCount) * log2N(positCount / valueCount)
                        - (negatCount/valueCount) * log2N(negatCount/valueCount));
            }

        } catch (Exception ex) {
            System.out.println("method access exception");
        }
        return infoScore;

    }

}
