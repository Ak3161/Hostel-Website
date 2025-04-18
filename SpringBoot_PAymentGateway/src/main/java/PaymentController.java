import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

	 @GetMapping("/payment")
	    public String showPaymentPage() {
	        return "front"; // Don't include .html, just the name
	    }
}
