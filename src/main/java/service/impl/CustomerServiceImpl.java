package service.impl;

import model.Customer;
import service.ICustomerService;

import java.util.ArrayList;

public class CustomerServiceImpl implements ICustomerService {
    private static final ArrayList<Customer> customers;

    static {
        customers = new ArrayList<>();
        customers.add(new Customer(1,"hao","h@mail","hanoi"));
        customers.add(new Customer(2,"nam","n@mail","hanam"));
        customers.add(new Customer(3,"duc","d@mail","hatinh"));
        customers.add(new Customer(4,"quang","q@mail","hatay"));
        customers.add(new Customer(5,"duong","d@mail","hadong"));
    }

    @Override
    public ArrayList<Customer> findAll() {
        return customers;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer findById(int id) {
        for (Customer customer: customers) {
            if (customer.getId() == id){
                return customer;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Customer customer) {
        customers.set(id, customer);
    }

    @Override
    public void remove(int id) {
        Customer customer = findById(id);
        customers.remove(customer);
    }
}
