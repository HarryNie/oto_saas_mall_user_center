package pecker.service;

import com.alibaba.fastjson.JSONObject;

public interface AddressService {

    JSONObject queryReceiver(JSONObject jsonObject);
    JSONObject getDefaultReciver(JSONObject jsonObject);
    JSONObject queryReceiverList(JSONObject jsonObject);
    JSONObject addReceiver(JSONObject jsonObject);
    JSONObject updateReceiver(JSONObject jsonObject);
    JSONObject deleteReceiver(JSONObject jsonObject);
}
