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

import com.entity.GeshouEntity;
import com.entity.view.GeshouView;

import com.service.GeshouService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 歌手
 * 后端接口
 * @author 
 * @email 
 * @date 2023-04-24 22:37:44
 */
@RestController
@RequestMapping("/geshou")
public class GeshouController {
    @Autowired
    private GeshouService geshouService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GeshouEntity geshou,
		HttpServletRequest request){
        EntityWrapper<GeshouEntity> ew = new EntityWrapper<GeshouEntity>();

		PageUtils page = geshouService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, geshou), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,GeshouEntity geshou, 
		HttpServletRequest request){
        EntityWrapper<GeshouEntity> ew = new EntityWrapper<GeshouEntity>();

		PageUtils page = geshouService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, geshou), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GeshouEntity geshou){
       	EntityWrapper<GeshouEntity> ew = new EntityWrapper<GeshouEntity>();
      	ew.allEq(MPUtil.allEQMapPre( geshou, "geshou")); 
        return R.ok().put("data", geshouService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GeshouEntity geshou){
        EntityWrapper< GeshouEntity> ew = new EntityWrapper< GeshouEntity>();
 		ew.allEq(MPUtil.allEQMapPre( geshou, "geshou")); 
		GeshouView geshouView =  geshouService.selectView(ew);
		return R.ok("查询歌手成功").put("data", geshouView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GeshouEntity geshou = geshouService.selectById(id);
        return R.ok().put("data", geshou);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GeshouEntity geshou = geshouService.selectById(id);
        return R.ok().put("data", geshou);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GeshouEntity geshou, HttpServletRequest request){
    	geshou.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(geshou);
        geshouService.insert(geshou);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody GeshouEntity geshou, HttpServletRequest request){
    	geshou.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(geshou);
        geshouService.insert(geshou);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody GeshouEntity geshou, HttpServletRequest request){
        //ValidatorUtils.validateEntity(geshou);
        geshouService.updateById(geshou);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        geshouService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
