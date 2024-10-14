package vn.edu.ptit.supermarket.service;

import vn.edu.ptit.supermarket.model.response.CashierInOrderResponse;

public interface StaffService {

  CashierInOrderResponse findCashierById(String cashierId, String role);
}
