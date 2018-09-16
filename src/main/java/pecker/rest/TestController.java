package pecker.rest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pecker.mybatis.entity.City;
import pecker.mybatis.entity.CityExample;
import pecker.mybatis.mapper.CityMapper;
import pecker.mybatis.mapper.ProvinceMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    @PostMapping
    public JSONObject getStoreProvinceCity(@RequestBody JSONObject jsonObject){
        log.info("{}", jsonObject);
        List<Map<String, Object>> a = (List<Map<String, Object>>) jsonObject.get("a");
        for (Map<String, Object> map : a){
//            String id = (String) map.get("id");
//            String name = (String) map.get("name");
            List<Map<String, Object>> cityList = (List<Map<String, Object>>) map.get("city");
//            log.info("id = {}, name = {}", id, name);
            for(Map<String, Object> map1 : cityList){
//                log.info("map1: {}", map1);
                String id1 = (String) map1.get("id");
                String name1 = (String) map1.get("name");
                CityExample cityExample = new CityExample();
                cityExample.or().andCityNameEqualTo(name1);
                City city = new City();
                city.setMallCode(id1);
                cityMapper.updateByExampleSelective(city, cityExample);
            }
//            ProvinceExample provinceExample = new ProvinceExample();
//            provinceExample.or().andProvinceNameEqualTo(name);
//            Province province = new Province();
//            province.setMallCode(id);
//            provinceMapper.updateByExampleSelective(province, provinceExample);
        }
        return null;
    }
}
