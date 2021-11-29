package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SidoGugunCodeDto : 시도, 구군", description = "시도 구군 코드와 이름 정보를 나타냄.")
public class SidoGugunCodeDto {

	@ApiModelProperty(value = "시도 코드")
	private String sidoCode;
	@ApiModelProperty(value = "시도 이름", example = "서울 특별시")
	private String sidoName;
	@ApiModelProperty(value = "구군 코드")
	private String gugunCode;
	@ApiModelProperty(value = "구군 이름", example = "도봉구")
	private String gugunName;
	
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	
	
	
	
}
