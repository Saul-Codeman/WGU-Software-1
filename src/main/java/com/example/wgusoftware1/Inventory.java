package com.example.wgusoftware1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * Inventory class that keeps track of all parts and products in the inventory
 */
public class Inventory {

    // Declare Fields
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Add parts
     * @param part added to allParts
     */
    public static void addPart(Part part){
        allParts.add(part);
    }

    /**
     * Add products
     * @param product add to allProducts
     */
    public static void addProduct(Product product){
        allProducts.add(product);
    }

    /**
     * Show all parts
     * @return allParts
     */
    // Get Parts and products
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Show all products
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    //Lookup parts and products by ID

    /**
     * Lookup parts by id
     * @param id to lookup
     * @return allParts.get(id)
     */
    public static Part lookupPart(int id){
        if(!allParts.isEmpty()){
            for(int i = 0; i < allParts.size(); i++){
                if(allParts.get(i).getId() == id){
                    return allParts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Lookup products by id
     * @param id to lookup
     * @return allProducts.get(id)
     */
    public static Product lookupProduct(int id){
        if(!allProducts.isEmpty()){
            for(int i = 0; i < allProducts.size(); i++){
                if(allProducts.get(i).getId() == id){
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }
    //Lookup Parts and Products by name

    /**
     * Lookup parts by name
     * @param name to lookup
     * @return allParts.get(name)
     */
    public static Part lookupPart(String name){
        if(!allParts.isEmpty()){
            for(int i = 0; i < allParts.size(); i++){
                if(allParts.get(i).getName() == name){
                    return allParts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Lookup products by name
     * @param name to lookup
     * @return allProducts.get(name)
     */
    public static Product lookupProduct(String name){
        if(!allProducts.isEmpty()){
            for(int i = 0; i < allProducts.size(); i++){
                if(allProducts.get(i).getName() == name){
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }
    //Update parts and products

    /**
     * Update parts
     * @param id of part to update
     * @param modifiedPart part modified
     */
    public static void updatePart(int id, Part modifiedPart){
        int i = -1;
        for (Part part : getAllParts()){
            i++;
            if(part.getId() == id){
                getAllParts().set(i, modifiedPart);
                return;
            }
        }
    }

    /**
     * Update products
     * @param id of product to update
     * @param modifiedProduct product modified
     */
    public static void updateProduct(int id, Product modifiedProduct){
        int i = -1;
        for (Product product : getAllProducts()){
            i++;
            if(product.getId() == id){
                getAllProducts().set(i, modifiedProduct);
                return;
            }
        }
    }

    /**
     * Delete part
     * @param part to delete
     * @return allParts.remove(part)
     * Delete part
     */
    public static boolean deletePart(Part part){
        return allParts.remove(part);
    }

    /**
     * Delete product
     * @param product to delete
     * @return allProducts.remove(product)
     * Delete product
     */
    public static boolean deleteProduct(Product product){
        return allProducts.remove(product);
    }

    // Searches for a part based on id or name

    /**
     * Search part by name or id
     * @param search id or name of part to be searched
     * @return Parts
     */
    public static ObservableList<Part> searchParts(String search){
        ObservableList<Part> Parts = FXCollections.observableArrayList();
        search = search.trim().toLowerCase();
        for (Part part : getAllParts()){
            if(part.getName().toLowerCase().contains(search) || Integer.toString(part.getId()).contains(search)){
                Parts.add(part);
            }
        }
        if (Parts.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Part not found");
            alert.showAndWait();
            return getAllParts();
        }else{
        return Parts;
        }
    }

    // Searches for products based on id or name

    /**
     * Search product by id or name
     * @param search id or name of product to be searched
     * @return Products
     */
    public static ObservableList<Product> searchProducts(String search){
        ObservableList<Product> Products = FXCollections.observableArrayList();
        search = search.trim().toLowerCase();
        for (Product product : getAllProducts()){
            if(product.getName().toLowerCase().contains(search) || Integer.toString(product.getId()).contains(search)){
                Products.add(product);
            }
        }
        if (Products.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Product not found");
            alert.showAndWait();
            return getAllProducts();
        }else{
            return Products;
        }
    }

}
