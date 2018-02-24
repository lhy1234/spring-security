package com.imooc.web.controller;

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
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/query") //路径
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
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/detail/1")
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
		mockMvc.perform(MockMvcRequestBuilders.get("/user/detail/a") //匹配正则
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
				
	}
	
	
}
