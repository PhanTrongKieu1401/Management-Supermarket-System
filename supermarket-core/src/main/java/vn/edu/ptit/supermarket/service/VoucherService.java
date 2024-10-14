package vn.edu.ptit.supermarket.service;

import vn.edu.ptit.supermarket.model.response.VoucherInOrderResponse;

public interface VoucherService {

  VoucherInOrderResponse findVoucherById(String voucherId);
}
