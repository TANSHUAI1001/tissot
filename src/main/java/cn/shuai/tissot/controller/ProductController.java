package cn.shuai.tissot.controller;

import cn.shuai.tissot.dto.Exposure;
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

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String getIndex(){
        return "/welcome";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getTest(){
        return "/test";
    }

    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public ResponseEntity<Exposure> getName(){
        Exposure exposure = new Exposure(false,"md5",1234L);
        return new ResponseEntity<Exposure>(exposure,HttpStatus.OK);
    }

    @RequestMapping(value = "/value", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getValue() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "name");
        map.put("value", "1");
        logger.info(String.valueOf(map));
        return ResponseEntity.ok(map);
    }

}
