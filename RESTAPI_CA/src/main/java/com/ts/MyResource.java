package com.ts;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.CustomerDAO;
import com.dao.ProfessionalDAO;
import com.dto.Customer;
import com.dto.Professional;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	@Path("hi")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hi() throws UnsupportedEncodingException {
		System.out.println("Hi...");
		return "Hi Service!";
	}
	
	@Path("registerCust")
	@GET
	public String registerCust() {
		
		Customer customer = new Customer();
		customer.setCustomerName("pragna");
		customer.setDistrict("WG");
		customer.setEmailId("kaj@gmail.com");
		customer.setLandmark("hyd");
		customer.setMobNum("7908978");
		customer.setPassword("password");
		customer.setState("Telangana");
		
		System.out.println(customer);
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.register(customer);
	
		return "Success";
	}
	
	@Path("registerCustomer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerCustomer(Customer customer) {
		System.out.println("Data Recieved in Customer Register : " + customer);
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.register(customer);	
		return "sucess";
	}
	
	@Path("registerProfessional")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerProfessional(Professional professional) {
		System.out.println("Data Recieved in Professional Register : " + professional);
		ProfessionalDAO professionalDAO = new ProfessionalDAO();
		professionalDAO.register(professional);	
		return "sucess";
	}
	
	@Path("getCustomers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomers() {

		CustomerDAO customerDAO = new CustomerDAO();
		List <Customer> custlist = customerDAO.getAllCustomers();
		

		return custlist;
	}

	@Path("getCustByUserPass/{mailId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustByUserPass(@PathParam("mailId") String mailId,@PathParam("password") String password) {
		System.out.println("Recieved path params: "+mailId+" "+password); 
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.getCustByUserPass(mailId, password);
		return customer;
	}
	
	@Path("getProfByUserPass/{mailId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Professional getProfByUserPass(@PathParam("mailId") String mailId,@PathParam("password") String password) {
		System.out.println("Recieved path params: "+mailId+" "+password); 
		ProfessionalDAO professionalDAO = new ProfessionalDAO();
		Professional professional = ProfessionalDAO.getProfByUserPass(mailId, password);
		return professional;
	}
	
	/*	
	@Path("getCustByUserPass/{mailId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getEmpByUserPass(@PathParam("mailId") String mailId,@PathParam("password") String password) {
		System.out.println("Recieved path params: "+mailId+" "+password); 
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.getCustByUserPass(mailId, password);
		return customer;
	}
@Path("mail")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() throws MessagingException {

		String subject="Test Mail";
		String body="Java mail test...";
		String email="likithamanne99@gmail.com";
		String host = "smtp.gmail.com";
		String from = "mdgp2u@gmail.com";
		String pass = "abc1234";

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = {email}; // added this line

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress = new InternetAddress[to.length];

		// To get the array of addresses

		for( int i=0; i < to.length; i++ ) 
		{ 
			// changed from a while loop
			toAddress[i] = new InternetAddress(to[i]);
		}

		for( int i=0; i < toAddress.length; i++)
		{ 
			// changed from a while loop
			message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		}

		message.setSubject(subject);
		message.setText(body);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();

		return "Successful";
	}
*/
	/*@Path("getEmpByEmail/{email}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmpByUserPass(@PathParam("email") String email) {
		System.out.println("Recieved path params: "+email); 
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.getEmpByEmail(email);
		return employee;
	}

	@Path("getEmpByUserPass/{loginId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmpByUserPass(@PathParam("loginId") String loginId,@PathParam("password") String password) {
		System.out.println("Recieved path params: "+loginId+" "+password); 
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.getEmpByUserPass(loginId, password);
		return employee;
	}


	@Path("getEmployees")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {

		EmployeeDAO employeeDAO = new EmployeeDAO();
		List <Employee> empList = employeeDAO.getAllEmployees();

		return empList;
	}


	@Path("getDepartments")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getDepartments() {        
		DeptDAO deptDao = new DeptDAO();
		List <Department> deptList = deptDao.getAllDepts();
		return deptList;
	}

	@Path("getDeptByName/{deptName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getDeptByName(@PathParam("deptName") String deptName) {        
		System.out.println(deptName);      
		DeptDAO deptDao = new DeptDAO();
		List<Department> depts = deptDao.getDeptByName(deptName);
		System.out.println(depts); 
		return depts;
	}
	
	@Path("registerEmp")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerEmp(Employee employee) {
		System.out.println("Data Recieved in Emp Register : " + employee);
		EmployeeDAO employeeDao = new EmployeeDAO();
		employeeDao.register(employee);	
	}
	
	@Path("registerDept")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerDept(Department department) {
		System.out.println("Data Recieved in Dept Register : "+department); 
		DeptDAO deptDao = new DeptDAO();
		deptDao.register(department);
	}
	
	//It is sample code if need to test and insert values into any tables
	@Path("registerEmp1")
	@GET
	public String registerEmp1() {
		DeptDAO deptDao = new DeptDAO();
		
		Department dept = deptDao.getDept(1);
		
		Employee employee = new Employee();
		employee.setEmpName("PASHA");
		employee.setEmail("email@gmail.com");
		employee.setGender("Male");
		employee.setJoinDate(new java.util.Date());
		employee.setDepartment(dept); 
		
		EmployeeDAO employeeDao = new EmployeeDAO();
		employeeDao.register(employee);
	
		return "Success";
	}
	
	@Path("registerDept1")
	@GET
	public String registerDept1() {
		DeptDAO deptDao = new DeptDAO();
		
		Department dept = new Department();
		dept.setDeptId(1);
		dept.setDeptName("IT");
		dept.setLocation("Hyd");
		
		DeptDAO deptDAO = new DeptDAO();
		deptDAO.register(dept);
		
		return "Success";
	
	}*/
}
