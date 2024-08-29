package com.mitchell.cosplaycity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mitchell.cosplaycity.services.FilesService;

@Controller
public class FilesController {
	
	@Autowired
	private FilesService filesService;
	
	@GetMapping("/uploads/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(
			@PathVariable String filename) {
		Resource file = filesService.load(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

}
