package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NewsParameterDto;

public interface NewsService {

	public List<NewsDto> listArticle(NewsParameterDto newsParameterDto) throws Exception;
	public NewsDto getArticle(String title) throws Exception;
}
