package com.ale.englishnote;

import com.ale.englishnote.service.FileParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EnglishNoteApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EnglishNoteApplication.class, args);

		FileParser fileParser = context.getBean(FileParser.class);
		fileParser.getFileData();
	}

}
