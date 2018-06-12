package hello;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author Renjith
 * 
 * http://localhost:8080/
 * http://localhost:8080/credit-cards-list
 * http://localhost:8080/search-credit-card
 * http://localhost:8080/form
 * http://localhost:8080/results
 * 
 * 
 */
@Controller
public class WebController implements WebMvcConfigurer {

	private static Logger logger = Logger
			.getLogger(WebController.class);

	private CreditCardRepository creditCardRepository;	

	@Autowired
	public WebController(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;	
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");		
	}

	@GetMapping("/")
	public String showForm(CreditCard creditCard) {
		return "form";
	}

	@PostMapping("/")
	public String checkCreditCarddetails(@Valid CreditCard creditCard, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		creditCardRepository.save(creditCard);
		return "redirect:/results";
	}	
	
	@GetMapping("/credit-cards-list")
	public String creditCardMethod(Model model) {		
		List<CreditCard> cardList = creditCardRepository.findAll();
		if (cardList != null) {
			model.addAttribute("cards", cardList);
		}
		return "credit-cards-list";
	}

	@GetMapping("/search-credit-card")
	public String searchForm(String CreditCardNumber) {		
		return "search-card-form";
	}

	@PostMapping("/search-credit-card")
	public String searchCreditCard(Model model, @RequestParam("number") String search) {
		logger.info("inside searchCreditCard methos >>>>");		
		List<CreditCard> cardList = creditCardRepository.findByNumberContaining(search); 
		if (cardList != null) {
			model.addAttribute("cards", cardList);
		} 	
		return "credit-cards-list";
	} 

}
