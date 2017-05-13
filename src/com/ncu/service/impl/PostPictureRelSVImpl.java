package com.ncu.service.impl;

import com.ncu.dao.interfaces.IPostPictureRelDAO;
import com.ncu.service.interfaces.IPostPictureRelSV;
import com.ncu.table.bean.PostPictureRelBean;
import com.ncu.table.ivalue.IPostPictureRelValue;
import com.ncu.table.ivalue.IPostValue;
import com.ncu.util.SQLCon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/4/12.
 */
@Service("PostPictureRelSVImpl")
public class PostPictureRelSVImpl implements IPostPictureRelSV {
    @Resource(name="PostPictureRelDAOImpl")
    IPostPictureRelDAO postPictureRelDAO;

    /**
     * 根据用户的ID和帖子主键和图片列表保存信息
     * @param pictureList
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void savePostPictureRelForController(long postId, ArrayList pictureList) throws Exception{
        if(postId !=0 && pictureList!= null){
            int length = pictureList.size();
            for(int i= 0;i<length;i++){
                PostPictureRelBean bean = new PostPictureRelBean();
                bean.setPostId(postId);
                bean.setPostPictureId((long)pictureList.get(i));
                postPictureRelDAO.save(bean);
            }
        }
    }

    /**
     * 根据帖子的ID帖子关联的图片
     * @param postId
     * @return
     * @throws Exception
     */
    public List<IPostPictureRelValue> queryPostPictureRelInfoByPostId(long postId) throws Exception{
        if(postId > 0){
            StringBuilder condition = new StringBuilder("");
            HashMap params = new HashMap<>();
            SQLCon.connectSQL(IPostPictureRelValue.S_PostId, postId, condition, params, false);
            return postPictureRelDAO.queryPostPicttureRelInfoByCondition(condition.toString(),params,-1,-1);
        }
        return null;
    }
}
