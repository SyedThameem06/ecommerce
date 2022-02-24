/**
 * 
 * Author Syed Thameem
 * 
 * File Name: EshopApplicationTests
 * File Description: Spring Boot Test application.
 * 
 */
package com.abc.eshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.eshop.EshopApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApplication.class)
public class EshopApplicationTests {

	
	@Test
	public void contectLoad() {
		assertEquals(0, 0);
	}

}
