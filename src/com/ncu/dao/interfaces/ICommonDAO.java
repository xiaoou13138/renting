package com.ncu.dao.interfaces;

import com.ncu.table.bean.ParamsDefine;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/23.
 */
public interface ICommonDAO {
    public List commonQuery(final String sql, ParamsDefine[] paramsDefine) throws Exception;
    public List commonQuery(final String sql, ParamsDefine[] paramsDefine,int begin,int end) throws Exception ;
    public long getCount(final String sql, ParamsDefine[] paramsDefine) throws Exception ;
}
