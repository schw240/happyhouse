package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.mapper.ReplyMapper;
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeReply(ReplyDto replyDto) throws Exception {
		// 내용이 없거나 로그인이 풀린 경우
		if(replyDto.getContent() == null || replyDto.getUserid() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(ReplyMapper.class).writeReply(replyDto) == 1;
	}

	@Override
	public List<ReplyDto> listReply(int boardid) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).listArticle(boardid);
	}

	@Override
	public boolean deleteReply(int replyno) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).deleteReply(replyno) == 1;
	}

	@Override
	public boolean modifyReply(ReplyDto replyDto) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).modifyReply(replyDto) == 1;
	}

}
