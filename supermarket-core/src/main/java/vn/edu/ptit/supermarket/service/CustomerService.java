package vn.edu.ptit.supermarket.service;

import vn.edu.ptit.supermarket.model.response.CustomerInOrderResponse;

public interface CustomerService {

  CustomerInOrderResponse findCustomerById(String customerId);
}
