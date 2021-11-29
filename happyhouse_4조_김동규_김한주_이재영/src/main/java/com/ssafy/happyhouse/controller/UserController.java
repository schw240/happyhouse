package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "회원가입. 회원 정보를 입력한다. 아직 성공여부에 따른 반환값은 정해지지 않음 ")
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @ApiParam(value = "회원가입시 필요한 회원정보", required = true)UserDto userDto) throws Exception {
		userService.registerMember(userDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
	

	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");	// key, data, subject
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("로그인 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(jwtService.isUsable(request.getHeader("access-token"))) {
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String,Object>> (resultMap, status);
	}
	
	//=======================================================================================
	//여기부터 구현 :  수정 / 탈퇴 ,  : 완료
//	아이디 중복체크  : 완료
//	관심지역 등록 : 완료
	
	@ApiOperation(value = "로그아웃. 세션을 만료해서 회원 정보 접근을 막음")
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 정보수정
	@ApiOperation(value = "회원 정보 수정. 회원정보를 보낸다. 수정 화면에 기존 정보를 살려서 표현하여 변경하고자 하는 정보만 입력할 수 있도록 제공", response = UserDto.class)
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody @ApiParam(value = "변경할 회원정보", required = true) UserDto userDto) throws Exception {
		System.out.println(userDto.toString());
		userService.updateUser(userDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	@ApiOperation(value = "회원정보 삭제. 현재 로그인 된 회원의 정보를 삭제하고 token을 없앤다.")
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable("userid") @ApiParam(value = "삭제할 회원의 아이디.", required = true) String userid) throws Exception {
		userService.deleteUser(userid);		
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@ApiOperation(value ="아이디 중복검사", notes="String을 받아서 db에 일치하는 아이디가 있는지 확인")
	@GetMapping("/idcheck/{chkid}")
	public ResponseEntity<Integer> idcheck(@PathVariable("chkid") @ApiParam(value="검사할 아이디",required=true) String chkid) throws Exception{
		
		int isExist=userService.idcheck(chkid);
		
		return new ResponseEntity<Integer>(isExist,HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심지역 등록",notes="원하는 1개의 지역을 관심지역으로 등록")
	@PutMapping("/updateArea")
	public ResponseEntity<String> updateInterestArea(@RequestParam @ApiParam(value = "유저아이디 , 변경할 관심지역정보", required = true) Map<String,String> params) throws Exception {
//		System.out.println(userDto.toString());
		logger.info("map: " + params.get("userid") + " " +params.get("dongcode"));
		userService.updateInterestArea(params);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value ="dongcode를 주소로", notes="code를 주소로변환")
	@GetMapping("/convert")
	public ResponseEntity<String> convertCodeToString(@RequestParam("code") @ApiParam(value="바꿀 코드",required=true) String code) throws Exception{
		logger.info("code: "+code);
		String addr =userService.convertCodeToString(code);
		
		return new ResponseEntity<String>(addr,HttpStatus.OK);
	}
	
	@ApiOperation(value="관심지역 리스트 ", notes="해당 유저의 관심지역 리스트를 불러온다.")
	@GetMapping("/getArea/{userid}")
	public ResponseEntity<Map<String, Object>> getInterestAreaById(@PathVariable("userid") String userid) throws Exception{
		logger.info(userid);
		List<String> list = userService.getInterestAreaById(userid);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(list.size()>0) {
			logger.info("area가져오기"+list.get(0));
			resultMap.put("message",SUCCESS);
			resultMap.put("areas",list);
		}
		else {
			resultMap.put("message",FAIL);
			
		}
			return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
			
	}
	
	@ApiOperation(value ="아이디의 동주소 모두삭제", notes="아이디로 동코드 다삭제")
	@DeleteMapping("/delArea")
	public ResponseEntity<String> deleteAreaById(@RequestParam("userid") @ApiParam(value="삭제할아이디",required=true) String userid) throws Exception{
		
		userService.deleteAreaById(userid);
		
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	@ApiOperation(value ="관심지역등록", notes="아이디로 코드등록")
	@PostMapping("/insertArea")
	public ResponseEntity<String> insertAreaById(@RequestParam @ApiParam(value="넣을 아이디 / 지역코드",required=true) Map<String,String> params) throws Exception{
		logger.info(params.get("userid"));
		userService.insertAreaById(params);
		 
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
//	ajax로 로그인
//	@PostMapping("/login")
//	@ResponseBody
//	public ResponseEntity<UserDto> login(@RequestBody Map<String, String> map, Model model, HttpSession session,
//			HttpServletResponse response) throws Exception {
//		System.out.println("로그인" + map.get("ssafy"));
//		UserDto userDto = userService.login(map);
////		System.out.println(userDto.toString());
//		if (userDto != null) {
//			session.setAttribute("userinfo", userDto);
//		}
//		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
//	}
}
