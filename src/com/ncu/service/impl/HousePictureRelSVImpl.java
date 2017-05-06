package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.dao.interfaces.IHousePictureRelDAO;
import com.ncu.service.interfaces.IHousePictureRelSV;
import com.ncu.table.bean.HousePictureRelBean;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.ivalue.IHousePictureRelValue;
import com.ncu.util.SQLCon;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/31.
 */
@Service("HousePictureRelSVImpl")
public class HousePictureRelSVImpl implements IHousePictureRelSV {
    @Resource(name="HousePictureRelDAOImpl")
    private IHousePictureRelDAO housePictureRelDAO;

    @Resource(name="CommonDAOImpl")
    private ICommonDAO commonDAO;


    /**
     * 根据房子的ID查询房子和图片的关系
     * @param houseId
     * @return
     * @throws Exception
     */
    public List<IHousePictureRelValue> queryHousePictureRelByHouseId(long houseId) throws Exception {
        if(houseId >0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IHousePictureRelValue.S_HouseId,houseId,condition,params,false);
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
    public IHousePictureRelValue queryMainHousePicture(long houseId) throws Exception{
        if(houseId >0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IHousePictureRelValue.S_HouseId,houseId,condition,params,false);
            SQLCon.connectSQL(IHousePictureRelValue.S_PictureType,"MAIN",condition,params,false);
            List<IHousePictureRelValue> list = housePictureRelDAO.getHousePictureRelByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size()>0){
                return list.get(0);
            }

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

    /**
     * 查询房子和照片的信息
     * @param houseId 房子主键
     * @param pictureType 照片类型
     * @return
     * @throws Exception
     */
    public List<IHousePictureRelValue> queryHousePictureRelByHouseIdAndType(long houseId,String pictureType,int begin,int end)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IHousePictureRelValue.S_HouseId,houseId,condition,params,false);
        if(StringUtils.isNotBlank(pictureType)){
            SQLCon.connectSQL(IHousePictureRelValue.S_PictureType,pictureType,condition,params,false);
        }
        return  housePictureRelDAO.getHousePictureRelByCondition(condition.toString(),params,-1,-1);
    }
    public long queryHousePictureRelCountByHouseIdAndType(long houseId,String pictureType)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IHousePictureRelValue.S_HouseId,houseId,condition,params,false);
        if(StringUtils.isNotBlank(pictureType)){
            SQLCon.connectSQL(IHousePictureRelValue.S_PictureType,pictureType,condition,params,false);
        }
        return  housePictureRelDAO.getHousePictureRelCountByCondition(condition.toString(),params);
    }

    /**
     * 查询图片关联信息
     * @param houseId
     * @param pictureId
     * @return
     * @throws Exception
     */
    public IHousePictureRelValue queryHousePictureRelByHouseIdAndPictureId(long houseId,long pictureId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IHousePictureRelValue.S_HouseId,houseId,condition,params,false);
        SQLCon.connectSQL(IHousePictureRelValue.S_HousePictureId,pictureId,condition,params,false);
        List<IHousePictureRelValue> list = housePictureRelDAO.getHousePictureRelByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 删除图片
     * @param houseId
     * @param pictureListIn
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteHousePictureRel(long houseId,JSONArray pictureListIn)throws Exception{
        ArrayList pictureList =  new ArrayList();
        for(int i = 0 ;i<pictureListIn.size();i++){
            Long pictureId = pictureListIn.getLong(i);
            pictureList.add(pictureId);
        }
        String sql = "from HousePictureRelBean where houseId=:houseId and housePictureId in(:pictureIdList) ";
        ArrayList<ParamsDefine> paramsDefines = new ArrayList<>();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setColName("houseId");
        paramsDefine.setParamVal(houseId);
        paramsDefine.setIsList(false);
        paramsDefines.add(paramsDefine);

        ParamsDefine paramsDefinePicture = new ParamsDefine();
        paramsDefinePicture.setColName("pictureIdList");
        paramsDefinePicture.setParamListVal(pictureList);
        paramsDefinePicture.setIsList(true);
        paramsDefines.add(paramsDefinePicture);
        List<IHousePictureRelValue> list = commonDAO.commonQuery(sql,paramsDefines.toArray(new ParamsDefine[0]));
        /*if(list != null && list.size()>0){
            int length = list.size();
            for(int i = 0;i<length;i++){
                housePictureRelDAO.deleteHousePictureRel(list.get(i));
            }
        }*/
    }
}
