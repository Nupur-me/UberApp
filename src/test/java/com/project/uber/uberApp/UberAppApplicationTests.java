package com.project.uber.uberApp;

import com.project.uber.uberApp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail("hopasot721@degcos.com",
				"This is the Testing Email",
				"Body of my Email");
	}

	@Test
	void sendEmailMultiple(){
		String[] emails = {
				"hopasot721@degcos.com",
				"nupurkumari.7a@gmail.com",
				"190304105117@paruluniversity.ac.in",
				"nupurkumari.0708@gmail.com",
				"nupur.shrivastava.070@gmail.com"
		};
		emailSenderService.sendEmail(emails,
				"Hello from the Uber Application Demo",
				"Keep Coding!!");
	}

}
