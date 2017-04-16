package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IHouseCollectValue;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by xiaoou on 2017/4/14.
 */
public interface IHouseCollectSV {
    /**
     * 根据用户ID和房子ID保存收藏信息
     * @param userId
     * @param houseId
     * @throws Exception
     */
    public void saveCollectInfoByUserIdAndHouseId(long userId,long houseId) throws Exception;

    /**
     * 查询收藏的信息
     * @param userId
     * @param houseId
     * @return
     * @throws Exception
     */
    public IHouseCollectValue queryCollectInfoByUserIdAndHouseId(long userId, long houseId) throws Exception;

    /**
     * 删除收藏
     * @param userId
     * @param houseId
     * @throws Exception
     */
    public void deleteHouseCollectByUserIdAndHouseId(long userId, long houseId) throws Exception;

    /**
     * 查询用户收藏的房子
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IHouseCollectValue> queryCollectHouseByUserId(long userId,int begin,int end) throws Exception;
    public long queryCollectHouseCountByUserId(long userId) throws Exception;
}
