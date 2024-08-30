package com.example.hibernateP;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="Product")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="proId")
    private int proId;
    private String productName;
    private int productStock;

    @ManyToOne
    @JoinColumn(name="catId")
    private Category category;

    Product(){}
    public int getProId(){
        return this.proId;
    }

    public String getProductName(){
        return this.productName;
    }

    public int getProductStock(){
        return this.productStock;
    }

    public Category getCategory(){
        return this.category;
    }

    public void setProId(int proId){
        this.proId = proId;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setProductStock(int productStock){
        this.productStock = productStock;
    }

    public void setCategory(Category category){
        this.category = category;
    }


}
