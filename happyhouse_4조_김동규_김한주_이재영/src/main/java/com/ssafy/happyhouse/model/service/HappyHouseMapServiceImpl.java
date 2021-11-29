package com.ssafy.happyhouse.model.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;


@Service
public class HappyHouseMapServiceImpl implements HappyHouseMapService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		// TODO Auto-generated method stub
		
		List<HouseInfoDto> list = sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
		for(HouseInfoDto hdto : list) {
			System.out.println(hdto.getAptCode());
		}
		// 이름순 정렬
//		list = list.stream().sorted(Comparator.comparing(HouseInfoDto::getAptName)).collect(Collectors.toList());

		// 숫자로 정렬
		list = list.stream().sorted(Comparator.comparingInt(HouseInfoDto::getBuildYear)).collect(Collectors.toList());

		return list;
//		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getAptSearchByName(String aptname) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapMapper.class).getAptSearchByName(aptname);
	}

	@Override
	public List<HouseInfoDto> AutoComplete(String key) throws Exception {
//		return sqlSession.getMapper(HouseMapMapper.class).AutoComplete(key).subList(0, 3);
		List<HouseInfoDto> list = sqlSession.getMapper(HouseMapMapper.class).AutoComplete(key);
		if(list.size() >= 3) {
			list.subList(0, 3);
		}
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getAptName());
//		}
		return list; 
	}

}
