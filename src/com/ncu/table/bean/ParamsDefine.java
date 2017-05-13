package com.ncu.table.bean;
import java.util.List;

/**
 * Created by zuowy on 2017/3/25.
 */
public class ParamsDefine {
    private String colName;
    private boolean isList;
    private List paramListVal;
    private Object paramVal;
    public void setColName(String value){
        this.colName = value;
    }
    public void setIsList(boolean value){
        this.isList = value;
    }
    public void setParamListVal(List value){
        this.paramListVal = value;
    }
    public void setParamVal(Object value){
        this.paramVal = value;
    }
    public String getColName(){
        return  this.colName;
    }
    public boolean getIsList(){
        return isList;
    }
    public List getParamListVal(){
        return paramListVal;
    }
    public Object getParamVal(){
        return paramVal;
    }
}
