package hello;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author Renjith
 *  
 */
@Controller
public class WebController2 implements WebMvcConfigurer {

	private static Logger logger = Logger
			.getLogger(WebController2.class);	
	private CustomerRepository customerRepository;

	@Autowired
	public WebController2(CustomerRepository customerRepository) {	
		this.customerRepository = customerRepository;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results3").setViewName("results3");
	}

	
	@GetMapping("/new-user")
	public String shownewUserForm(Customer customer) {
		return "new-user";
	}

	@PostMapping("/new-user")
	public String checknewUser(Customer customer) {		
		customerRepository.save(customer);
		logger.info("inside checknewUser <<<<<< >>>>");
		return "redirect:/results3";
	}

}
