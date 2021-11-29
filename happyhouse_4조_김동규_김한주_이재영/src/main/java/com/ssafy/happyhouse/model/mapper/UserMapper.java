package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;
@Mapper
public interface UserMapper {

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
