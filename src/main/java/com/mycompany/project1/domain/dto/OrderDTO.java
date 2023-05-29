package com.mycompany.project1.domain.dto;


import com.mycompany.project1.domain.OrderDetails;
import com.mycompany.project1.domain.OrderStatus;
import com.mycompany.project1.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private User user;
    private BigDecimal sum;
    private String address;

    private List<OrderDetails> details;

    private OrderStatus status;
}