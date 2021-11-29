package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NewsParameterDto;
import com.ssafy.happyhouse.model.NewsDto;

@Mapper
public interface NewsMapper {

	// 뉴스는 등록이 아닌 불러오기만
	List<NewsDto> listArticle(NewsParameterDto newsParameterDto) throws Exception;
	NewsDto getArticle(String title) throws Exception;
}
