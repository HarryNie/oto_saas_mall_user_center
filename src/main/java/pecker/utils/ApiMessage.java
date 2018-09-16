package pecker.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pecker.enums.ResultCode;

import java.io.Serializable;

/**
 * 响应json数据用的公共类
 *
 * @author Administrator
 */
public class ApiMessage implements Serializable {

    int code;

    String message;

    Object data;

    public ApiMessage() {
    }

    public ApiMessage(Object data) {
        this.code = 0;
        this.message = "处理成功";
        this.data = data;
    }

    public ApiMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiMessage(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiMessage(ResultCode reqEnums) {
        this.code = reqEnums.code;
        this.message = reqEnums.message;
    }

    public ApiMessage(ResultCode reqEnums, Object data) {
        this.code = reqEnums.code;
        this.message = reqEnums.message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static JSONObject result() {
        return JSONObject.parseObject(JSON.toJSONString(new ApiMessage(ResultCode.SUCCESS)));
    }

    public static JSONObject result(Object data) {
        return JSONObject.parseObject(JSON.toJSONString(new ApiMessage(data)));
    }
}
