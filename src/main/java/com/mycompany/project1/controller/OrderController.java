package com.mycompany.project1.controller;

import com.mycompany.project1.domain.OrderStatus;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.OrderDTO;
import com.mycompany.project1.domain.dto.OrderDetailsDTO;
import com.mycompany.project1.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private OrderDetailsServiceImpl orderDetailsService;
    private ProductService productService;

    public OrderController(OrderService orderService, OrderDetailsServiceImpl orderDetailsService, ProductService productService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public String getOrders(@AuthenticationPrincipal User user, Model model) {
        List<OrderDTO> orders;
        if (user.isAdmin()) {
            orders = orderService.getOrders();
        } else {
            orders = orderService.getOrdersByUserId(user.getId());
        }
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orderdetails/{orderid}")
    public String getOrderDetailByOrderId(@PathVariable Long orderid, Model model) {
        List<OrderDetailsDTO> orderDetailsDTO = orderDetailsService.findOrderDetailsByOrderId(orderid);
        model.addAttribute("orderDetails", orderDetailsDTO);
        return "orderDetail";
    }

    @GetMapping("/orders/{id}/edit/{orderStatus}")
    public String updateOrderStatus(@PathVariable Long id, @PathVariable String orderStatus) {
        if (orderStatus.equals("canceled")) {
            orderService.updateOrderStatus(OrderStatus.CANCELED, id);
        }
        if (orderStatus.equals("closed")) {
            orderService.updateOrderStatus(OrderStatus.CLOSED, id);
        }
        return "redirect:/orders";
    }

    @GetMapping("/orderdetails/product/{productid}")
    public String getProductBuId(@PathVariable Long productid, Model model) {
        String type = productService.getProductTypeById(productid);
        String redirectPath = "redirect:/" + type + "/" + productid;
        return redirectPath;
    }

}
