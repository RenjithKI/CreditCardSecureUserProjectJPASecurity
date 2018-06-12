package hello;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Renjith
 *
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	
	List<CreditCard> findByNumber(String number);
	
	List<CreditCard> findByNumberContaining(String number);
	
	List<CreditCard> findAll();

}