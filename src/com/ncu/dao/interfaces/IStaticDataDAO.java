package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IStaticDataValue;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/3/27.
 */
public interface IStaticDataDAO {
    public List<IStaticDataValue> queryStaticDataInfoByCondition(String condition, HashMap params, int begin, int end) throws  Exception;
}
