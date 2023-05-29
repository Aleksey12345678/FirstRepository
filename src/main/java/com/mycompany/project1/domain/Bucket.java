package com.mycompany.project1.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name="bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany
    @JoinTable(name="buckets_products",
    joinColumns = @JoinColumn(name = "bucket_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
    private List <Product> products;

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                '}';
    }
}
