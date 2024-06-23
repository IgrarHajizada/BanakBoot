package az.orient.bankboot.controller;

import az.orient.bankboot.dto.reponse.RespAccount;
import az.orient.bankboot.dto.reponse.Response;
import az.orient.bankboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/GetAccountList")
    public Response<List<RespAccount>> getAccountList() {
        return accountService.getAccountList();
    }

    @GetMapping("/GetAccountListListByCustomerId/{custId}")
    public Response<List<RespAccount>> getAccountListByCustomerId(@PathVariable("custId") Long customerId) {
        return accountService.getAccountListByCustomerId(customerId);
    }

    @GetMapping("/GetAccountById/{accountId}")
    public Response<RespAccount> getAccountById(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountById(accountId);
    }

}
