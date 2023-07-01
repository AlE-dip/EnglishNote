package com.ale.englishnote;

import com.ale.englishnote.repository.WordRepository;
import com.ale.englishnote.service.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class EnglishNoteApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EnglishNoteApplication.class, args);

		FileService fileService = context.getBean(FileService.class);
		fileService.importDefault();
	}

}
