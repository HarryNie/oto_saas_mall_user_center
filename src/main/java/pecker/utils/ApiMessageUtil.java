package pecker.utils;


import pecker.enums.ResultCode;

/**
 * 返回结果集工具类
 */
public class ApiMessageUtil {

    //请求成功， 有返回值
    public static ApiMessage success(Object object) {
        return new ApiMessage(ResultCode.SUCCESS, object);
    }

    //请求成功， 有返回值
    public static ApiMessage success() {
        return new ApiMessage(ResultCode.SUCCESS);
    }

    //请求失败，非系统异常
    public static ApiMessage failure(int s1, String s2) {
        return new ApiMessage(s1, s2);
    }

    //请求失败，非系统异常
    public static ApiMessage failure(ResultCode enums) {
        return new ApiMessage(enums);
    }

    //请求失败，系统异常
    public static ApiMessage failure() {
        return new ApiMessage(ResultCode.SUCCESS);
    }
}
