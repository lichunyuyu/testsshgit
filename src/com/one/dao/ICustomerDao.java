package com.one.dao;

import com.one.entity.Customer;

import java.util.List;

/**
 * Created by vtstar on 2017/12/14.
 */

public interface ICustomerDao {

    public void addUser(Customer customer);

    public List<Customer> getAllCustomer();

    public boolean delCustomer(String id);

    public Customer getCustomer(String id);

    public boolean updateCustomer(Customer customer);
}
