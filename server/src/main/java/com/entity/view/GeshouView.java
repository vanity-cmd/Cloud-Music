package com.entity.view;

import com.entity.GeshouEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 歌手
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2023-04-24 22:37:44
 */
@TableName("geshou")
public class GeshouView  extends GeshouEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public GeshouView(){
	}
 
 	public GeshouView(GeshouEntity geshouEntity){
 	try {
			BeanUtils.copyProperties(this, geshouEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
