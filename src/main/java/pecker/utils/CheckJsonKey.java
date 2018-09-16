package pecker.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import pecker.enums.ResultCode;

public class CheckJsonKey {

    public static void checkKey(JSONObject jsonObject, String... args) throws ApiException{

        if (jsonObject == null && jsonObject.size() == 0) {
            throw new ApiException(ResultCode.PARAM_ERROR.code, ResultCode.PARAM_ERROR.message);
        }
        if (args != null && args.length >0){

            for (String s : args){
                if (!jsonObject.containsKey(s)){
                    throw new ApiException(ResultCode.PARAM_ERROR.code, "参数(" + s + ")为空");
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
