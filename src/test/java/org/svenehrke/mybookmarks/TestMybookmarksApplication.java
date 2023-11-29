package org.svenehrke.mybookmarks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestMybookmarksApplication {

	public static void main(String[] args) {
		SpringApplication.from(MybookmarksApplication::main).with(TestMybookmarksApplication.class).run(args);
	}

}
