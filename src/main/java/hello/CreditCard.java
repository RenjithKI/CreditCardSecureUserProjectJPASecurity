package hello;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
/**
 * @author Renjith
 *
 */
@Entity
public class CreditCard {

	@Id
	@NotNull	
	private String number;

	@NotNull
	@Size(min=2, max=70)
	private String name;	

	@NotNull	
	@Min(1)
	@Max(12)
	private Integer expiryMonth;	

	@NotNull	
	@Min(18)
	@Max(30)
	private Integer expiryYear;/*2018 to 2030 year  yy/mm*/	
	
	@ManyToOne
	private Customer customer;

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public Integer getExpiryMonth() {
		return expiryMonth;
	}

	public Integer getExpiryYear() {
		return expiryYear;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExpiryMonth(Integer expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public void setExpiryYear(Integer expiryYear) {
		this.expiryYear = expiryYear;
	}	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", name=" + name + "]";
	}
}
