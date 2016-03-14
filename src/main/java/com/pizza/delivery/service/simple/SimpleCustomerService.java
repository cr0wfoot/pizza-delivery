package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Address;
import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.repository.CustomerRepository;
import com.pizza.delivery.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Simple implementation, interacts with customer layer
 * @see Customer
 * @see CustomerRepository
 */
@Service
public class SimpleCustomerService implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer find(Long id) {
        return customerRepository.read(id);
    }
    
    @Override
    public Customer find(Long id, boolean fetchLazy) {
        return customerRepository.read(id, fetchLazy);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.readAll();
    }

    @Override
    @Transactional
    public Customer createNewCustomer(CustomerDTO customerDto) {
        Customer customer = getCustomerFromDTO(customerDto);
        Long id = customerRepository.create(customer);
        customer.setId(id);
        return customer;
    }
    
    private Address getAddressFromDTO(CustomerDTO customerDto) {
        Address address = new Address();
        address.setCity(customerDto.getCity());
        address.setStreet(customerDto.getStreet());
        address.setAppartment(customerDto.getAppartment());
        return address;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerFromDTO(CustomerDTO customerDto) {
        if(customerDto == null) return new Customer();
        Customer customer;
        if(customerDto.getCustomerId() != null) {
            customer = customerRepository.read(customerDto.getCustomerId());
        } else {
            customer = new Customer();
        }
        customer.setName(customerDto.getName());
        customer.setAddress(getAddressFromDTO(customerDto));
        return customer;
    }
    
    @Override
    @Transactional
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void delete(Long id) {
        customerRepository.delete(id);
    }
    
}