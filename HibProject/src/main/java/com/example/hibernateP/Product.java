package com.example.hibernateP;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQueries(
    {
    @NamedQuery(
        name="getAllCategories",
        query="select p.category from Product p"
    )
})

@Entity
@Table(name="Product")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="proId")
    private int proId;
    private String productName;
    private int productStock;
    private String category;

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

    public String getCategory(){
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

    public void setCategory(String category){
        this.category = category;
    }


}
