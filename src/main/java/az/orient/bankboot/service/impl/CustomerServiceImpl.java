package az.orient.bankboot.service.impl;

import az.orient.bankboot.dto.reponse.RespCustomer;
import az.orient.bankboot.dto.reponse.RespStatus;
import az.orient.bankboot.dto.reponse.Response;
import az.orient.bankboot.dto.request.ReqCustomer;
import az.orient.bankboot.entity.Customer;
import az.orient.bankboot.enums.EnumAvailableStatus;
import az.orient.bankboot.exception.BankException;
import az.orient.bankboot.exception.ExceptionConstant;
import az.orient.bankboot.repository.CustomerRepository;
import az.orient.bankboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        List<RespCustomer> respCustomerList = new ArrayList<>();

        try {

            List<Customer> customerList = customerRepository.
                    findAllByActive(EnumAvailableStatus.ACTIVE.getValue());

            if (customerList.isEmpty()) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }

            for (Customer customer : customerList) {
                RespCustomer respCustomer = convert(customer);
                respCustomerList.add(respCustomer);
            }

            response.setT(respCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException exception) {
            response.setStatus(new RespStatus(exception.getCode(), exception.getMessage()));
            exception.printStackTrace();
        } catch (Exception exception) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
            exception.printStackTrace();
        }
        return response;
    }


    @Override
    public Response<RespCustomer> getCustomerById(Long customerId) {
        Response<RespCustomer> response = new Response<>();

        try {

            if (customerId == null)
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request");


            Customer customer = customerRepository.
                    findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            RespCustomer respCustomer = convert(customer);
            response.setT(respCustomer);
            response.setStatus(RespStatus.getSuccessMessage());


        } catch (BankException exception) {
            response.setStatus(new RespStatus(exception.getCode(), exception.getMessage()));
            exception.printStackTrace();
        } catch (Exception exception) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
            exception.printStackTrace();
        }
        return response;

    }


    @Override
    public Response addCustomer(ReqCustomer reqCustomer) {
        Response response = new Response<>();

        try {
            if (reqCustomer == null || reqCustomer.getName() == null || reqCustomer.getSurname() == null)
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request");
            Customer customer = setCustomer(reqCustomer);

            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException exception) {
            response.setStatus(new RespStatus(exception.getCode(), exception.getMessage()));
            exception.printStackTrace();
        } catch (Exception exception) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
            exception.printStackTrace();
        }
        return response;
    }


    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
        Response response = new Response<>();

        try {
            if (reqCustomer == null || reqCustomer.getId() == null
                    || reqCustomer.getName() == null || reqCustomer.getSurname() == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request");
            }
            Customer customer = customerRepository.findByIdAndActive(reqCustomer.getId(),
                    EnumAvailableStatus.ACTIVE.getValue());

            if (customer == null) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }

            customer.setName(reqCustomer.getName());
            customer.setSurname(reqCustomer.getSurname());
            customer.setEmail(reqCustomer.getEmail());
            customer.setPhone(reqCustomer.getPhone());
            customer.setDob(reqCustomer.getDob());
            customer.setCif(reqCustomer.getCif());
            customer.setPin(reqCustomer.getPin());
            customer.setSeria(reqCustomer.getSeria());
            customer.setUsername(reqCustomer.getUsername());
            customer.setPassword(reqCustomer.getPassword());

            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException exception) {
            response.setStatus(new RespStatus(exception.getCode(), exception.getMessage()));
            exception.printStackTrace();
        } catch (Exception exception) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
            exception.printStackTrace();
        }
        return response;
    }


    @Override
    public Response deleteCustomer(Long customerId) {
        Response response = new Response<>();

        try {
            if (customerId == null) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            Customer customer = customerRepository.findByIdAndActive(customerId,
                    EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }


            customer.setActive(EnumAvailableStatus.DEACTIVATE.getValue());
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException exception) {
            response.setStatus(new RespStatus(exception.getCode(), exception.getMessage()));
            exception.printStackTrace();
        } catch (Exception exception) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
            exception.printStackTrace();
        }
        return response;
    }


    private static Customer setCustomer(ReqCustomer reqCustomer) {
        Customer customer = new Customer();
        customer.setName(reqCustomer.getName());
        customer.setSurname(reqCustomer.getSurname());
        customer.setEmail(reqCustomer.getEmail());
        customer.setPhone(reqCustomer.getPhone());
        customer.setDob(reqCustomer.getDob());
        customer.setCif(reqCustomer.getCif());
        customer.setPin(reqCustomer.getPin());
        customer.setSeria(reqCustomer.getSeria());
        customer.setUsername(reqCustomer.getUsername());
        customer.setPassword(reqCustomer.getPassword());
        return customer;
    }


    private RespCustomer convert(Customer customer) {
        RespCustomer respCustomer = new RespCustomer();

        if (customer == null) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
        }
        respCustomer.setId(customer.getId());
        respCustomer.setUsername(customer.getUsername());
        respCustomer.setName(customer.getName());
        respCustomer.setSurname(customer.getSurname());
        if (customer.getDob() != null) ;
        respCustomer.setDob(df.format(customer.getDob()));
        respCustomer.setCif(customer.getCif());
        respCustomer.setSeria(customer.getSeria());
        respCustomer.setPin(customer.getPin());
        respCustomer.setEmail(customer.getEmail());
        respCustomer.setPhone(customer.getPhone());


        return respCustomer;
    }

}

