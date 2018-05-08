package cn.shuai.tissot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shuai on 2017/1/3.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @RequestMapping(name="/",method = RequestMethod.GET)
//    public String getIndex(){
//        return "index";
//    }

    @RequestMapping(name="/name",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getName(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","name");
        map.put("value","0");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/value", method = RequestMethod.GET)
    @ResponseBody
    public Object getValue() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "name");
        map.put("value", "1");
        logger.info(String.valueOf(map));
        return map;
    }

    public String getTime() {
        Date now = new Date();
        return now.toString();
    }
}
