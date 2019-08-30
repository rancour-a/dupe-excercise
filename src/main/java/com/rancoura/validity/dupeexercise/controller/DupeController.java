package com.rancoura.validity.dupeexercise.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rancoura.validity.dupeexercise.service.CompareService;


@Controller
@RequestMapping("/")
public class DupeController {

	private static final Logger logger = LoggerFactory.getLogger(DupeController.class);
	
	@Autowired
	CompareService compareService;

	@RequestMapping(value = "/duplicates", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, List<String>>> getDuplicates() {

		try {
			return ResponseEntity.ok(compareService.findDuplicates());
		} catch (Exception e) {
			logger.error("Something went wrong: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(value = "/")
	public String index(Model model) {
        model.addAttribute("dupeMap", compareService.findDuplicates());
		return "index";
	}
}