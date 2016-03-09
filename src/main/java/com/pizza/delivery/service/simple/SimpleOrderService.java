package com.pizza.delivery.service.simple;

import com.pizza.delivery.domain.Basket;
import com.pizza.delivery.domain.OrderState;
import com.pizza.delivery.domain.discounts.DiscountManager;
import com.pizza.delivery.domain.dto.CustomerDTO;
import com.pizza.delivery.domain.entities.Customer;
import com.pizza.delivery.domain.entities.OrderDetails;
import com.pizza.delivery.domain.entities.Pizza;
import com.pizza.delivery.domain.entities.PizzaOrder;
import com.pizza.delivery.repository.PizzaOrderRepository;
import com.pizza.delivery.service.CustomerService;
import com.pizza.delivery.service.DiscountCardService;
import com.pizza.delivery.service.OrderService;
import com.pizza.delivery.service.PizzaService;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SimpleOrderService implements OrderService {
    
    private static final int MAX_ACCEPTABLE_NUMBER_OF_PIZZAS = 10;
    private static final int MIN_ACCEPTABLE_NUMBER_OF_PIZZAS = 1;
    private static final OrderState DEFAULT_STATE_OF_NEW_ORDER = OrderState.NEW;
    
    @Autowired
    private PizzaOrderRepository orderRepository;
    
    @Autowired
    private PizzaService pizzaService;
    
    @Autowired
    private DiscountCardService discountCardService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private DiscountManager discountManager;
    
    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void changeStateOfOrder(Long orderId, OrderState newState) {
        if(orderId == null) return;
        PizzaOrder order = orderRepository.read(orderId);
        if(order.getState().isAvaliableStateToChange(newState)) {
            order.setState(newState);
            orderRepository.update(order);   
        }
    }
    
    @Override
    @Transactional
    public void placeNewOrder(CustomerDTO customerDto, Basket basketOfPizzas) {
        if(customerDto == null || 
           basketOfPizzas == null || 
           !checkRestrictionsForPizzasQuantity(basketOfPizzas) || 
           !customerDto.isAddressComplete()) return;

        Customer customer;
        if(customerDto.getCustomerId() == null) {
            customer = customerService.createNewCustomer(customerDto);
        } else {
            customer = customerService.getCustomerFromDTO(customerDto);
        }
        
        PizzaOrder newOrder = new PizzaOrder();
        Set<OrderDetails> details = createOrderDetails(newOrder, basketOfPizzas);
        newOrder.setCustomer(customer);
        newOrder.setState(DEFAULT_STATE_OF_NEW_ORDER);
        newOrder.setDetails(details);
        newOrder.setPrice(calculatePriceWithDiscounts(newOrder));
        
        chargePointsToDiscountCard(customer, newOrder.getPrice());
        
        basketOfPizzas.clear();
        orderRepository.create(newOrder); 
    }
    
    @Override
    @Transactional
    public void addPizzasToOrder(Long orderId, Basket basketOfPizzas) {
        if(orderId == null || basketOfPizzas == null) return;
        PizzaOrder order = orderRepository.read(orderId);
        if(!order.getState().equals(OrderState.IN_PROGRSS) ||
           !order.getState().equals(OrderState.NEW)) return;
        
        Set<OrderDetails> details = createOrderDetails(order, basketOfPizzas);
        Double oldPrice = order.getPrice();
        
        checkRestrictionsForPizzasQuantity(basketOfPizzas);
        
        order.setDetails(details);
        order.setPrice(calculatePriceWithDiscounts(order));
        
        chargePointsToDiscountCard(order.getCustomer(), order.getPrice() - oldPrice);
        
        orderRepository.update(order);
    }
        
    @Override
    @Secured("ROLE_ADMIN")
    public List<PizzaOrder> showOrders() {
        return orderRepository.readAll();
    }
    
    private void chargePointsToDiscountCard(Customer customer, Double orderPrice) {
        if (customer.isDiscountCardExists()) {
            discountCardService.chargePoints(customer.getCard(), orderPrice.intValue());
        }
    }
    
    private Double calculatePriceWithDiscounts(PizzaOrder order) {
        Double totalDiscount = discountManager.calculateTotalDiscount(order);
        return order.getTotalPrice() - totalDiscount;
    }
    
    private boolean checkRestrictionsForPizzasQuantity(Basket basketOfPizzas) {
        int size = basketOfPizzas.getQuantity();
        if(size < MIN_ACCEPTABLE_NUMBER_OF_PIZZAS) {
            return false;
        } else if(size > MAX_ACCEPTABLE_NUMBER_OF_PIZZAS) {
            return false;
        }
        return true;
    }
    
    private Set<OrderDetails> createOrderDetails(PizzaOrder order, Basket basketOfPizzas) {
        Set<OrderDetails> details = new HashSet<OrderDetails>();
        Pizza pizza;
        for(Entry<Pizza, Integer> e : basketOfPizzas.getPizzas().entrySet()) {
            if((pizza = pizzaService.find(e.getKey().getId())) != null) {
                details.add(new OrderDetails(order, pizza, pizza.getPrice(), e.getValue()));
            }
        }
        return details;
    }
 
}