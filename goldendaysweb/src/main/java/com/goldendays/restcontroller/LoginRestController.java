package com.goldendays.restcontroller;

import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldendays.config.util.JwtUtil;
import com.goldendays.contract.IUserService;
import com.goldendays.contract.modelobjects.JwtToken;
import com.goldendays.contract.modelobjects.Person;
import com.goldendays.dto.JwtTokenClaimDto;
import com.goldendays.formbean.UserBean;

@RestController
public class LoginRestController extends BaseRestController {

	@Autowired
	private IUserService userService;

	/**
	 * Login authentication service
	 * @throws ServletException 
	 * @throws Exception 
	 * 
	 **/
	@RequestMapping("/login")
	public String login(UserBean user) throws ServletException  {
		String jwtToken = "";
		Person person = new Person();
		person.setEmail(user.getEmail());
		person.setPassword(user.getPass());
		ArrayList<Person> resultPersonel = userService.findByEmailAndPass(person);
		if (resultPersonel == null || resultPersonel.isEmpty()) {
			return "redirect://localhost:8080/goldendaysweb/rest/login";
		} else {
			//reauthentication
			
			JwtTokenClaimDto jwtTokenClaimDto = new JwtTokenClaimDto();
			jwtTokenClaimDto.setEmail(user.getEmail());
			jwtTokenClaimDto.setId(null);
			jwtTokenClaimDto.setIssuer("dirtywall.com");
			jwtTokenClaimDto.setTtlMillis(30*60000);//minutes * 60000 = miliseconds 
			
			
			//authenticated with token user 
			if(user.getToken() != null && !user.getToken().isEmpty()){
				JwtUtil.invalidToken(userService,user.getToken(),user.getEmail());
				return "redirect://localhost:8080/goldendaysweb/rest/login" + "token invalidated, already invalidated or expired ";
			}
			
			//authenticated tokenless user and unauthenticated users
			JwtToken jwt = new JwtToken();
			jwt.setJwtToken(jwtToken);
			jwt.setSubject(user.getEmail());
			JwtUtil.deleteToken(userService, jwt);
			jwtToken = JwtUtil.createJWT(userService, jwtTokenClaimDto);

			return "Login Successful! And Your JWT Token : " +jwtToken;
		}

	}

	/**
	 * Logout service
	 * 
	 **/
	@RequestMapping("/logout")
	public String logout(UserBean user) {
		JwtToken jwtToken = new JwtToken();
		jwtToken.setSubject(user.getEmail());
		jwtToken.setSubject(user.getPass());
		jwtToken.setJwtToken(user.getToken());
		userService.deleteToken(jwtToken);
		return "redirect://localhost:8080/goldendaysweb/rest/logout";
	}

	/**
	 * Signup service
	 * 
	 **/
	@RequestMapping("/signUp")
	public String signUp(UserBean user) {
		Person person = new Person();
		person.setEmail(user.getEmail());
		person.setPassword(user.getPass());
		person.setName(user.getName());
		if (userService.addPerson(person) < 1) {
			return "redirect://localhost:8080/goldendaysweb/rest/signUp";
		}
		return "redirect://localhost:8080/goldendaysweb/rest/login";
	}

	@RequestMapping("/restMethod")
	public int restMethod(UserBean user) {
		return 1;
	}

	/**
	 * Add user to system
	 * 
	 **/
	private int loginSuccess(UserBean user) {
		Person person = new Person();
		person.setId(user.getId());
		person.setEmail(user.getEmail());
		person.setName(user.getName());
		person.setPassword(user.getPass());
		return userService.addPerson(person);
	}

	/**
	 * Email exist control validation
	 * 
	 **/
	private Boolean findByEmail(String email) {
		return null != userService.findByEmail(email) ? true : false;

	}

	/**
	 * Update user information
	 * 
	 **/
	private int updateUser(UserBean user) {
		Person person = new Person();
		person.setId(user.getId());
		person.setEmail(user.getEmail());
		person.setName(user.getName());
		person.setPassword(user.getPass());
		return userService.editPerson(person, user.getId());
	}
}