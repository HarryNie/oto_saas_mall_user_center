package com.zhongping.utils.json;

import com.alibaba.fastjson.JSONObject;
import com.zhongping.enums.ReqEnums;
import com.zhongping.utils.exception.ApiException;
import org.springframework.util.StringUtils;

public class CheckJsonKey {

    public static void checkKey(JSONObject jsonObject, String... args) throws ApiException{

        if (jsonObject == null && jsonObject.size() == 0) {
            throw new ApiException(ReqEnums.REQ_PARAM_ERROR.getCode(), "参数为空" );
        }
        if (args != null && args.length >0){

            for (String s : args){
                if (!jsonObject.containsKey(s)){
                    throw new ApiException(ReqEnums.REQ_PARAM_ERROR.getCode(), "参数(" + s + ")为空");
                }
            }
        }
    }

    public static JSONObject checkParams(String params , String... args){
        JSONObject jsonObject = paramsToJSONObject(params);
        checkKey(jsonObject,args);
        return jsonObject;
    }

    public static JSONObject paramsToJSONObject(String params){
        JSONObject jsonObject = new JSONObject();
        if (!StringUtils.isEmpty(params)){
            jsonObject = JSONObject.parseObject(params);
        }
        return jsonObject;
    }
}
