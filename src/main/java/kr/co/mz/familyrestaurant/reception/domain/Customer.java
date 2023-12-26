package kr.co.mz.familyrestaurant.reception.domain;

import lombok.Getter;

@Getter
// going to be entity
public class Customer {
    private Integer totalGroupSize;
    private Boolean booking;
    private String location;

    public Customer(Integer totalGroupSize, Boolean booking) {
        if (totalGroupSize < 1) {
            throw new IllegalArgumentException("Group size must include at least the customer themselves, hence 1 or more.");
        }
        this.totalGroupSize = totalGroupSize;
        this.booking = booking;
        location = "Outside";
    }

    public void enterWithoutGroup(Reception reception) {
        location = reception.getClass().getSimpleName();
        reception.acceptCustomerWithoutGroup(this);
    }

    public void enterWithGroup(Reception reception) {
        location = reception.getClass().getSimpleName();
        reception.acceptCustomerWIthGroup(this);
    }
}
