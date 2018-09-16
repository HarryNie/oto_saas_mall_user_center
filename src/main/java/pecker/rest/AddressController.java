package pecker.rest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pecker.service.AddressService;
import pecker.utils.CheckJsonKey;

@RestController
@RequestMapping("/user/v1")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/contact")
    public JSONObject queryReceiver(@RequestHeader String userid, @RequestParam String contactId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userid);
        jsonObject.put("id", contactId);
        return addressService.queryReceiver(jsonObject);
    }

    /**
     * 查询默认地址
     *
     * @param userid
     * @return
     */
    @GetMapping("/contact/default")
    public JSONObject getDefaultReciver(@RequestHeader String userid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userid);
        return addressService.getDefaultReciver(jsonObject);
    }

    @GetMapping("/contacts")
    @SuppressWarnings("unchecked")
    public JSONObject queryReceiverList(@RequestHeader String userid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userid);
        return addressService.queryReceiverList(jsonObject);
    }

    /**
     * name	String	是	"张三丰"	联系人姓名
     * phone	String	是	"13500000000"	联系人电话
     * userId	String	是	"blm_test"	用户ID
     * address	String	是	"上海市虹口区"	地址
     * detail	String	是	"花园路128号7座"	详细地址
     * houseNum	String	是	"2091"	门牌号
     * province	String	是	"江苏"	省名
     * provinceId	String	是	"11"	省ID
     * city	String	是	"南京"	市名
     * cityId	String	是	"1101"	市ID
     * county	String	是	"江宁区"	县区名
     * countyId	String	是	"110101"	县区ID
     * longitude	Double	否	121.4797133	收货地址经度
     * latitude	Double	否	31.27663027	收货地址纬度
     * gender	Integer	否	0	性别，0-男性，1-女性，默认男性
     * isDefault	Integer	否	0	是否设为默认地址，0或不传为否，1为是
     * <p>
     * provinceId
     * cityId
     * receiverAddress
     * receiverName
     * receiverPhone
     * isDefault
     *
     * @param userid
     * @param jsonObject
     * @return
     */
    @PostMapping("/contact")
    public JSONObject addReceiver(@RequestHeader String userid, @RequestBody JSONObject jsonObject) {
        CheckJsonKey.checkKey(jsonObject, "provinceId", "cityId", "countyId", "province", "city", "county", "address", "name", "phone");
        jsonObject.put("userId", userid);
        return addressService.addReceiver(jsonObject);
    }

    @PutMapping("/contact")
    public JSONObject updateReceiver(@RequestHeader String userid, @RequestBody JSONObject jsonObject) {
        CheckJsonKey.checkKey(jsonObject, "provinceId", "cityId", "countyId", "province", "city", "county", "address", "name", "phone", "contactId");
        jsonObject.put("userId", userid);
        return addressService.updateReceiver(jsonObject);
    }

    @DeleteMapping("/contact")
    public JSONObject deleteReceiver(@RequestHeader(required = false) String userid,
                                     @RequestParam String contactId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userid);
        jsonObject.put("id", contactId);
        return addressService.deleteReceiver(jsonObject);
    }
}
