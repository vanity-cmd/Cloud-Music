package com.dao;

import com.entity.GeshouEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.GeshouVO;
import com.entity.view.GeshouView;


/**
 * 歌手
 * 
 * @author 
 * @email 
 * @date 2023-04-24 22:37:44
 */
public interface GeshouDao extends BaseMapper<GeshouEntity> {
	
	List<GeshouVO> selectListVO(@Param("ew") Wrapper<GeshouEntity> wrapper);
	
	GeshouVO selectVO(@Param("ew") Wrapper<GeshouEntity> wrapper);
	
	List<GeshouView> selectListView(@Param("ew") Wrapper<GeshouEntity> wrapper);

	List<GeshouView> selectListView(Pagination page,@Param("ew") Wrapper<GeshouEntity> wrapper);
	
	GeshouView selectView(@Param("ew") Wrapper<GeshouEntity> wrapper);
	

}
