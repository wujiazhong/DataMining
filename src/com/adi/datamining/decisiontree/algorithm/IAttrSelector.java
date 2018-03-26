package com.adi.datamining.decisiontree.algorithm;

import com.adi.datamining.decisiontree.data.BaseRecord;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * Created by wudi on 2016/1/23.
 */
public interface IAttrSelector {
    Field selectID3(List<BaseRecord> records, Set<Field> atrrs);
    Field selectC45(List<BaseRecord> records, Set<Field> atrrs);
}
