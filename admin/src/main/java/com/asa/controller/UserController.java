package com.asa.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asa.core.common.SystemCommon;

/** 
  *   @author  作者 : 范德胜
  *   @date 创建时间：2017年4月20日 下午3:06:51
  *   @version 1.0 
  *   
  */

@Controller
public class UserController {

	@RequestMapping(value = "/test", method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE + SystemCommon.CHARSET)
	//@Privileges(type=SystemCommon.PRIVILEGE_TYPE_BASIC,requestType="GET",
                  //name = "修改模板名称", remark = "修改模板名称")
	public void testMethod() {
		System.out.println("222");
	}

}
