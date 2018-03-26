package com.adi.datamining.decisiontree.algorithm;

import com.adi.datamining.decisiontree.data.BaseRecord;
import com.adi.datamining.decisiontree.common.TreeNode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by wudi on 2016/1/22.
 */
public class DecisionTree {


    IAttrSelector selector;

    public DecisionTree(IAttrSelector selector) {
        this.selector = selector;
    }

    /**创建决策树*/
    public TreeNode createTree(List<BaseRecord> records, Set<Field> attrSet) {
        if(null == records || records.size() < 1)
            return null;
        TreeNode node = new TreeNode();
        //1.如果所有的记录分类属性值都相同，如果全部相同则直接返回分类属性值
        if(isAllInSameClass(records)){
            node.setAttrName(String.valueOf(records.get(0).getDecisionAttr()));
            return node;
        }
        //2.如果属性列表为空，统计记录集合中正负样例个数，正>负?true:false
        if(null == attrSet || 0 == attrSet.size()){
            node.setAttrName(String.valueOf(getMostClass(records)));
            return node;
        }
        //3.选择出来增益最大的属性
        // ID3
//        Field bestField = selector.selectID3(records,attrSet);

        // C45
        Field bestField = selector.selectC45(records,attrSet);
        //4.根据最好属性的值分为多个分支
        List<List<BaseRecord>> splitValues = splitRecords(records, bestField);
        List<TreeNode> children = new ArrayList<>(splitValues.size());
        attrSet.remove(bestField);
        //5.遍历子节点
        for (List<BaseRecord> recordList : splitValues) {
            children.add(createTree(recordList, attrSet));
        }
        node.setTreeNodeList(children);
        node.setAttrName(bestField.getName());
        return node;
    }

    /**根据属性的值分不同列表*/
    private List<List<BaseRecord>> splitRecords(List<BaseRecord> records, Field field) {
        List<List<BaseRecord>> result = new ArrayList<>();
        try {
            field.setAccessible(true);
            outerLoop :
            for(BaseRecord record : records) {
                Object value = field.get(record);
                for(List<BaseRecord> recordList : result) {
                    if(field.get(recordList.get(0)).equals(value)) {
                        recordList.add(record);
                        continue outerLoop;
                    }
                }
                List<BaseRecord> recordList = new ArrayList<>();
                recordList.add(record);
                result.add(recordList);
            }
        } catch (Exception ex) {
            System.out.println("method access exception");
        }

        return result;
    }

    /**根据列表中分类的正负样例个数决定叶子节点为true or false*/
    private Boolean getMostClass(List<BaseRecord> records) {
        int positCount = 0;
        int negatCount = 0;
        for(BaseRecord record : records) {
            if(record.getDecisionAttr())
                ++positCount;
            else
                ++negatCount;
        }
        return positCount > negatCount ? true : false;
    }

    /**判断所有记录是否具有相同的分类值*/
    private boolean isAllInSameClass(List<BaseRecord> records) {
        Boolean buyComp = records.get(0).getDecisionAttr();
        for(BaseRecord record : records) {
            if(!buyComp.equals(record.getDecisionAttr()))
                return false;
        }
        return true;
    }

}
