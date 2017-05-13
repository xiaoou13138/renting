package com.ncu.cache;

import com.ncu.service.interfaces.IStaticDataSV;
import com.ncu.table.ivalue.IStaticDataValue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuowy on 2017/3/27.
 */
@Service("StaticDataCache")
public class StaticDataCache {
    @Autowired
    @Qualifier("StaticDataSVImpl")
    private IStaticDataSV sv;

    @Cacheable(value="staticDataCache", key="#code")
    public List<IStaticDataValue> getStaticDataByCode(String code){
        try{
            System.out.println("开始读取缓存");
            return sv.queryStaticDataByCode(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 根据code从静态表里面获取codeValue
     * @param code
     * @return
     */
    public String getCodeValueByCode(String code){
        if(StringUtils.isNotBlank(code)){
            List<IStaticDataValue> list = getStaticDataByCode(code);
            if(list !=null &&list.size() == 1){
                return list.get(0).getCodeValue();
            }
        }
        return null;
    }

    /**
     * 根据code从静态表里边获取codeValue数组
     * @param code
     * @return
     */
    public String[] getCodeValueListByCode(String code){
        ArrayList<String> array = new ArrayList();
        if(StringUtils.isNotBlank(code)){
            List<IStaticDataValue> list = getStaticDataByCode(code);
            if(list !=null &&list.size() == 1){
                int length = 0;
                for(int i = 0;i<length;i++){
                    array.add(list.get(i).getCodeValue());
                }
            }
        }
        return array.toArray(new String[0]);
    }

    /**
     * 根据codeType查询一个codeName
     * @param code
     * @return
     */
    public String getCodeNameByCodeType(String code){
        if(StringUtils.isNotBlank(code)){
            List<IStaticDataValue> list = getStaticDataByCode(code);
            if(list !=null &&list.size() == 1){
                return list.get(0).getCodeName();
            }
        }
        return null;
    }
    public String getCodeNameByCodeTypeAndValue(String code,String value){
        List<IStaticDataValue> list = getStaticDataByCode(code);
        if(list !=null &&list.size() >0 ){
            if(StringUtils.isNotBlank(value)){
                for(int i = 0;i<list.size();i++){
                    if(value.equals(list.get(i).getCodeValue())){
                        return list.get(i).getCodeName();
                    }
                }
            }else{
                return list.get(0).getCodeName();
            }
        }
        return null;
    }
}
