package com.asa.controller.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/** * @author  作者 : 范德胜
  * @date 创建时间：2017年4月20日 下午3:20:23
  * @version 1.0 
  */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext*.xml","classpath:spring-servlet.xml"})
@ActiveProfiles({"unittest","development"})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = false)
public class UserControllerTest {
	
	@Autowired  
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

	/**
	 * GET接口测试模板
	 * @throws Exception 
	 */
	@Test
	public void getTemplate() throws Exception{
		mockMvc.perform(get("/test")
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON)
				)
		 		 .andExpect(status()
				 .isOk())
		 		 .andDo(print());
	}
	
	/**
	 * POST接口测试模板
	 */
	@Test
	public void postTemplate(){
		/*MessageTemplateDto dto = new MessageTemplateDto();
		dto.setContent("jhq,test");
		dto.setNumber("1");
		dto.setPushTime(new Date().getTime());
		dto.setReferencesNo("0");
		dto.setResource(MessageTemplate.RESOURCE_SYSTEM);
		dto.setTemplateName("test");
		
		String jsonStr = new ObjectMapper().writeValueAsString(dto);
		System.out.println("\n\n"+jsonStr+"\n\n");
		 
		mockMvc.perform(post("/api/saveMessageTemplate")
				 .characterEncoding("UTF-8")
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON)
				 .characterEncoding("UTF-8")
				 .content(jsonStr))
		 		 .andExpect(status()
				 .isOk())
		 		 .andDo(print());*/
	}
	
}
