package com.ncu.service.impl;

import com.ncu.dao.interfaces.IPictureDAO;
import com.ncu.service.interfaces.IHousePictureRelSV;
import com.ncu.service.interfaces.IPictureSV;
import com.ncu.table.bean.PictureBean;
import com.ncu.table.ivalue.IHousePictureRelValue;
import com.ncu.table.ivalue.IPictureValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/31.
 */
@Repository("PictureSVImpl")
public class PictureSVImpl implements IPictureSV {
    @Resource(name="PictureDAOImpl")
    private IPictureDAO pictureDAO;

    @Resource(name="HousePictureRelSVImpl")
    private IHousePictureRelSV housePictureRelSV;
    /**
     * 根据照片的ID查找照片的信息
     * @param pictureId
     * @return
     * @throws Exception
     */
    public IPictureValue queryPictureInfoByPictureId(long pictureId) throws Exception {
        if(pictureId != 0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IPictureValue.S_PictureId,pictureId,condition,params,false);
            SQLCon.connectSQL(IPictureValue.S_DelFlag,1L,condition,params,false);
            List<IPictureValue> list =  pictureDAO.getPictureByCondition(condition.toString(),params,-1,-1);
            if(list !=null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(IPictureValue value) throws Exception {
        pictureDAO.save(value);
    }

    /**
     * 保存图片的定义表的信息
     * @param pictureName
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public long savePictureInfoByPictureName(String pictureName) throws Exception {
        if(StringUtils.isNotBlank(pictureName)){
            PictureBean bean = new PictureBean();
            bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            bean.setDelFlag(1L);
            bean.setPicturePath(pictureName);
            pictureDAO.save(bean);
            return bean.getPictureId();
        }
        return 0L;
    }

    /**
     * 查询房子的主照片
     * @param houseId
     * @throws Exception
     */
    public IPictureValue queryMainPictureByHouseId(long houseId) throws Exception{
        if(houseId > 0){
            IHousePictureRelValue housePictureRelValue = housePictureRelSV.queryMainHousePicture(houseId);
            if(housePictureRelValue != null){
                long pictureId = housePictureRelValue.getHousePictureId();
                IPictureValue pictureValue = queryPictureInfoByPictureId(pictureId);
                if (pictureValue != null) {
                    return pictureValue;
                }
            }
        }
        return null;
    }
}
