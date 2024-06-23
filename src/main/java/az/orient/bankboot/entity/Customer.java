package az.orient.bankboot.entity;


import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@Table(name = "customer")
@Entity
@DynamicInsert
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Date dob;
    private String cif;
    private String pin;
    private String seria;
    @CreationTimestamp
    private Date dateDate;
    @ColumnDefault(value = "1")
    private Integer active;


}
