package com.asa.user.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.asa.user.service.UserService;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年4月20日 下午3:29:18
  * @version 1.0 
  */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext*.xml"})
@ActiveProfiles({ "unittest", "development" })
@TransactionConfiguration(defaultRollback = false)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void xmlTest(){
		System.out.println("111");
	}

}
