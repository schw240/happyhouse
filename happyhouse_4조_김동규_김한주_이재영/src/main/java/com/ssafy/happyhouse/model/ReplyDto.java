package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ReplyDto : 댓글 정보", description = "댓글의 상세 정보를 나타낸다.")
public class ReplyDto {
	@ApiModelProperty(value = "댓글 번호")
	private int replyno;
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;
	@ApiModelProperty(value = "작성자 이름")
	private String username;
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	@ApiModelProperty(value = "댓글이 작성된 글의 번호")
	private int boardid;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	@ApiModelProperty(value = "댓글의 레벨. 대댓글 작성시 필요.")
	private int level;
	public int getReplyno() {
		return replyno;
	}
	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
