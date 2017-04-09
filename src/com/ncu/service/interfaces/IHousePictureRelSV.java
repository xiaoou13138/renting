package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IHousePictureRelValue;
import java.util.List;
/**
 * Created by xiaoou on 2017/3/31.
 */
public interface IHousePictureRelSV {
    public List<IHousePictureRelValue> queryHousePictureRelByHouseId(String houseId) throws  Exception;
    public void save(IHousePictureRelValue value) throws Exception;
    public List<IHousePictureRelValue> queryMainHousePicture(String houseId) throws Exception;
    public void savePictureRelByUserIdAndTypeAndPictureId(long houseId,long pictureId,String pictureTypr) throws Exception;
}
