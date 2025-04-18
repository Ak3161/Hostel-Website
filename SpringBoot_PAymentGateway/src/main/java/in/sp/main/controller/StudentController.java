package in.sp.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.sp.main.Student;
import in.sp.main.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    // Home page - front.html
    @GetMapping("/")
    public String init() {
        return "front";  
    }

    // After payment success - index.html
    @GetMapping("index")
    public String showIndex() {
        return "index"; // Make sure index.html is in src/main/resources/templates
    }

    // Razorpay callback
    @PostMapping("/handel-payment-callback")
    public String handlePaymentCallback(@RequestParam Map<String, String> respPayload) {
        System.out.println(respPayload);
        Student updateOrder = service.updateOrder(respPayload);
        return "redirect:/index";  // Or return "success" if you have success.html
    }

    // Create order from frontend
    @PostMapping(value="/create-order", produces="application/json")
    @ResponseBody
    public ResponseEntity<Student> createOrder(@RequestBody Student student) throws Exception {
        Student createdOrder = service.createOrder(student);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
