package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeArticle(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
//		sqlSession.getMapper(BoardMapper.class).deleteMemo(articleno);
		return sqlSession.getMapper(BoardMapper.class).deleteArticle(articleno) == 1;
	}

	@Override
	public BoardDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getArticle(articleno);
	}

	@Override
	public Integer totalArticle(BoardParameterDto boardParameterDto) throws Exception {
		int last = sqlSession.getMapper(BoardMapper.class).totalArticle();
		return (last - 1) / boardParameterDto.getSpp() + 1;
		
	}

	
}
