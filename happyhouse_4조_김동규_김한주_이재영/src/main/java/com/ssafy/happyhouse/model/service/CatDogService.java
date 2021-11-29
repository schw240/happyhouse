package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.CatDogDto;
import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.util.PageNavigation;

public interface CatDogService {

	boolean writeArticle(CatDogDto catDogDto) throws Exception;
	List<CatDogDto> listArticle(Map<String, String> map) throws Exception;
	List<FileInfoDto> listFile(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	CatDogDto getArticle(int articleNo) throws Exception;
	void updateArticle(CatDogDto catDogDto) throws Exception;
	boolean deleteArticle(int articleNo, String path) throws Exception;
	List<FileInfoDto> getFile(int articleNo) throws Exception;
	
}
