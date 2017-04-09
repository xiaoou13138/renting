package com.ncu.service.interfaces;


import com.ncu.table.ivalue.IStaticDataValue;

import java.util.List;

/**
 * Created by xiaoou on 2017/3/27.
 */
public interface IStaticDataSV {
    public List<IStaticDataValue> queryStaticDataByCode(String code) throws Exception;
}
