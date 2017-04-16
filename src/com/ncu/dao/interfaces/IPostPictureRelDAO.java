package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IPostPictureRelValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/12.
 */
public interface IPostPictureRelDAO {
    public void save(IPostPictureRelValue value) throws Exception;
    public List<IPostPictureRelValue> queryPostPicttureRelInfoByCondition(String condition, HashMap params,int begin,int end) throws Exception;
}
