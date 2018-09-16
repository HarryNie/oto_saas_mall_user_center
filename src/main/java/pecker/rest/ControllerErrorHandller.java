package pecker.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import pecker.enums.ResultCode;
import pecker.utils.ApiException;
import pecker.utils.ApiMessage;
import pecker.utils.ApiMessageUtil;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author baiban 
 * create by baiban to 2018-05-21
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerErrorHandller {

    /**
     * Valid标签 校验失败
     */
    @ExceptionHandler(BindException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiMessage processControllerError(NativeWebRequest request, BindException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);

        return ApiMessageUtil.failure(ResultCode.PARAM_ERROR);
    }

    /**
     * Validated标签 校验失败
     */
    @ExceptionHandler(ConstraintViolationException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiMessage processControllerError(NativeWebRequest request, ConstraintViolationException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);

        return ApiMessageUtil.failure(ResultCode.PARAM_ERROR);
    }

    /**
     * required=true 校验失败
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiMessage processControllerError(NativeWebRequest request,
            MissingServletRequestParameterException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.PARAM_ERROR);
    }

    /**
     * JSR303注解参数 校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiMessage processJsr303ValidatorError(NativeWebRequest request,
            MethodArgumentNotValidException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.PARAM_ERROR);
    }

    /**
     * 输入参数数据类型转换错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiMessage processControllerError(NativeWebRequest request, HttpMessageNotReadableException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.PARAM_ERROR);
    }

    /**
     * 连接超时
     */
    @ExceptionHandler(TimeoutException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiMessage processControllerError(NativeWebRequest request, TimeoutException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.ERROR);
    }


    /**
     * 封装的通用异常, error日志
     */
    @ExceptionHandler(ApiException.class)
   // @ResponseStatus(HttpStatus.CHECKPOINT)
    public ApiMessage processControllerError(NativeWebRequest request, ApiException ex) {
        log.error("level:{}, code:{}, message:{}, request parameters:{}",ex.getLevel(),ex.getErrorCode(),
                ex.getErrorMessage(),
                getObjString(request.getParameterMap()), ex);

        return ApiMessageUtil.failure(ex.getErrorCode(),ex.getErrorMessage());
    }

    /**
     * spring封装的数据库异常
     */
    @ExceptionHandler(DataAccessException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiMessage processControllerError(NativeWebRequest request, DataAccessException ex) {
        log.error("message:{}, request parameters:{}",
                  ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.ERROR);
    }

    /**
     * spring未捕获的数据库异常
     */
    @ExceptionHandler(SQLException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiMessage processControllerError(NativeWebRequest request, SQLException ex) {
        log.error("code:{}, message:{}, request parameters:{}",
                ex.getErrorCode(), ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.ERROR);
    }

    /**
     * Insert或Update数据时违反了完整性，例如字段过长
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiMessage processControllerError(NativeWebRequest request, DataIntegrityViolationException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.ERROR.code,"数据格式错误！");
    }

    /**
     * 兜底1
     */
    @ExceptionHandler(RuntimeException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiMessage processControllerError(NativeWebRequest request, RuntimeException ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.ERROR);
    }

    /**
     * 兜底2
     */
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiMessage processControllerError(NativeWebRequest request, Exception ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.ERROR);
    }

    /**
     * 兜底3
     */
    @ExceptionHandler(Throwable.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiMessage processControllerError(NativeWebRequest request, Throwable ex) {
        log.error("message:{}, request parameters:{}",
                ex.getMessage(),
                getObjString(request.getParameterMap()), ex);
        return ApiMessageUtil.failure(ResultCode.ERROR);
    }

    private String getObjString(Object obj) {
        if(obj == null) {
            return "null";
        } else {
            Class clazz = obj.getClass();
            if(!clazz.isArray() && !(obj instanceof Collection) && !(obj instanceof Map)) {
                Method method = getMethod(clazz, "toString", new Class[0]);
                if(method == null) {
                    return "null";
                } else {
                    Class declaringClass = method.getDeclaringClass();
                    return Object.class != declaringClass?String.valueOf(obj): JSON.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
                }
            } else {
                return JSON.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
            }
        }
    }

    private Method getMethod(Class<?> clazz, String name, Class... parameterTypes) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException var4) {
            return null;
        }
    }

}