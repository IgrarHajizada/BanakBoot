package az.orient.bankboot.service;

import az.orient.bankboot.dto.reponse.RespCustomer;
import az.orient.bankboot.dto.reponse.Response;
import az.orient.bankboot.dto.request.ReqCustomer;

import java.util.List;

public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerById(Long customerId);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(Long customerId);
}

