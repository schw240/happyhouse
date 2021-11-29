package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseInfoDto : 아파트 정보", description = "아파트 정보를 나타낸다.")
public class HouseInfoDto implements Comparable<HouseInfoDto> {
	@ApiModelProperty(value = "아파트 코드")
	private String aptCode;
	@ApiModelProperty(value = "아파트 이름")
	private String aptName;
	@ApiModelProperty(value = "동 코드")
	private String dongCode;
	@ApiModelProperty(value = "동 이름")
	private String dongName;
	@ApiModelProperty(value = "시도 이름", example = "서울특별시")
	private String sidoName;
	@ApiModelProperty(value = "구군 이름", example = "도봉구")
	private String gugunName;
	@ApiModelProperty(value = "건축년도")
	private int buildYear;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "사진")
	private String img;
	@ApiModelProperty(value = "최근 거래가")
	private String recentPrice;
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}
	@Override
	public int compareTo(HouseInfoDto o) {
		// TODO Auto-generated method stub
		
		return this.buildYear - o.buildYear;
	}
	
	
}
