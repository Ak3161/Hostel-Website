package in.sp.main.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import in.sp.main.Student;
import in.sp.main.repo.Student_data;

@Service
public class StudentService {
	@Autowired
	private Student_data student_data;
	
	@Value("${razorpay.key.id}")
	private String razorPayKey;
	
	@Value("${razorpay.secret.key}")
	private String razorPaySecret;
	
	private RazorpayClient client;
	
	public Student createOrder(Student pay) throws Exception {
		if (razorPayKey == null || razorPayKey.isEmpty() || razorPaySecret == null || razorPaySecret.isEmpty()) {
            throw new IllegalArgumentException("Razorpay API keys are not set! Check application.properties.");
        }

        this.client = new RazorpayClient(razorPayKey, razorPaySecret);
		JSONObject payReq = new JSONObject();
		
	payReq.put("amount", pay.getAmount()*100); //It will amount in paisa
	payReq.put("currency", "INR");
	payReq.put("receipt", pay.getEmail());
	
	this.client = new RazorpayClient(razorPayKey, razorPaySecret);
		//It create the Order in Razorpay
		Order razorPayOrder = client.orders.create(payReq);
		System.out.println(razorPayOrder);
		pay.setRazorpayOrderId(razorPayOrder.get("id"));
		pay.setOrderStatus(razorPayOrder.get("status"));
		student_data.save(pay);
		return pay;
	}
	
	public Student updateOrder(Map<String,String> responsePayLoad) {
		String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
		
		Student order = student_data.findByRazorpayOrderId(razorPayOrderId);
		order.setOrderStatus("PAYMENT_COMPLETED");
		Student updatedOrder = student_data.save(order);
		return updatedOrder;
	}
	
	
	
	
	
}
