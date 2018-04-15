package com.example.app02.mgmt;

import com.example.app02.libdemo.domain.Book;
import com.example.app02.libdemo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.example.app02")
@EnableJpaRepositories(basePackages = "com.example.app02")
@EntityScan("com.example.app02")
@EnableScheduling
@EnableCaching
public class DemoApplication {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService appService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

	@Bean CommandLineRunner initAppData(ApplicationContext ctx ) {
		return args -> {
			logger.info("initAppData ... ");
			Book app = new Book();
			app.setId("app321");
			app.setName("appName321");
			appService.saveApp(app);

			logger.info("save one app");
			List<Book> apps = appService.getBooks();
			logger.info("app size: {}", apps.size());
		};
	}
}
