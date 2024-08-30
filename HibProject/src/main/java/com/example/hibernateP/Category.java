package com.example.hibernateP;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@NamedQueries(
    {
    @NamedQuery(
        name="getAllCategories",
        query="select c from Category c"
    )
})

@Entity
@Table(name="Category")
public class Category {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int catId;

    private String catName;

    @OneToMany(mappedBy="category")
    private Set<Product> products;

    Category(){}

    public int getCatId(){
        return this.catId;
    }

    public String getCatName(){
        return this.catName;
    }

    public Set<Product> getProducts(){
        return this.products;
    }

    public void setCatId(int catId){
        this.catId = catId;
    }

    public void setCatName(String catName){
        this.catName = catName;
    }

    public void setProducts(Set<Product> products){
        this.products = products;
    }
}
