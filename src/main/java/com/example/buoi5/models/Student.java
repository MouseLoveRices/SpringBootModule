package com.example.buoi5.models;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="student")
public class Student extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message="Ten khong duoc rong")
    @Size(min = 2, max = 50, message = "Ten phai co 2 den 50 ky tu")
    private String ten;

    @NotBlank(message = "Thanh pho khong duoc rong")
    private String thanhPho;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Past(message = "Phai la 1 ngay trong qua khu")
    private LocalDate ngaySinh;
    
    @NotNull(message = "Xep Loai khong duoc rong")
    @Enumerated(EnumType.STRING)
    private XepLoai xepLoai;

}
