package pecker.enums;

/**
 * @Author hongql
 *
 * @Date Created by blm on 22/5/17.
 * @Description 描述
 */
public enum ResultCode {
    SUCCESS(0,"处理成功"),
    PARAM_ERROR(1,"所传参数不正确"),
    NULL_ERROR(2,"未找到相关数据"),
    IMG_NULL_ERROR(3,"上传失败，上传图片数据为空"),
    IMG_FORMA_ERROR(4,"上传图片格式不合法"),
    IMG_DATA_ERROR(5,"上传失败，数据不合法"),
    MAX_SCORP_ERROR(6,"收货地址超出范围"),
    LOGIC_ERROR(7,"后台有误，请联系客服"),
    IMG_SERVER_ERROR(8,"服务器抛锚了，请重新上传"),
    REFUND_ERROR(9,"取消订单失败"),
    ERROR(10,"处理失败"),
    ORDER_REST(11,"该服务已停售，请重新选择服务"),
    SERVICE_COUNT_FULL(12,"该时间段的服务已爆满，请重新选择服务时间"),
    REPEATED_ERRPR(13,"您已操作，请稍后"),
    COMMENT_ERROR(14,"订单已评价"),
    UNCOMMENT_ERROR(15,"该笔订单不可评价"),
    ADDRESS_NULL_ERROR(15,"收货地址不存在"),
    ORDERTYPE_NULL_ERROR(16,"订单类型不存在"),
    BINDINVOICEERROR(17,"绑定发票失败"),
    CREATEINVOICEERROR(18,"创建发票失败"),
    ORDERCENTER_ERROR(19,"调用订单中心接口出错"),
    CATEGORY_COMMODITY_NULL(20,"该种类商品暂时停售"),
    ORDER_MAX(21,"超出活动最大购买限制"),
    BOSSENTERPRISE_NULL_ERROR(4001,"未找到相关的平台信息，请检查appCode是否正确"),
    APPLICATION_NULL_ERROR(4002,"未找到相关的应用信息，请检查appCode是否正确"),
    INVENTORY_NOT_ENOUGH(4003,"商品库存不足"),
    ;

    public int code;
    public String message;

    ResultCode(int code , String name){
        this.code = code;
        this.message = name;
    }
}