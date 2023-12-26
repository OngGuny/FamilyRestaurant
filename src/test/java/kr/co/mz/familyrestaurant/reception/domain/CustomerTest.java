package kr.co.mz.familyrestaurant.reception.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerTest {
@Mock
private CustomerRepository customerRepository;
    @Test
    void testCreateCustomer(){
        //필요한 정보만 갖고있는 녀석으로 추상화
        // 일행, 예약 여부
        var customer = new Customer(4,false);
        assertEquals(4,customer.getCompany());
        assertEquals(false,customer.getBooking());

    }
}
