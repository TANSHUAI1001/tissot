package cn.shuai.tissot.controller;


import java.util.List;

import cn.shuai.tissot.dto.ActivityExecution;
import cn.shuai.tissot.dto.ActivityResult;
import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.entity.Activity;
import cn.shuai.tissot.enums.ActivityResultEnum;
import cn.shuai.tissot.enums.ActivityStatEnum;
import cn.shuai.tissot.exception.ActivityCloseException;
import cn.shuai.tissot.exception.ActivityException;
import cn.shuai.tissot.exception.RepeatKillException;
import cn.shuai.tissot.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/seckill")
public class SeckillController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private ActivityService activityService;

    /**
     * 商品列表页面
     */
    @RequestMapping("/list")
    public String list(Model model) {
        // 获取列表页
        List<Activity> list = activityService.getActivityList();
        model.addAttribute("list", list);
        return "/list";
    }

    /**
     * 商品详情页面
     *
     */
    @RequestMapping("/{seckillId}/detail")
    public String detail(@PathVariable Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }

        Activity seckill = activityService.getActivityById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "/detail";
    }

    /**
     * 暴露秒杀地址
     */
    @RequestMapping("/{seckillId}/exposer")
    @ResponseBody
    public ActivityResult<Exposure> exposer(@PathVariable Long seckillId) {
        ActivityResult<Exposure> result;
        try {
            Exposure exposer = activityService.exportActivityUrl(seckillId);
            result = new ActivityResult<Exposure>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new ActivityResult<Exposure>(false, ActivityResultEnum.NOT_FOUND);
        }
        return result;
    }

    /**
     * 执行秒杀
     *
     */
    @RequestMapping("/{seckillId}/{md5}/execution")
    @ResponseBody
    public ActivityResult<ActivityExecution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5,
                                                     @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return new ActivityResult<ActivityExecution>(false, ActivityResultEnum.NOT_REGISTER);
        }

        ActivityResult<ActivityExecution> result;
        try {
            ActivityExecution seckillExecution = activityService.executeActivity(seckillId, phone, md5);
            result = new ActivityResult<ActivityExecution>(true, seckillExecution);
        } catch (RepeatKillException e) {
            ActivityExecution seckillExecution = new ActivityExecution(seckillId, ActivityStatEnum.REPEAT_KILL);
            result = new ActivityResult<ActivityExecution>(true, seckillExecution);
        } catch (ActivityCloseException e) {
            ActivityExecution seckillExecution = new ActivityExecution(seckillId, ActivityStatEnum.END);
            result = new ActivityResult<ActivityExecution>(true, seckillExecution);
        } catch (ActivityException e) {
            ActivityExecution seckillExecution = new ActivityExecution(seckillId, ActivityStatEnum.INNER_ERROR);
            result = new ActivityResult<ActivityExecution>(true, seckillExecution);
        }
        return result;
    }

    /**
     * 获取系统时间
     *
     */
    @RequestMapping("/time/now")
    @ResponseBody
    public ActivityResult<Long> time() {
        long time = System.currentTimeMillis();
        return new ActivityResult<Long>(true, time);
    }
}