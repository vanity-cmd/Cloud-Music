package com.entity.vo;

import com.entity.YinlexinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 音乐信息
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2023-04-24 22:37:43
 */
public class YinlexinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 歌手
	 */
	
	private String singer;
		
	/**
	 * 音乐类型
	 */
	
	private String yinleleixing;
		
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 编曲
	 */
	
	private String bianqu;
		
	/**
	 * 填词
	 */
	
	private String tianci;
		
	/**
	 * 语言
	 */
	
	private String yuyan;
		
	/**
	 * 专辑
	 */
	
	private String zhuanji;
		
	/**
	 * 时长
	 */
	
	private String shizhang;
		
	/**
	 * 发行方
	 */
	
	private String faxingfang;
		
	/**
	 * 发行日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date faxingriqi;
		
	/**
	 * 歌词
	 */
	
	private String geci;
		
	/**
	 * 音乐文件
	 */
	
	private String songfile;
		
	/**
	 * 账号
	 */
	
	private String zhanghao;
		
	/**
	 * 昵称
	 */
	
	private String nicheng;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
				
	
	/**
	 * 设置：歌手
	 */
	 
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	/**
	 * 获取：歌手
	 */
	public String getSinger() {
		return singer;
	}
				
	
	/**
	 * 设置：音乐类型
	 */
	 
	public void setYinleleixing(String yinleleixing) {
		this.yinleleixing = yinleleixing;
	}
	
	/**
	 * 获取：音乐类型
	 */
	public String getYinleleixing() {
		return yinleleixing;
	}
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
				
	
	/**
	 * 设置：编曲
	 */
	 
	public void setBianqu(String bianqu) {
		this.bianqu = bianqu;
	}
	
	/**
	 * 获取：编曲
	 */
	public String getBianqu() {
		return bianqu;
	}
				
	
	/**
	 * 设置：填词
	 */
	 
	public void setTianci(String tianci) {
		this.tianci = tianci;
	}
	
	/**
	 * 获取：填词
	 */
	public String getTianci() {
		return tianci;
	}
				
	
	/**
	 * 设置：语言
	 */
	 
	public void setYuyan(String yuyan) {
		this.yuyan = yuyan;
	}
	
	/**
	 * 获取：语言
	 */
	public String getYuyan() {
		return yuyan;
	}
				
	
	/**
	 * 设置：专辑
	 */
	 
	public void setZhuanji(String zhuanji) {
		this.zhuanji = zhuanji;
	}
	
	/**
	 * 获取：专辑
	 */
	public String getZhuanji() {
		return zhuanji;
	}
				
	
	/**
	 * 设置：时长
	 */
	 
	public void setShizhang(String shizhang) {
		this.shizhang = shizhang;
	}
	
	/**
	 * 获取：时长
	 */
	public String getShizhang() {
		return shizhang;
	}
				
	
	/**
	 * 设置：发行方
	 */
	 
	public void setFaxingfang(String faxingfang) {
		this.faxingfang = faxingfang;
	}
	
	/**
	 * 获取：发行方
	 */
	public String getFaxingfang() {
		return faxingfang;
	}
				
	
	/**
	 * 设置：发行日期
	 */
	 
	public void setFaxingriqi(Date faxingriqi) {
		this.faxingriqi = faxingriqi;
	}
	
	/**
	 * 获取：发行日期
	 */
	public Date getFaxingriqi() {
		return faxingriqi;
	}
				
	
	/**
	 * 设置：歌词
	 */
	 
	public void setGeci(String geci) {
		this.geci = geci;
	}
	
	/**
	 * 获取：歌词
	 */
	public String getGeci() {
		return geci;
	}
				
	
	/**
	 * 设置：音乐文件
	 */
	 
	public void setSongfile(String songfile) {
		this.songfile = songfile;
	}
	
	/**
	 * 获取：音乐文件
	 */
	public String getSongfile() {
		return songfile;
	}
				
	
	/**
	 * 设置：账号
	 */
	 
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	
	/**
	 * 获取：账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
				
	
	/**
	 * 设置：昵称
	 */
	 
	public void setNicheng(String nicheng) {
		this.nicheng = nicheng;
	}
	
	/**
	 * 获取：昵称
	 */
	public String getNicheng() {
		return nicheng;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
			
}
