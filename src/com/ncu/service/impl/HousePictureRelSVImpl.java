package com.ncu.service.impl;

import com.ncu.dao.interfaces.IHousePictureRelDAO;
import com.ncu.service.interfaces.IHousePictureRelSV;
import com.ncu.table.bean.HousePictureRelBean;
import com.ncu.table.ivalue.IHousePictureRelValue;
import com.ncu.util.SQLCon;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/31.
 */
@Service("HousePictureRelSVImpl")
public class HousePictureRelSVImpl implements IHousePictureRelSV {
    @Resource(name="HousePictureRelDAOImpl")
    private IHousePictureRelDAO housePictureRelDAO;


    /**
     * 根据房子的ID查询房子和图片的关系
     * @param houseId
     * @return
     * @throws Exception
     */
    public List<IHousePictureRelValue> queryHousePictureRelByHouseId(String houseId) throws Exception {
        if(StringUtils.isNotBlank(houseId)){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IHousePictureRelValue.S_HouseId,Long.parseLong(houseId),condition,params,false);
            return housePictureRelDAO.getHousePictureRelByCondition(condition.toString(),params,-1,-1);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(IHousePictureRelValue value) throws Exception {
        housePictureRelDAO.save(value);
    }


    /**
     * 查询房源的主图片
     * @param houseId
     * @return
     * @throws Exception
     */
    public List<IHousePictureRelValue> queryMainHousePicture(String houseId) throws Exception{
        if(StringUtils.isNotBlank(houseId)){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IHousePictureRelValue.S_HouseId,Long.parseLong(houseId),condition,params,false);
            SQLCon.connectSQL(IHousePictureRelValue.S_PictureType,"MAIN",condition,params,false);
            return housePictureRelDAO.getHousePictureRelByCondition(condition.toString(),params,-1,-1);
        }
        return null;
    }

    /**
     * 保存图片和用户的信息
     * @param houseId
     * @param pictureId
     * @param pictureType
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void savePictureRelByUserIdAndTypeAndPictureId(long houseId, long pictureId, String pictureType) throws Exception {
        if(houseId != -1){
            HousePictureRelBean bean = new HousePictureRelBean();
            bean.setHouseId(houseId);
            bean.setHousePictureId(pictureId);
            bean.setPictureType(pictureType);
            housePictureRelDAO.save(bean);
        }
    }
}
