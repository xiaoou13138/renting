package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IHousePictureRelValue;
import net.sf.json.JSONArray;

import java.util.List;
/**
 * Created by zuowy on 2017/3/31.
 */
public interface IHousePictureRelSV {
    public List<IHousePictureRelValue> queryHousePictureRelByHouseId(long houseId) throws  Exception;
    public void save(IHousePictureRelValue value) throws Exception;
    public IHousePictureRelValue queryMainHousePicture(long houseId) throws Exception;
    public void savePictureRelByUserIdAndTypeAndPictureId(long houseId,long pictureId,String pictureTypr) throws Exception;

    /**
     * 查询房子和照片的信息
     * @param houseId 房子主键
     * @param pictureType 照片类型
     * @return
     * @throws Exception
     */
    public List<IHousePictureRelValue> queryHousePictureRelByHouseIdAndType(long houseId,String pictureType,int begin,int end)throws Exception;
    public long queryHousePictureRelCountByHouseIdAndType(long houseId,String pictureType)throws Exception;

    /**
     * 查询图片关联信息
     * @param houseId
     * @param pictureId
     * @return
     * @throws Exception
     */
    public IHousePictureRelValue queryHousePictureRelByHouseIdAndPictureId(long houseId,long pictureId)throws Exception;
    /**
     * 删除图片
     * @param houseId
     * @param pictureList
     * @throws Exception
     */
    public void deleteHousePictureRel(long houseId,JSONArray pictureList)throws Exception;

}
