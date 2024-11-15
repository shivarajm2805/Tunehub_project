package com.example.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entities.Users;
import com.example.service.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	UsersService userv;
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		Order order=null;
		try {
		RazorpayClient razorpay = new RazorpayClient("rzp_test_NvG29nx3OmER4m", "gyLUTefgrBYvkk8IQBPhwzPP");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",50000);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "receipt#1");
		JSONObject notes = new JSONObject();
		notes.put("notes_key_1","Tea, Earl Grey, Hot");
		orderRequest.put("notes",notes);

		 order = razorpay.orders.create(orderRequest);// here we get order object
		}
		catch(Exception e) {
			System.out.println("Exception while creating order");
		}
		return order.toString();// returning the order object to simplePayment page
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_NvG29nx3OmER4m", "gyLUTefgrBYvkk8IQBPhwzPP");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "gyLUTefgrBYvkk8IQBPhwzPP");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// payment-success -> upadate to premium user
	
	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session) 
	{
		String email=(String) session.getAttribute("email");//getAttribute method returns object hence we performing down-casting and convert into string
		Users user=userv.getUser(email);// here we get Users object by using  getUser() method with help of email
		user.setPremium(true);// Initially premium(column in User table) is false if the user payment is success then we need to treat as he/she is premium user hence here we set the premium is true
		userv.updateUser(user);// here we updating the user as premium user permanently by setting true in the Users table or in databse by using updateUser() method
		return "login";
	}
	
	
	// payment-failer ->redirect to login
	@GetMapping("payment-failure")
	public String paymentFailure() {
		//redirect to payment-error page or login	
		return "login";
	}
	
}
