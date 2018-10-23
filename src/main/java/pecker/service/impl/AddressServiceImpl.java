package pecker.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pecker.AAAconfig.BaojunConfig;
import pecker.enums.ResultCode;
import pecker.mybatis.entity.City;
import pecker.mybatis.entity.CityExample;
import pecker.mybatis.entity.Province;
import pecker.mybatis.entity.ProvinceExample;
import pecker.mybatis.mapper.CityMapper;
import pecker.mybatis.mapper.ProvinceMapper;
import pecker.service.AddressService;
import pecker.utils.ApiException;
import pecker.utils.ApiMessage;
import pecker.utils.HttpPoster;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public JSONObject queryReceiver(JSONObject jsonObject) {
        try {
            JSONObject res = HttpPoster.doPost(BaojunConfig.QUERY_ADDRESS_URL, jsonObject);
            if (res.getIntValue("code") == 200) {
                JSONObject data = JSONObject.parseObject(JSON.toJSONString(res.get("data")));
                this.replaceUserInfo(data);
                this.replaceAddressWithMallCode(data);
                return ApiMessage.result(data);
            }
            log.error("查询宝骏地址接口失败1");
            throw new ApiException(ResultCode.ERROR.code, "查询宝骏地址接口失败1");
        } catch (ApiException e) {
            e.printStackTrace();
            log.error("查询宝骏地址接口失败2");
            throw new ApiException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询宝骏地址接口失败3");
            throw new ApiException(ResultCode.ERROR.code, "查询宝骏地址接口失败3");
        }
    }

    @Override
    public JSONObject getDefaultReciver(JSONObject jsonObject) {
        try {
            JSONObject res = HttpPoster.doPost(BaojunConfig.DEFAULT_ADDRESS_URL, jsonObject);
            if (res.getIntValue("code") == 200) {
                JSONObject data = JSONObject.parseObject(JSON.toJSONString(res.get("data")));
                this.replaceUserInfo(data);
                this.replaceAddressWithMallCode(data);
                return ApiMessage.result( data);
            }
            log.error("查询宝骏默认地址接口失败1");
            throw new ApiException(ResultCode.ERROR.code, "查询宝骏默认地址接口失败1");
        } catch (ApiException e) {
            e.printStackTrace();
            log.error("查询宝骏默认地址接口失败2");
            throw new ApiException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询宝骏地址接口失败3");
            throw new ApiException(ResultCode.ERROR.code, "查询宝骏默认地址接口失败3");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject queryReceiverList(JSONObject jsonObject) {
        try {
            JSONObject res = HttpPoster.doPost(BaojunConfig.ADDRESS_LIST_URL, jsonObject);
            if (res.getIntValue("code") == 200) {
                List<JSONObject> jsons = (List<JSONObject>) res.get("data");
                for (JSONObject data : jsons) {
                    this.replaceUserInfo(data);
                    this.replaceAddressWithMallCode(data);
                }
                return ApiMessage.result( jsons);
            }
            log.error("查询宝骏地址列表接口失败1");
            throw new ApiException(ResultCode.ERROR.code, "查询宝骏地址列表接口失败1");
        } catch (ApiException e) {
            e.printStackTrace();
            log.error("查询宝骏地址列表接口失败2");
            throw new ApiException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询宝骏地址列表接口失败3");
            throw new ApiException(ResultCode.ERROR.code, "查询宝骏地址列表接口失败3");
        }
    }

    @Override
    public JSONObject addReceiver(JSONObject jsonObject) {
        this.replaceAddressWithBaojunCode(jsonObject);
        jsonObject.put("receiverName", jsonObject.getString("name"));
        jsonObject.put("receiverPhone", jsonObject.getString("phone"));

        String paramStr = jsonObject.toString();
        jsonObject.clear();
        jsonObject.put("paramStr", paramStr);
        try {
            JSONObject res = HttpPoster.doPost(BaojunConfig.ADD_ADDRESS_URL, jsonObject);
            if (res.getIntValue("code") == 200) {
                JSONObject data = JSONObject.parseObject(JSON.toJSONString(res.get("data")));
                this.replaceUserInfo(data);
                this.replaceAddressWithMallCode(data);
                return ApiMessage.result(data);
            }
            log.error("宝骏新增地址接口失败1");
            throw new ApiException(ResultCode.ERROR.code, "宝骏新增地址接口失败1");
        } catch (ApiException e) {
            e.printStackTrace();
            log.error("宝骏新增地址接口失败2");
            throw new ApiException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("宝骏新增地址接口失败3");
            throw new ApiException(ResultCode.ERROR.code, "宝骏新增地址接口失败3");
        }
    }

    @Override
    public JSONObject updateReceiver(JSONObject jsonObject) {
        this.replaceAddressWithBaojunCode(jsonObject);
        jsonObject.put("id", jsonObject.getString("contactId"));
        jsonObject.put("receiverName", jsonObject.getString("name"));
        jsonObject.put("receiverPhone", jsonObject.getString("phone"));

        String paramStr = jsonObject.toString();
        jsonObject.clear();
        jsonObject.put("paramStr", paramStr);
        try {
            JSONObject res = HttpPoster.doPost(BaojunConfig.MODIFY_ADDRESS_URL, jsonObject);
            if (res.getIntValue("code") == 200) {
                JSONObject data = JSONObject.parseObject(JSON.toJSONString(res.get("data")));
                this.replaceUserInfo(data);
                this.replaceAddressWithMallCode(data);
                return ApiMessage.result(data);
            }
            log.error("宝骏更新地址接口失败1");
            throw new ApiException(ResultCode.ERROR.code, "宝骏更新地址接口失败1");
        } catch (ApiException e) {
            e.printStackTrace();
            log.error("宝骏更新地址接口失败2");
            throw new ApiException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("宝骏更新地址接口失败3");
            throw new ApiException(ResultCode.ERROR.code, "宝骏更新地址接口失败3");
        }
    }

    @Override
    public JSONObject deleteReceiver(JSONObject jsonObject) {
        try {
            JSONObject res = HttpPoster.doPost(BaojunConfig.DEL_ADDRESS_URL, jsonObject);
            if (res.getIntValue("code") == 200) {
                return ApiMessage.result();
            }
            log.error("宝骏更新地址接口失败1");
            throw new ApiException(ResultCode.ERROR.code, "宝骏更新地址接口失败1");
        } catch (ApiException e) {
            e.printStackTrace();
            log.error("宝骏更新地址接口失败2");
            throw new ApiException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("宝骏更新地址接口失败3");
            throw new ApiException(ResultCode.ERROR.code, "宝骏更新地址接口失败3");
        }
    }

    private JSONObject replaceAddressWithMallCode(JSONObject jsonObject) {
        String cityId = jsonObject.getString("cityId");
        String provinceId = jsonObject.getString("provinceId");
        if (provinceId != null && !provinceId.equals("")) {
            Province province = provinceMapper.selectByPrimaryKey(provinceId);
            if (province != null)
                jsonObject.put("provinceId", province.getMallCode());
        }
        if (cityId != null && !cityId.equals("")) {
            City city = cityMapper.selectByPrimaryKey(cityId);
            if (city != null)
                jsonObject.put("cityId", city.getMallCode());
        }
        String receiverAddress = jsonObject.getString("receiverAddress");
        String countyId;
        String county;
        String address;
        if (receiverAddress.split("-=-=-").length > 2) {
            countyId = receiverAddress.split("-=-=-")[0];
            county = receiverAddress.split("-=-=-")[1];
            address = receiverAddress.replaceFirst(countyId + "-=-=-", "");
            address = address.replaceFirst(county + "-=-=-", "");
            jsonObject.put("countyId", countyId);
            jsonObject.put("county", county);
            jsonObject.put("address", address);
            jsonObject.remove("receiverAddress");
        }else {
            jsonObject.put("address", receiverAddress);
            jsonObject.remove("receiverAddress");
        }

        jsonObject.put("detail", "");
        jsonObject.put("isDefault", jsonObject.getIntValue("isDefault"));
        return jsonObject;
    }

    private JSONObject replaceAddressWithBaojunCode(JSONObject jsonObject) {
        String provinceId = jsonObject.getString("provinceId");
        if (provinceId == null || provinceId.equals(""))
            return null;
        ProvinceExample provinceExample = new ProvinceExample();
        provinceExample.or().andMallCodeEqualTo(provinceId);
        List<Province> provinceList = provinceMapper.selectByExample(provinceExample);
        if (provinceList != null && !provinceList.isEmpty()) {
            provinceId = provinceList.get(0).getId();
            jsonObject.put("provinceId", provinceId);
        }
        String cityId = jsonObject.getString("cityId");
        if (cityId == null || cityId.equals(""))
            return null;
        CityExample cityExample = new CityExample();
        cityExample.or().andMallCodeEqualTo(cityId);
        List<City> cityList = cityMapper.selectByExample(cityExample);
        if (cityList != null && !cityList.isEmpty()) {
            cityId = cityList.get(0).getId();
            jsonObject.put("cityId", cityId);
        }

        String countyId = jsonObject.getString("countyId");
        String county = jsonObject.getString("county");
        String address = jsonObject.getString("address");
        String receiverAddress = countyId + "-=-=-" + county + "-=-=-" + address;
        jsonObject.put("receiverAddress", receiverAddress);
        jsonObject.remove("countyId");
        jsonObject.remove("address");

        return jsonObject;
    }

    private void replaceUserInfo(JSONObject data) {
        data.put("contactId", data.getString("id"));
        data.put("name", data.getString("receiverName"));
        data.put("phone", data.getString("receiverPhone"));
        data.remove("id");
        data.remove("receiverName");
        data.remove("receiverPhone");
    }
}
