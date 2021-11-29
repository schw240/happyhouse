package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.NoticeDto;

@Mapper
public interface NoticeMapper {

	int writeArticle(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	void updateHit(int articleno) throws Exception;
	NoticeDto getArticle(int articleno) throws Exception;
	public int modifyArticle(NoticeDto noticeDto) throws SQLException;
//	public void deleteMemo(int articleno) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	int totalArticle() throws SQLException;
}
