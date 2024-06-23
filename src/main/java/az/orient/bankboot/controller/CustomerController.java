package az.orient.bankboot.controller;


import az.orient.bankboot.dto.reponse.RespCustomer;
import az.orient.bankboot.dto.reponse.Response;
import az.orient.bankboot.dto.request.ReqCustomer;
import az.orient.bankboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/GetCustomerList")
    public Response<List<RespCustomer>> getCustomerList() {
        return customerService.getCustomerList();
    }


    @GetMapping(value = "/GetCustomerById/{custId}")
    public Response<RespCustomer> getCustomerById(@PathVariable("custId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }


    @PostMapping("/AddCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.addCustomer(reqCustomer);
    }

    @PutMapping("/UpdateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PutMapping("/DeleteCustomer")
    public Response deleteCustomer(@RequestParam("custId")  Long customerId) {
        return customerService.deleteCustomer(customerId);
    }


}
