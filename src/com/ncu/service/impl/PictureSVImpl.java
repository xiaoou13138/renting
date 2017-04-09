package com.ncu.service.impl;

import com.ncu.dao.interfaces.IPictureDAO;
import com.ncu.service.interfaces.IPictureSV;
import com.ncu.table.bean.PictureBean;
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

    /**
     * 根据照片的ID查找照片的信息
     * @param pictureId
     * @return
     * @throws Exception
     */
    public IPictureValue queryPictureInfoByPictureId(String pictureId) throws Exception {
        if(StringUtils.isNotBlank(pictureId)){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IPictureValue.S_PictureId,Long.parseLong(pictureId),condition,params,false);
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
}
