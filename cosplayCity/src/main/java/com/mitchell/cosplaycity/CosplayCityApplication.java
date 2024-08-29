package com.mitchell.cosplaycity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mitchell.cosplaycity.services.FilesService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class CosplayCityApplication 
//implements CommandLineRunner 
{
	
//	@Resource
//	FilesService filesService;
	
	public static void main(String[] args) {
		SpringApplication.run(CosplayCityApplication.class, args);
	}
	
//	@Override
//	public void run(String... arg) throws Exception{
//		this.filesService.init();
//	}

}
