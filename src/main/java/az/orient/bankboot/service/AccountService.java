package az.orient.bankboot.service;

import az.orient.bankboot.dto.reponse.RespAccount;
import az.orient.bankboot.dto.reponse.Response;

import java.util.List;

public interface AccountService {
    Response<List<RespAccount>> getAccountList();

    Response<List<RespAccount>> getAccountListByCustomerId(Long customerId);

    Response<RespAccount> getAccountById(Long accountId);
}
