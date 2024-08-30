package com.example.hibernateP;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class Main{
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Scanner scan = new Scanner(System.in);
        try{
        boolean exit = false;
        while(exit != true){
            session.beginTransaction();
            System.out.println("Menu");
            System.out.println("1.Add Product, 2.Remove Product, 3.Product Catalog, 4.End Session, 5. Update Product");
            int choice = scan.nextInt();
            switch(choice){
                case 1: addProduct(session);
                        break;
                case 2: delProduct(session);
                        break;
                case 3: displayProduct(session);
                        break;
                case 4: System.out.println("Session Ended");
                        session.close();
                        HibernateUtil.getSessionFactory().close();
                        exit= true;
                        break;
                case 5: updateProdcut(session);
                        break;
                default: System.out.println("Select valid option");
            }
        }
        }catch(Exception e){System.out.println(e);}
        
    }

    static Category findByCatName(Session session,String catName){
        List<Category> categories = categoryFetch(session);
        for(Category category : categories){
            if(category.getCatName().equals(catName)){
                return category;
            }
        }
        return null;

    }

    static List<Category> categoryFetch(Session session){
        TypedQuery query = session.getNamedQuery("getAllCategories");

        List<Category> allCats = query.getResultList();
        return allCats;
    }

    static void addProduct(Session session){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Product Name");
        String proName = scan.next();

        System.out.println("Enter Product remaining Stock");
        int proStock = scan.nextInt();

        System.out.println("Enter Product Category");
        String proCategory = scan.next();
        Product product = new Product();
        product.setProductName(proName);
        product.setProductStock(proStock);

        Set<Product> products = new HashSet<>();

        Category category = findByCatName(session, proCategory);
        if (category != null){
            products = category.getProducts();
        }
        else{
            category = new Category();
            category.setCatName(proCategory);
            
        }
        product.setCategory(category);
        products.add(product);
        session.save(category);
        session.save(product);
        session.getTransaction().commit();
    }

    static void delProduct(Session session){
        Scanner scan = new Scanner(System.in);

        String delQuery = "delete from Product p where p.productName = :proName";

        System.out.println("Enter Product Name which should be deleted");
        String proName = scan.next();

        Query query = session.createQuery(delQuery);
        query.setParameter("proName", proName);
       
        int res = query.executeUpdate();
        session.getTransaction().commit();
    }

    static void displayProduct(Session session){
        
        List<Product> listProducts = session.createQuery("from Product",Product.class).list();
        System.out.println("Id | Product Name | Product Category | Remaining Stock");
        for(Product product : listProducts){
            System.out.print(product.getProId()+" "+product.getProductName()+" "+product.getCategory().getCatName()+" "+product.getProductStock());
            System.out.println();
        }
        session.getTransaction().commit();
    }

    static void updateProdcut(Session session){
        Scanner scan = new Scanner(System.in);

        

        System.out.println("Enter product name which you want to update");
        String proName = scan.next();

        System.out.println("Enter What you want to update: 1. Name, 2.Stock");
        int upValue = scan.nextInt();

        switch(upValue){
            case 1: 
            String nameQuery = "update Product p set p.productName = :newName where p.productName = :proName";
            Query queryN = session.createQuery(nameQuery);
            System.out.println("Enter new Name");
            String newName = scan.next();
            queryN.setParameter("newName", newName);
            queryN.setParameter("proName", proName);
            queryN.executeUpdate();
            break;
            
            case 2: 
            String stockQuery = "update Product p set p.productStock = :newStock where p.productName = :proName";
            Query queryS = session.createQuery(stockQuery);
            System.out.println("Enter remaining Stock");
            int newStock = scan.nextInt();
            queryS.setParameter("newStock", newStock);
            queryS.setParameter("proName", proName);
            queryS.executeUpdate();
            break;

            // case 3: 
            // String catQuery = "update Product p set p.productStock = :newCat where p.productName = :proName";
            // Query queryC = session.createQuery(catQuery);
            // System.out.println("Enter remaining Stock");
            // String newCat = scan.next();
            // queryC.setParameter("newStock", newCat);
            // queryC.setParameter("proName", proName);
            // queryC.executeUpdate();
            // break;

            default:System.out.println("Enter Valid Value");
        }
        session.flush();
        session.getTransaction().commit();
        
    }
}