package com.ncu.service.impl;

import com.ncu.dao.interfaces.IStaticDataDAO;
import com.ncu.service.interfaces.IStaticDataSV;
import com.ncu.table.ivalue.IStaticDataValue;
import com.ncu.util.SQLCon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/27.
 */
@Service("StaticDataSVImpl")
public class StaticDataSVImpl implements IStaticDataSV {
    @Autowired
    @Qualifier("StaticDataDAOImpl")
    private IStaticDataDAO dao;
    @Override
    public List<IStaticDataValue> queryStaticDataByCode(String code) throws Exception {
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IStaticDataValue.S_CodeType,code,condition,params,false);
        SQLCon.connectSQL(IStaticDataValue.S_DelFlag,"1",condition,params,false);
        return dao.queryStaticDataInfoByCondition(condition.toString(),params,-1,-1);

    }
}
