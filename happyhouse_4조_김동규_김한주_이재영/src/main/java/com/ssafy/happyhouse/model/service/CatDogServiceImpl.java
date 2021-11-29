package com.ssafy.happyhouse.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.CatDogDto;
import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.mapper.CatDogMapper;
import com.ssafy.util.PageNavigation;

@Service
public class CatDogServiceImpl implements CatDogService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeArticle(CatDogDto catDogDto) throws Exception {
		// TODO Auto-generated method stub
		if(catDogDto.getSubject() == null || catDogDto.getContent() == null) {
			throw new Exception();
		}
		CatDogMapper catDogMapper = sqlSession.getMapper(CatDogMapper.class);
		int insert =catDogMapper.writeArticle(catDogDto);
		List<FileInfoDto> fileInfos = catDogDto.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			catDogMapper.registerFile(catDogDto);
		}
		return insert == 1;
		
	}

	@Override
	public List<CatDogDto> listArticle(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(CatDogMapper.class).listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(CatDogMapper.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		
		return pageNavigation;
	}

	@Override
	public CatDogDto getArticle(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CatDogMapper.class).getArticle(articleNo);
	}
	
	@Override
	public List<FileInfoDto> getFile(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CatDogMapper.class).getFile(articleNo);
	}

	@Override
	@Transactional
	public void updateArticle(CatDogDto catDogDto) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.getMapper(CatDogMapper.class).updateArticle(catDogDto);
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleNo, String path) throws Exception {
		// TODO Auto-generated method stub
		CatDogMapper catDogMapper = sqlSession.getMapper(CatDogMapper.class);
		List<FileInfoDto> fileList = catDogMapper.getFile(articleNo);
		catDogMapper.deleteFile(articleNo);
		catDogMapper.deleteArticle(articleNo);
		for(FileInfoDto fileInfoDto : fileList) {
			File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
			file.delete();
		}
		return true;
	}

	@Override
	public List<FileInfoDto> listFile(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(CatDogMapper.class).listFile(param);
	}

}
