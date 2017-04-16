package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IPostPictureRelValue;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/12.
 */
public interface IPostPictureRelSV {
    /**
     * 根据用户的ID和帖子主键和图片列表保存信息
     * @param postId
     * @param pictureList
     * @throws Exception
     */
    public void savePostPictureRelForController(long postId, ArrayList pictureList) throws Exception;

    /**
     * 根据帖子的ID帖子关联的图片
     * @param postId
     * @return
     * @throws Exception
     */
    public List<IPostPictureRelValue> queryPostPictureRelInfoByPostId(long postId) throws Exception;
}
