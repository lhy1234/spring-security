package com.imooc.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webCtx;
	
	//伪造mvc
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build();
	}
	
	/**
	 * 查询
	 */
	@Test
	public void whenQuerySuccess() throws Exception{
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user") //路径
				.param("page", "10")   //参数
				.param("size", "12")
				.param("sort", "age,desc")
				.param("username", "xiaoming") 
				.param("age", "18")
				.param("ageTo", "40")
				.param("other", "otherProperty")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()) //状态码200
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))//长度为3，具体查看github的jsonPath项目	
				.andReturn().getResponse().getContentAsString();
		System.err.println(result);
	}
	
	/**
	 * 详情
	 */
	@Test
	public void whenGetInfoSuccess() throws Exception{
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();
		System.err.println(result);
	}
	
	/**
	 * 详情失败
	 */
	@Test
	public void whenGetInfoFail() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/a") //匹配正则
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void whenCreateSuccess() throws Exception{
		long date = new Date().getTime();
		System.err.println(">>>>>>>>"+date);
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date+"}";
		 String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				 .contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(content))
		 		 .andExpect(MockMvcResultMatchers.status().isOk())
		 		 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
		 		 .andReturn().getResponse().getContentAsString();
		 System.err.println(result);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception{
		//jdk8新增，当前日期加一年，测试生日@past注解
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.err.println(">>>>>>>>"+date);
		String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		 String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1") //put
				 .contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(content))
		 		 .andExpect(MockMvcResultMatchers.status().isOk())
		 		 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
		 		 .andReturn().getResponse().getContentAsString();
		 System.err.println("update result>>>>> "+result);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
