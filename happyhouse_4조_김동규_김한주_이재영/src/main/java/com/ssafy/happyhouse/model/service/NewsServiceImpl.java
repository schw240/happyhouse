package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NewsParameterDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<NewsDto> listArticle(NewsParameterDto newsParameterDto) throws Exception {
		int start = newsParameterDto.getPg() == 0 ? 0 : (newsParameterDto.getPg() - 1) * newsParameterDto.getSpp();
		newsParameterDto.setStart(start);
		return sqlSession.getMapper(NewsMapper.class).listArticle(newsParameterDto);
	}

	@Override
	public NewsDto getArticle(String title) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(NewsMapper.class).getArticle(title);
	}
}
