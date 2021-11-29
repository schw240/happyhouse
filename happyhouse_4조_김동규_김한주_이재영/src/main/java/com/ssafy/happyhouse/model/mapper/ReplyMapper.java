package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.ReplyDto;
@Mapper
public interface ReplyMapper {

	int writeReply(ReplyDto replyDto) throws SQLException;
	List<ReplyDto> listArticle(int boardid) throws SQLException;
	int deleteReply(int replyno) throws SQLException;
	int modifyReply(ReplyDto replyDto) throws SQLException;

}
