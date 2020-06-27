package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Interview.InterviewModel;


@RestController
@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/")
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		Interview test = null;
		
		for (int i = 0; i < 10; i ++) {
			test = new Interview();
			test.setInterview(new InterviewModel("test" + i, "OK" ));
			InterviewController.ilist.add(test);
		}
	}

}
