package com.example.internet_market_backend.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;
    private Long price;
    private String title;
    private String description;
    private String category;
    private Long count;
    @Column(name = "total_price")
    private Long totalPrice;
    @ManyToMany(mappedBy = "cart")
    @ToString.Exclude
    private Set<UserEntity> users;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DishEntity dish = (DishEntity) o;
        return id != null && Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
