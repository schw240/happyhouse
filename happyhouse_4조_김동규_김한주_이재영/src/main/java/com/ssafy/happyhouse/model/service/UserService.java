package com.ssafy.happyhouse.model.service;


import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {

	void registerMember(UserDto userDto) throws Exception;

	UserDto login(UserDto userDto) throws Exception;

	void updateUser(UserDto userDto) throws Exception;

	void deleteUser(String userid) throws Exception;

	UserDto userInfo(String userid) throws Exception;
	
	int idcheck(String chkid) throws Exception;
	
	void updateInterestArea(Map<String,String> params) throws Exception;
	
	String convertCodeToString(String code) throws Exception;
	
	List<String> getInterestAreaById(String userid)throws Exception;
	
	void deleteAreaById(String userid) throws Exception;
	void insertAreaById(Map<String,String> params) throws Exception;
}
