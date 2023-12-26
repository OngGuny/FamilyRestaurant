package kr.co.mz.familyrestaurant.reception.domain;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class Reception {
    private Queue<Customer> entryQueue = new LinkedList<>();
    private Integer customersCount = 0;

    public Reception(){
    }

    public int getCustomersCount() {
        return customersCount;
    }

    public int getWaitingTeamsCount(){
        return entryQueue.size();
    }
    public void acceptCustomerWithoutGroup(Customer customer){
        if(entryQueue.contains(customer)){
            return;
        }
        entryQueue.add(customer);
        customersCount++;
    }
    public void acceptCustomerWIthGroup(Customer customer){
        if(entryQueue.contains(customer)){
            customersCount += customer.getTotalGroupSize() - 1 ;
            return;
        }
        entryQueue.add(customer);
        customersCount += customer.getTotalGroupSize();
    }
}
