package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HappyHouseMapService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/map")
@CrossOrigin
public class HappyHouseMapController {

	@Autowired
	private HappyHouseMapService happyHouseMapService;
	
	@ApiOperation(value = "시도 리스트를 받아온다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>> (happyHouseMapService.getSido(), HttpStatus.OK);
	}
	@ApiOperation(value = "시도 코드를 입력받아서 구군 리스트를 받아온다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>> (happyHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	@ApiOperation(value = "구군 코드를 입력받아서 동 리스트를 받아온다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>> (happyHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	@ApiOperation(value = "검색어를 입력받아서 자동완성될 단어 리스트를 받아온다.", response = List.class)
	@PostMapping("/auto")
	public ResponseEntity<List<HouseInfoDto>> AutoComplete(@RequestParam("key") String key) throws Exception {
		List<HouseInfoDto> list = happyHouseMapService.AutoComplete(key);
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getAptName());
//		}
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	@ApiOperation(value = "검색어로 동을 입력받아서 아파트 리스트를 받아온다.", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		
		return new ResponseEntity<List<HouseInfoDto>> (happyHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	@ApiOperation(value = "검색어로 아파트 이름을 입력받아서 아파트 리스트를 받아온다.", response = List.class)
	@GetMapping("/aptname")
	public ResponseEntity<List<HouseInfoDto>> exactapt(@RequestParam("aptname") String aptname) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>> (happyHouseMapService.getAptSearchByName(aptname), HttpStatus.OK);
	}
}
