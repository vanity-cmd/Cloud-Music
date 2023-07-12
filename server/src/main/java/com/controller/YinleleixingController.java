package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.YinleleixingEntity;
import com.entity.view.YinleleixingView;

import com.service.YinleleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 音乐类型
 * 后端接口
 * @author 
 * @email 
 * @date 2023-04-24 22:37:44
 */
@RestController
@RequestMapping("/yinleleixing")
public class YinleleixingController {
    @Autowired
    private YinleleixingService yinleleixingService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YinleleixingEntity yinleleixing,
		HttpServletRequest request){
        EntityWrapper<YinleleixingEntity> ew = new EntityWrapper<YinleleixingEntity>();

		PageUtils page = yinleleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yinleleixing), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YinleleixingEntity yinleleixing, 
		HttpServletRequest request){
        EntityWrapper<YinleleixingEntity> ew = new EntityWrapper<YinleleixingEntity>();

		PageUtils page = yinleleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yinleleixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YinleleixingEntity yinleleixing){
       	EntityWrapper<YinleleixingEntity> ew = new EntityWrapper<YinleleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yinleleixing, "yinleleixing")); 
        return R.ok().put("data", yinleleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YinleleixingEntity yinleleixing){
        EntityWrapper< YinleleixingEntity> ew = new EntityWrapper< YinleleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yinleleixing, "yinleleixing")); 
		YinleleixingView yinleleixingView =  yinleleixingService.selectView(ew);
		return R.ok("查询音乐类型成功").put("data", yinleleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YinleleixingEntity yinleleixing = yinleleixingService.selectById(id);
        return R.ok().put("data", yinleleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YinleleixingEntity yinleleixing = yinleleixingService.selectById(id);
        return R.ok().put("data", yinleleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YinleleixingEntity yinleleixing, HttpServletRequest request){
    	yinleleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yinleleixing);
        yinleleixingService.insert(yinleleixing);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YinleleixingEntity yinleleixing, HttpServletRequest request){
    	yinleleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yinleleixing);
        yinleleixingService.insert(yinleleixing);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YinleleixingEntity yinleleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yinleleixing);
        yinleleixingService.updateById(yinleleixing);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yinleleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
