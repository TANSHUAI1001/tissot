package cn.shuai.tissot.controller;

import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.dto.ActivityExecution;
import cn.shuai.tissot.dto.ActivityResult;
import cn.shuai.tissot.entity.Activity;
import cn.shuai.tissot.enums.ActivityResultEnum;
import cn.shuai.tissot.enums.ActivityStatEnum;
import cn.shuai.tissot.exception.ActivityCloseException;
import cn.shuai.tissot.exception.RepeatKillException;
import cn.shuai.tissot.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by shuai on 2017/1/3.
 * RequestMapping 注解：
 * 1. 支持标准URL
 * 2. Ant风格URL( ? 和 * 和 ** 字符）
 * 3. 带{xxx}占位符的URL
 *
 * RequestMapping注解有六个属性，下面我们把她分成三类进行说明。
 * 1、 value， method；
 * value：指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；
 * method：指定请求的method类型， GET、POST、PUT、DELETE等；
 * 2、 consumes，produces；
 * consumes：指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
 * produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
 * 3、 params，headers；
 * params：指定request中必须包含某些参数值是，才让该方法处理。
 * headers：指定request中必须包含某些指定的header值，才能让该方法处理请求。
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html
 */
// /user/*/creation => /user/a/creation, /user/b/creation
// /user/**/creation => /user/creation, /user/a/b/creation

@Controller
@RequestMapping("/activity")
public class ActivityController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ActivityService activityService;

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String getWelcome(){
        return "/welcome";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getTest(){
        return "/test";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String getIndex(){
        return "/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getList() {
        List<Activity> activities = activityService.getActivityList();
        ActivityResult result = new ActivityResult<List>(true,activities);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public ResponseEntity getDetail(@PathVariable Long id) {
        if(id == null){
            ActivityResult result = new ActivityResult(false, ActivityResultEnum.ERROR_PARAM);
            return ResponseEntity.ok(result);
        }
        Activity activity = activityService.getActivityById(id);
        if(activity == null){
            ActivityResult result = new ActivityResult(false,ActivityResultEnum.NOT_FOUND);
            return ResponseEntity.ok(result);
        }
        ActivityResult result = new ActivityResult<Activity>(true,activity);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public ResponseEntity getServerTime() {
        Date now = new Date();
        ActivityResult result = new ActivityResult<Long>(true,now.getTime());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}/exposure", method = RequestMethod.GET)
    public ResponseEntity getExposureUrl(@PathVariable Long id) {
        if(id == null){
            ActivityResult<Exposure> result = new ActivityResult<Exposure>(false,ActivityResultEnum.ERROR_PARAM);
            return ResponseEntity.ok(result);
        }
        Exposure exposure = activityService.exportActivityUrl(id);
        ActivityResult<Exposure> result = new ActivityResult<Exposure>(true,exposure);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}/{md5}/execution", method = RequestMethod.GET)
    @ResponseBody
    public ActivityResult execute(@PathVariable Long id, @PathVariable String md5,
                                  @CookieValue(value = "telephone",required = false) Long phone) {
        if(phone == null){
            return new ActivityResult(false,ActivityResultEnum.NOT_REGISTER);
        }
        ActivityResult<ActivityExecution> result;
        try {
            ActivityExecution executionResult = activityService.executeActivity(id,phone,md5);
            result = new ActivityResult<ActivityExecution>(true,executionResult);
        }catch (RepeatKillException re){
            ActivityExecution execution = new ActivityExecution(id, ActivityStatEnum.REPEAT_KILL);
            return new ActivityResult<ActivityExecution>(true,execution);
        }catch (ActivityCloseException ae){
            ActivityExecution execution = new ActivityExecution(id,ActivityStatEnum.END);
            return new ActivityResult<ActivityExecution>(true,execution);
        }catch (Exception e){
            logger.error(e.getMessage());
            ActivityExecution execution = new ActivityExecution(id,ActivityStatEnum.INNER_ERROR);
            return new ActivityResult<ActivityExecution>(true,execution);
        }

        return result;
    }

}
