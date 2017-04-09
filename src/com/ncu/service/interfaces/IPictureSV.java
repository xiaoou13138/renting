package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IPictureValue;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/31.
 */
public interface IPictureSV {
    public void save(IPictureValue value)throws Exception;
    public IPictureValue queryPictureInfoByPictureId(String pictureId) throws Exception;

    /**
     * 保存图片的信息
     * @param pictureName
     * @throws Exception
     */
    public long savePictureInfoByPictureName(String pictureName) throws Exception ;
}