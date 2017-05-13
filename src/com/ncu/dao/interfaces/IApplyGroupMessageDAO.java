package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IApplyGroupMessageValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.HashMap;

/**
 * Created by zuowy on 2017/4/10.
 */
public interface IApplyGroupMessageDAO {
    public void save(IApplyGroupMessageValue value) throws Exception;
    public List<IApplyGroupMessageValue> queryApplyGroupMessageInfoByCondition(String condition, HashMap params,int begin,int end) throws Exception;
    public long queryApplyGroupMessageCountByCondition(String condition, HashMap params) throws Exception;
}
