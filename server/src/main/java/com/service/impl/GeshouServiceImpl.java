package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.GeshouDao;
import com.entity.GeshouEntity;
import com.service.GeshouService;
import com.entity.vo.GeshouVO;
import com.entity.view.GeshouView;

@Service("geshouService")
public class GeshouServiceImpl extends ServiceImpl<GeshouDao, GeshouEntity> implements GeshouService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GeshouEntity> page = this.selectPage(
                new Query<GeshouEntity>(params).getPage(),
                new EntityWrapper<GeshouEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GeshouEntity> wrapper) {
		  Page<GeshouView> page =new Query<GeshouView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<GeshouVO> selectListVO(Wrapper<GeshouEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GeshouVO selectVO(Wrapper<GeshouEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GeshouView> selectListView(Wrapper<GeshouEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GeshouView selectView(Wrapper<GeshouEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
