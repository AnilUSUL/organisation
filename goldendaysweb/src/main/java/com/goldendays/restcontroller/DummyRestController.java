package com.goldendays.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldendays.dto.Dto;

@RestController
public class DummyRestController extends BaseRestController{

	@RequestMapping(value = "/restGreetMap")
	public Map<String, String> greetMap(String name) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("ad:", "ANIL");
		map.put("soyad:", "USUL");
		map.put("yas:", "29");
		return map;
	}
	
	@RequestMapping(value = "/restGreetObj")
	public Dto greetObj(String name) {
		Dto dto = new Dto();
		dto.setName("ANIL");
		dto.setSurName("USUL");
		dto.setAge(29);
		return dto;
	}
	
	@RequestMapping(value = "/restGreetArr")
	public List<Dto> greetArr(String name) {
		List<Dto> list = new ArrayList<Dto>();
		Dto dto1 = new Dto();
		dto1.setName("ANIL");
		dto1.setSurName("USUL");
		dto1.setAge(29);
		Dto dto2 = new Dto();
		dto2.setName("ANIL");
		dto2.setSurName("USUL");
		dto2.setAge(29);
		Dto dto3 = new Dto();
		dto3.setName("ANIL");
		dto3.setSurName("USUL");
		dto3.setAge(29);
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		return list;
	}
}
