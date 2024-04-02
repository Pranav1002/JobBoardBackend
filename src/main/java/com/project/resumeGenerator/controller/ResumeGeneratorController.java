package com.project.resumeGenerator.controller;


import com.project.resumeGenerator.model.Resume1;
import com.project.resumeGenerator.util.PdfOneGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/jobseeker")
public class ResumeGeneratorController {

	@Autowired
	private PdfOneGenerator pdfGen;

	@PostMapping(path = "/resume")
	public ResponseEntity<String> postResume(@Valid @RequestBody Resume1 resume) throws IOException {


		return new ResponseEntity<String>(pdfGen.createDocument(resume), HttpStatus.OK);
	}

	@GetMapping(path = "/resumef")
	public ResponseEntity<byte[]> getResume(@RequestParam("filename") String filename) throws IOException {

		return new ResponseEntity<byte[]>(pdfGen.getDocument(filename), HttpStatus.OK);
	}
}
