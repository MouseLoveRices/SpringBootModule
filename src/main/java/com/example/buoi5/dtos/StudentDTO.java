package com.example.buoi5.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @NotBlank(message = "Ten khong duoc rong")
    @Size(min = 2, max = 50, message = "Ten phai co tu 2 den 50 ky tu")
    private String ten;

    @NotBlank(message = "Thanh pho khong duoc de trong")
    private String thanhPho;

    @Past
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    @NotNull(message = "Xep Loai khong duoc rong")
    private String xepLoai;
}
