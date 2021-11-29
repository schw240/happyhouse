package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NewsParameterDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/news")
@Api("게시판 컨트롤러")
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	
	@Autowired
	private NewsService newsService;
	
	
	@GetMapping
	public ResponseEntity<List<NewsDto>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) NewsParameterDto newsParameterDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<NewsDto>>(newsService.listArticle(newsParameterDto), HttpStatus.OK);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<NewsDto> getArticle(@PathVariable("title") @ApiParam(value = "얻어올 뉴스의 회사명.", required = true) String title) throws Exception {
		logger.info("getArticle - 호출 : " + title);
		return new ResponseEntity<NewsDto>(newsService.getArticle(title), HttpStatus.OK);
	}
	
}
