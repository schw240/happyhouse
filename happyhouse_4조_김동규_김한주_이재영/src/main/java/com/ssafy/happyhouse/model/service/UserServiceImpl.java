package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void registerMember(UserDto userDto) throws Exception {
		System.out.println("서비스: " + userDto.toString());
		sqlSession.getMapper(UserMapper.class).registerMember(userDto);
	}

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUserid() == null || userDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		sqlSession.getMapper(UserMapper.class).updateUser(userDto);
		
	}

	@Override
	public void deleteUser(String userid) throws Exception {
		sqlSession.getMapper(UserMapper.class).deleteUser(userid);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(userid);
	}
	
	@Override
	public int idcheck(String chkid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).idcheck(chkid);
	}
	@Override
	public void updateInterestArea(Map<String,String> params) throws Exception {
		sqlSession.getMapper(UserMapper.class).updateInterestArea(params);
		
	}
	@Override
	public String convertCodeToString(String code) throws Exception {
		
		return sqlSession.getMapper(UserMapper.class).convertCodeToString(code);
	}
	
	@Override
	public List<String> getInterestAreaById(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getInterestAreaById(userid);
	}
	@Override
	public void deleteAreaById(String userid) throws Exception {
		sqlSession.getMapper(UserMapper.class).deleteAreaById(userid);
		
	}
	@Override
	public void insertAreaById(Map<String, String> params) throws Exception {
		sqlSession.getMapper(UserMapper.class).insertAreaById(params);
		
	}
}
