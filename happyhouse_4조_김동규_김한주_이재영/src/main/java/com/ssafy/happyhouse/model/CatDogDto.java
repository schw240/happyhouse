package com.ssafy.happyhouse.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CatDogDto : 캣독 정보", description = "캣독의 정보를 나타낸다.")
public class CatDogDto {
	@ApiModelProperty(value = "게시글 번호")
	private int articleno; // 게시글 번호
	@ApiModelProperty(value = "게시글 제목")
	private String subject;// 제목
	@ApiModelProperty(value = "게시글 날짜")
	private String regTime;// 날짜
	@ApiModelProperty(value = "게시글 작성자")
	private String userid;// 작성자(유저)
	@ApiModelProperty(value = "게시글 파일")
	private List<FileInfoDto> fileInfos;// 사진(파일)
	@ApiModelProperty(value = "게시글 내용")
	private String content;// 내용
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
