package com.ts;
import java.io.BufferedReader;



import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dao.CustomerDAO;
import com.dao.ItemDAO;
import com.dao.ProfessionalDAO;
import com.dao.RequestsDAO;
import com.dao.ServiceDAO;
import com.dao.TestimonialDAO;
import com.db.HibernateTemplate;
import com.dto.Customer;
import com.dto.ItemDetails;
import com.dto.Professional;
import com.dto.Requests;
import com.dto.Services;
import com.dto.Testimonials;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/*@Path("mail/{cid}/{subject}/{body}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String mail(@PathParam("cid") int cid, @PathParam("subject") String subject1,@PathParam("body") String body1) throws MessagingException {
			String subject= subject1;
			String body= body1;
			
			String host = "smtp.gmail.com";
			String from = "cyberartificiers@gmail.com";
			String pass = "wiseproject";
			
			
			RequestsDAO requestsDAO = new RequestsDAO();
			requestsDAO.addRequests(cid,sid);
			List<Professional> profList = new ArrayList<Professional>();
			ProfessionalDAO professionalDAO = new ProfessionalDAO();
			profList = professionalDAO.getAllProfessionals();
			Properties props = System.getProperties();
			for(int i = 0 ; i < profList.size(); i++){
				String email = profList.get(i).getEmailId();
				
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

				for( int i1=0; i1 < to.length; i1++ )
				{
					// changed from a while loop
					toAddress[i1] = new InternetAddress(to[i1]);
				}

				for( int i1=0; i1 < toAddress.length; i1++)
				{
					// changed from a while loop
					message.addRecipient(Message.RecipientType.TO, toAddress[i1]);
				}

				message.setSubject(subject);
				message.setText(body);

				Transport transport = session.getTransport("smtp");

				transport.connect(host, from, pass);
				transport.sendMessage(message, message.getAllRecipients());

				transport.close();

			}
			
			

			
			return "Successful";
    	}
	*/
	@Path("hi")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hi() throws UnsupportedEncodingException {
		System.out.println("Hi...");
		return "Hi Service!";
	}
	
	@Path("AddRequest")
	@GET
	public int registerCust() {
		
		Requests requests = new Requests();
		requests.setCid(0);
		requests.setPid(1);
		requests.setrId(1);
		requests.setSid(1);
		requests.setStatus(0);
		
		return HibernateTemplate.addObject(requests);
	}
	
	@Path("storeTestimonial")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String storeTestimonial(Testimonials t){
		System.out.println("in mr");
		Testimonials x = new Testimonials();
		x.setName("one");
		x.setText("Iam Text..");
		x.setCustId(1);
		TestimonialDAO testinomialDAO = new TestimonialDAO();
		testinomialDAO.store(t);
		return "success";
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
	
	@Path("UpdateCustomerProfile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String UpdateCustomerProfile(Customer customer) {
		System.out.println("Data Recieved in UpdateCustomerProfile MYRESOURCE : " + customer);
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.updateProfile(customer);	
		return "sucess";
	}
	
	/*@Path("updateRequestStatus/{rid}/{x}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateRequestStatus(@PathParam("rid") String rid, @PathParam("x") String x) {
		System.out.println("Data Recieved in UpdateRequestStatus MYRESOURCE : " );
		RequestsDAO requestsDAO = new RequestsDAO();
		int ridi = Integer.parseInt(rid);
		int xi = Integer.parseInt(x); 
		requestsDAO.updateRequestStatus(ridi,xi);	
		return "sucess";
	}*/
	@Path("updateRequestStatus/{rid}/{x}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateRequestStatus(@PathParam("rid") String rid, @PathParam("x") String x) {
		System.out.println(rid+x);
		System.out.println("Data Recieved in UpdateRequestStatus MYRESOURCE : " );
		RequestsDAO requestsDAO = new RequestsDAO();
		int ridi = Integer.parseInt(rid);
		int xi = Integer.parseInt(x); 
		requestsDAO.updateRequestStatus(ridi,xi);	
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
	
	
	
	@Path("ViewRequests/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Requests> ViewRequests(@PathParam("pid") String s) {
		
		System.out.println("String dis "+s);
		/*CustomerDAO customerDAO = new CustomerDAO();
		List <Customer> custlist = customerDAO.getAllCustomers();*/
		int pid=Integer.parseInt(s); 
		RequestsDAO requestsDAO = new RequestsDAO();
		List<Requests> l = requestsDAO.viewRequests(pid);
		return l;
	}

	@Path("updateServices")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateServices(Services service) {
		System.out.println("Data Recieved in updateServices : " + service);
		service.setProfessional(null);
		ServiceDAO serviceDAO = new ServiceDAO();
		serviceDAO.updateServices(service);	
		MyResource mr = new MyResource();
		try {
			String s = mr.mail(service.getServiceId(),1, "Service Request !! ", " A customer needs ur service, if u r interested please login to ur account and accept the request !!");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sucess";
	}
	
	@Path("updateServicesProf")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateServicesProf(Services service) {
		System.out.println("Data Recieved in updateServicesProf : " + service);
		
		ServiceDAO serviceDAO = new ServiceDAO();
		serviceDAO.updateServicesProf(service);	
		
		return "sucess";
	}
	
    public String mail(int sid, int cid,String subject1,String body1) throws MessagingException {
			String subject= subject1;
			String body= body1;
			
			String host = "smtp.gmail.com";
			String from = "cyberartificiers@gmail.com";
			String pass = "wiseproject";
			
			
			RequestsDAO requestsDAO = new RequestsDAO();
			requestsDAO.addRequests(cid,sid);
			List<Professional> profList = new ArrayList<Professional>();
			ProfessionalDAO professionalDAO = new ProfessionalDAO();
			profList = professionalDAO.getAllProfessionals();
			Properties props = System.getProperties();
			for(int i = 0 ; i < profList.size(); i++){
				String email = profList.get(i).getEmailId();
				
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

				for( int i1=0; i1 < to.length; i1++ )
				{
					// changed from a while loop
					toAddress[i1] = new InternetAddress(to[i1]);
				}

				for( int i1=0; i1 < toAddress.length; i1++)
				{
					// changed from a while loop
					message.addRecipient(Message.RecipientType.TO, toAddress[i1]);
				}

				message.setSubject(subject);
				message.setText(body);

				Transport transport = session.getTransport("smtp");

				transport.connect(host, from, pass);
				transport.sendMessage(message, message.getAllRecipients());

				transport.close();

			}
			
			

			
			return "Successful";
    	}
    
    
    @Path("contact")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public String contact(){
    	String recipient = "pragnakanchana761@gmail.com"; 
    	  
        // email ID of  Sender. 
        String sender = "kanchanapragna761@gmail.com"; 
    
        // using host as localhost 
        String host = "localhost"; 
    
        // Getting system properties 
        Properties properties = System.getProperties(); 
    
        // Setting up mail server 
        properties.setProperty("mail.smtp.host", host); 
    
        // creating session object to get properties 
        Session session = Session.getDefaultInstance(properties); 
    
        try 
        { 
           // MimeMessage object. 
           MimeMessage message = new MimeMessage(session); 
    
           // Set From Field: adding senders email to from field. 
           message.setFrom(new InternetAddress(sender)); 
    
           // Set To Field: adding recipient's email to from field. 
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
    
           // Set Subject: subject of the email 
           message.setSubject("This is Suject"); 
    
           // set body of the email. 
           message.setText("This is a test mail"); 
    
           // Send email. 
           Transport.send(message); 
           System.out.println("Mail successfully sent"); 
        } 
        catch (MessagingException mex)  
        { 
           mex.printStackTrace(); 
        } 
    	return "success";
    }
    
	@Path("sendSMS")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public String sendSMS() throws IOException {
		String apiKey = "apikey=" + "GVQvUEFPNlE-TPuzonMGNfHVfkQ6vTMsDd688FMd7i";
		String message = "&message=" + "Hii";
		String sender = "&sender=" + "TXTLCL";
		String numbers = "&numbers=" + "7095620809";

		System.out.println("while api message to "+message);

		// Send data
		HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
		String data = apiKey + numbers + message + sender;
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
		conn.getOutputStream().write(data.getBytes("UTF-8"));
		final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
		stringBuffer.append(line);
		}
		rd.close();

		return stringBuffer.toString();
	}
		
	
	
	@Path("getCustomers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomers() {

		CustomerDAO customerDAO = new CustomerDAO();
		List <Customer> custlist = customerDAO.getAllCustomers();
		

		return custlist;
	}

	@Path("getTestimonials")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Testimonials> getTestimonials() {

		TestimonialDAO testimonialDAO = new TestimonialDAO();
		List <Testimonials> list = testimonialDAO.getAllTestimonials();
		

		return list;
	}
	
	@Path("getServicesList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Services> getServices() {
		System.out.println("My Resource All Service .........");
		List<Services> s=(List)HibernateTemplate.getObjectListByQuery("From Services");
		System.out.println("Inside All Services ..."+s);
		return s;
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
	
	@Path("getCustByEmail/{emailId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustByEmail(@PathParam("emailId")String email) {
		System.out.println("HEHEHE Recieved: "+email); 
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.getCustByEmail(email);
		return customer;
	}
	@Path("getProfByEmail/{emailId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Professional getProfByEmail(@PathParam("emailId")String email) {
		System.out.println("HEHEHE Recieved: "+email); 
		ProfessionalDAO professionalDAO = new ProfessionalDAO();
		Professional professional = professionalDAO.getProfByEmail(email);
		return professional;
	}
	
	
	
	/*
	@Path("uploadImage")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadImage(@FormDataParam("itemImage") InputStream fileInputStream,@FormDataParam("itemImage") FormDataContentDisposition
	formDataContentDisposition, @FormDataParam("itemName") String itemName, @FormDataParam("itemDescription") String itemDescription) throws IOException {;
	int read = 0;
	byte[] bytes = new byte[1024];

	String path = this.getClass().getClassLoader().getResource("").getPath();

	String pathArr[] = path.split("/WEB-INF/classes/");

	FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));


	while((read = fileInputStream.read(bytes)) != -1){

	out.write(bytes,0,read);
	}
	out.flush();
	out.close();

	ItemDetails item = new ItemDetails();
	item.setItemName(itemName);
	item.setItemDescription(itemDescription);
	item.setItemImage(formDataContentDisposition.getFileName());
	ItemDAO itemDao = new ItemDAO();
	itemDao.additem(item);
	}
	@Path("getImages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemDetails> getItems() {

	ItemDAO itemDAO = new ItemDAO();
	List <ItemDetails> itemList = itemDAO.getAllItemDetails();

	return itemList;
	}*/
	
	/*	
	 @Path("getEmpByEmail/{email}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmpByUserPass(@PathParam("email") String email) {
		System.out.println("Recieved path params: "+email); 
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.getEmpByEmail(email);
		return employee;
	}
	
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
