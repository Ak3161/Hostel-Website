package in.sp.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.Student;

public interface Student_data extends JpaRepository<Student, Integer> {
	
	public Student findByRazorpayOrderId(String orderId);
}
