package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IPictureValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/3/31.
 */
public interface IPictureSV {
    public void save(IPictureValue value)throws Exception;
    public IPictureValue queryPictureInfoByPictureId(long pictureId) throws Exception;

    /**
     * 保存图片的信息
     * @param pictureName
     * @throws Exception
     */
    public long savePictureInfoByPictureName(String pictureName) throws Exception ;

    /**
     * 查询房子的主照片
     * @param houseId
     * @throws Exception
     */
    public IPictureValue queryMainPictureByHouseId(long houseId) throws Exception;



    /**
     * 查询房子的图片信息
     * @param houseId
     * @param pictureType
     * @return 返回图片数组给页面
     * @throws Exception
     */
    public HashMap queryPictureListByHouseId(long houseId, String pictureType,int begin,int end)throws Exception;


}
