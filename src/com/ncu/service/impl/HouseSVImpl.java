package com.ncu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncu.dao.interfaces.IHouseDAO;
import com.ncu.dao.interfaces.IHousePictureRelDAO;
import com.ncu.dao.interfaces.IPictureDAO;
import com.ncu.service.interfaces.IHouseSV;
import com.ncu.table.ivalue.IHousePictureRelValue;
import com.ncu.table.ivalue.IHouseValue;
import com.ncu.table.ivalue.IPictureValue;

@Service("HouseSVImpl")
public class HouseSVImpl implements IHouseSV{
	@Resource(name="HouseDAOImpl")
	private IHouseDAO houseDAO;
	@Resource(name="PictureDAOImpl")
	private IPictureDAO pictureDAO;
	@Resource(name="HousePictureRelDAOImpl")
	private IHousePictureRelDAO housePictureRelDAO;
	
	@Override
	public String createHtml(long houseId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHtml(int begin, int end) throws Exception {
		StringBuilder html = new StringBuilder("");
		//根据begin和end来查询房子的信息
		List<IHouseValue> list = houseDAO.getHouseInfoByCondition(null, null, begin, end);
		if(list != null && list.size()>0){
			int length = list.size();
			for(int i = 0;i<length;i++){
				IHouseValue value = list.get(i);
				long houseId = value.getHouseId();
				String houseName = value.getHouseName();
				String dsc = value.getHouseDsc();
				float houseMoney = value.getHouseMoney();
				long pictureId = 0;
				String picturePath = "";
				//根据houseId查询与主图片的ID
				IHousePictureRelValue relValue = housePictureRelDAO.getMainRelByHouseId(houseId);
				//根据图片ID查询图片的路径
				if(relValue != null){
					pictureId = relValue.getRelPictureId();
					
					IPictureValue pictureValue = pictureDAO.getPictureByPictureId(pictureId);
					if(pictureValue != null){
						picturePath = pictureValue.getPicturePath();
					}
				}
				//根据信息组装界面
				html.append("<div class=\"product\">");
				html.append("<div class=\"image\"><img src=\"showImage?imageFile=");
				html.append(picturePath);
				html.append("\"");
				html.append("class=\"img-responsive\"></div>");
				html.append("<font class=\"myFont\"><div class=\"dsc\">");
				html.append(dsc);
				html.append("</div><div class=\"money\">");
				html.append(houseMoney);
				html.append("</div></font></div>");
			}
		}
		return html.toString();
	}

}
