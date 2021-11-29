package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.util.PageNavigation;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeArticle(NoticeDto noticeDto) throws Exception {
		if(noticeDto.getSubject() == null || noticeDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(NoticeMapper.class).writeArticle(noticeDto) == 1;
	}

	@Override
	public List<NoticeDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(NoticeMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(NoticeDto noticeDto) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).modifyArticle(noticeDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
//		sqlSession.getMapper(NoticeMapper.class).deleteMemo(articleno);
		return sqlSession.getMapper(NoticeMapper.class).deleteArticle(articleno) == 1;
	}

	@Override
	public NoticeDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getArticle(articleno);
	}

	@Override
	public Integer totalArticle(BoardParameterDto BoardParameterDto) throws Exception {
		int last = sqlSession.getMapper(NoticeMapper.class).totalArticle();
		return (last - 1) / BoardParameterDto.getSpp() + 1;
		
	}

	
}
