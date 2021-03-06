package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IHouseSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by zuowy on 2017/5/5.
 */
@Controller
@Scope("prototype")
public class AdminHouseManageController extends BaseController {
    @Resource(name="HouseSVImpl")
    private IHouseSV houseSV;

    @RequestMapping(value="/adminHouseManage_dealAction",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            String userType = getStringParamFromSession("userType");
            if(!"admin".equals(userType)){
                throw new Exception("用户不是管理员");
            }

            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            int actionType = viewObject.getInt("actionType");
            if(actionType ==  1 ){//获取用户表格信息
                long houseId = viewObject.getLong("houseId");
                if(houseId <= 0){
                    throw new Exception("房子主键不合法");
                }
                houseSV.deleteHouseByHouseId(houseId);
            }
        }catch (Exception e){
            e.printStackTrace();
            rtnJSONObject.put("errMessage",e.getMessage());
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }
}
