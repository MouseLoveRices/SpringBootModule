package com.example.buoi5.models;

public enum XepLoai {
    GIOI("Gioi"),KHA("Kha"),TRUNG_BINH("Trung binh"),YEU("Yeu");
    private String ten;
    XepLoai(String ten){
        this.ten=ten;
    }
    public String getTen(){
        return ten;
    }

    public static XepLoai fromTen(String ten){
        for(XepLoai x: XepLoai.values()){
            if(x.getTen().equals(ten)){
                return x;
            }
        }
        throw new IllegalArgumentException(ten);
    }
}
