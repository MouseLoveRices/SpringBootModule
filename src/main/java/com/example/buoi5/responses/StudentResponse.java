package com.example.buoi5.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.example.buoi5.models.Student;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse extends BaseResponse{
    private Long id;
    private String ten;
    private String thanhPho;
    private LocalDate ngaySinh;
    private String xepLoai;
    public static StudentResponse fromStudent(Student student){
        StudentResponse studentResponse = StudentResponse.builder()
                                .id(student.getId())
                                .ten(student.getTen())
                                .thanhPho(student.getThanhPho())
                                .ngaySinh(student.getNgaySinh())
                                .xepLoai(String.valueOf(student.getXepLoai()))
                                .build();

    return studentResponse;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getThanhPho() {
        return thanhPho;
    }
    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public String getXepLoai() {
        return xepLoai;
    }
    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    
}
