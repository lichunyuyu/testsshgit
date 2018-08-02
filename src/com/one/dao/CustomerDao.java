package com.one.dao;

import com.one.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vtstar on 2017/12/14.
 */

/**
 * 注入
 * */
@Repository
public class CustomerDao implements ICustomerDao{

    @Override
    public void addUser(Customer customer) {

    }

    @Override
    public List<Customer> getAllCustomer() {
        return null;
    }

    @Override
    public boolean delCustomer(String id) {
        return false;
    }

    @Override
    public Customer getCustomer(String id) {
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }
}
