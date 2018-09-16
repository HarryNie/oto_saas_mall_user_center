package pecker.AAAconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "baojun")
public class BaojunConfig {

    public static String QUERY_ADDRESS_URL;
    public static String DEFAULT_ADDRESS_URL;
    public static String ADDRESS_LIST_URL;
    public static String ADD_ADDRESS_URL;
    public static String MODIFY_ADDRESS_URL;
    public static String DEL_ADDRESS_URL;

    public void setQueryAddressUrl(String queryAddressUrl) {
        QUERY_ADDRESS_URL = queryAddressUrl;
    }

    public void setDefaultAddressUrl(String defaultAddressUrl) {
        DEFAULT_ADDRESS_URL = defaultAddressUrl;
    }

    public void setAddressListUrl(String addressListUrl) {
        ADDRESS_LIST_URL = addressListUrl;
    }

    public void setAddAddressUrl(String addAddressUrl) {
        ADD_ADDRESS_URL = addAddressUrl;
    }

    public void setModifyAddressUrl(String modifyAddressUrl) {
        MODIFY_ADDRESS_URL = modifyAddressUrl;
    }

    public void setDelAddressUrl(String delAddressUrl) {
        DEL_ADDRESS_URL = delAddressUrl;
    }
}
