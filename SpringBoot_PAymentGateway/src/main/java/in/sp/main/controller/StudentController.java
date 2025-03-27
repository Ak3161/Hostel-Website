package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.sp.main.Student;
import in.sp.main.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService service;
	@GetMapping("/")
	public String init() {
		return "index";
	}
	
	@PostMapping(value="/create-order", produces="application/json")
	@ResponseBody
	public ResponseEntity<Student> createOrder(Student student) throws Exception{
	Student createdOrder =	service.createOrder(student);
	return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}
}
