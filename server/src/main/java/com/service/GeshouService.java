package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GeshouEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GeshouVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GeshouView;


/**
 * 歌手
 *
 * @author 
 * @email 
 * @date 2023-04-24 22:37:44
 */
public interface GeshouService extends IService<GeshouEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GeshouVO> selectListVO(Wrapper<GeshouEntity> wrapper);
   	
   	GeshouVO selectVO(@Param("ew") Wrapper<GeshouEntity> wrapper);
   	
   	List<GeshouView> selectListView(Wrapper<GeshouEntity> wrapper);
   	
   	GeshouView selectView(@Param("ew") Wrapper<GeshouEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GeshouEntity> wrapper);
   	

}

