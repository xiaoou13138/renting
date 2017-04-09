package com.ncu.dao.interfaces;

import com.ncu.table.bean.ParamsDefine;

import java.util.List;

/**
 * Created by xiaoou on 2017/3/23.
 */
public interface ICommonDAO {
    public List commonQuery(final String sql, ParamsDefine[] paramsDefine) throws Exception;
}
