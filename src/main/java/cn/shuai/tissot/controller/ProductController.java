package cn.shuai.tissot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shuai on 2017/1/3.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

//    @RequestMapping(name="/",method = RequestMethod.GET)
//    public String getIndex(){
//        return "index";
//    }

//    @RequestMapping(name="/name",method = RequestMethod.GET)
//    public String getName(){
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("name","name");
//        map.put("value","0");
//        return map.toString();
//    }

    @RequestMapping(value = "/value",method = RequestMethod.GET)
    @ResponseBody
    public Object getValue(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","name");
        map.put("value","1");
        return map;
    }
}
