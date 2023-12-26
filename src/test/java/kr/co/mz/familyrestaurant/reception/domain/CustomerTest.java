package kr.co.mz.familyrestaurant.reception.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerTest {

    @Test
    void createCustomer_WithAllArgs(){
        var customer = new Customer(4,false);
        assertEquals(4,customer.getTotalGroupSize());
        assertEquals(false,customer.getBooking());
    }

    @Test
    void createCustomer_WithAtLeastOneCompany(){
        assertDoesNotThrow(() -> new Customer(1,true));
        assertDoesNotThrow(() -> new Customer(100,false));
    }
    @Test
    void throwsException_WithNegativeOrZeroSizeOfGroup(){
        assertThrows(IllegalArgumentException.class,()-> new Customer(-1,true));
        assertThrows(IllegalArgumentException.class,()-> new Customer(0,true));
    }
    @Test
    void enterReception_WithGroup(){
        var customer = new Customer(3,false);
        var reception = new Reception();

        assertEquals(3, customer.getTotalGroupSize());

        customer.enterWithGroup(reception);
        assertEquals("Reception",customer.getLocation());
        assertEquals(3,reception.getCustomersCount());
    }

    @Test
    void enterReception_WithAlone(){
        var customer = new Customer(3,false);
        var reception = new Reception();

        assertEquals(3, customer.getTotalGroupSize());

        customer.enterWithoutGroup(reception);
        assertEquals("Reception", customer.getLocation());
        assertEquals(1,reception.getWaitingTeamsCount());
    }

    @Test
    void enterReception_WithReentering(){
        var customer = new Customer(3,false);
        var reception = new Reception();

        assertEquals(3, customer.getTotalGroupSize());

        customer.enterWithoutGroup(reception);
        assertEquals("Reception", customer.getLocation());
        assertEquals(1,reception.getWaitingTeamsCount());

        customer.enterWithGroup(reception);
        assertEquals("Reception",customer.getLocation());
        assertEquals(3,reception.getCustomersCount());

        customer.enterWithoutGroup(reception);
        assertEquals(3,reception.getCustomersCount());
    }

    @Test
    void enterReception_WithTwoDifferentCustomers(){
        var customerWith4Group = new Customer(5,false);
        var customerWith2Group = new Customer(3,false);
        var reception = new Reception();

        customerWith4Group.enterWithoutGroup(reception);
        assertEquals(1,reception.getCustomersCount());

        customerWith2Group.enterWithGroup(reception);
        assertEquals(4,reception.getCustomersCount());

        assertEquals(2,reception.getWaitingTeamsCount());

        customerWith4Group.enterWithGroup(reception);
        assertEquals(8,reception.getCustomersCount());
    }
}
