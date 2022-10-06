import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;
import java.util.*;
import java.io.*;

class Product {
    String name;
    String product_id;
    double price;
    String details;

    public void create_product(String name, String product_id, double price, String details) {
        this.name = name;
        this.product_id = product_id;
        this.price = price;
        this.details = details;
    }
}

class Category {
    // category object
    int category_id;
    String category_name;
    ArrayList<Product> product = new ArrayList<Product>();

    public void create_category(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }
}

class Discount {
    String product_id;
    Double discount_percentage;
    String customer;

    public void create_discount(String product_id, Double discount_percentage, String customer) {
        this.product_id = product_id;
        this.discount_percentage = discount_percentage;
        this.customer = customer;
    }
}

class Giveaway {
    String id1;
    String id2;
    int new_price;

    public void create_giveaway(String id1, String id2, int new_price) {
        this.id1 = id1;
        this.id2 = id2;
        this.new_price = new_price;
    }
}

class Admin {
    String name;
    static ArrayList<Integer> categoryID = new ArrayList<Integer>();
    static ArrayList<Category> categories = new ArrayList<Category>();
    static ArrayList<Discount> offers = new ArrayList<Discount>();
    static ArrayList<Giveaway> giveaway = new ArrayList<Giveaway>();

    public void login(String name, String pass) {
        if (!(pass.equals("Kuber23"))
                || !(((name.equalsIgnoreCase("Beff Jezos")) || name.equalsIgnoreCase("Gill Bates")))) {
            System.out.println("You have entered either Wrong Username or Password, Please check and retry!");
        } else {
            this.name = name;
            while (true) {
                System.out.println("Welcome " + this.name);
                System.out.println("1.) Add Category");
                System.out.println("2.) Delete Category");
                System.out.println("3.) Add Product");
                System.out.println("4.) Delete Product");
                System.out.println("5.) Set Discount on Products");
                System.out.println("6.) Add Giveaway Deals");
                System.out.println("7.) Back");

                Scanner func = new Scanner(System.in);
                int functionality = func.nextInt();

                if (functionality == 1) {
                    // Add Category
                    this.Add_Category();
                } else if (functionality == 2) {
                    // Delete Category
                    this.Delete_Category();
                } else if (functionality == 3) {
                    // Add Product
                    this.Add_Product();
                } else if (functionality == 4) {
                    // Delete Product
                    this.Delete_Product();
                } else if (functionality == 5) {
                    // Set Discount on Products
                    this.Set_discount_on_product();
                } else if (functionality == 6) {
                    // Add Giveaway Deals
                    this.Add_Giveaway_details();
                } else if (functionality == 7) {
                    // back
                    break;
                }
            }
        }
    }

    public void Add_Category() {
        // Function for adding category
        System.out.println("Add Category ID");
        Scanner cate = new Scanner(System.in);
        int category_ID = cate.nextInt();

        if (this.categoryID.contains(category_ID)) {
            // ID already taked
            System.out.println("ID already taken, please enter a different ID ");
        } else {

            Category cat = new Category();

            System.out.println("Add Category name");
            Scanner caten = new Scanner(System.in);
            String category_name = caten.nextLine();

            // ID is unique now proceed
            Product x = new Product();
            System.out.println("Product name:");
            Scanner p_name = new Scanner(System.in);
            String p_naam = p_name.nextLine();

            System.out.println("Product id:");
            Scanner p_i = new Scanner(System.in);
            String p_id = p_i.nextLine();

            System.out.println("Product price:");
            Scanner prizz = new Scanner(System.in);
            Double pp = prizz.nextDouble();

            System.out.println("Product details:");
            Scanner p_d = new Scanner(System.in);
            String details = p_d.nextLine();

            x.create_product(p_naam, p_id, pp, details);

            cat.create_category(category_ID, category_name);

            categories.add(cat);
            categoryID.add(category_ID);

        }

    }

    public void Delete_Category() {
        // Function for deleting category
        Scanner c_name = new Scanner(System.in);
        String c_naam = c_name.nextLine();

        System.out.println("Product id:");
        Scanner c_i = new Scanner(System.in);
        int c_id = c_i.nextInt();

        // iterate over the arraylist categories and delte the category with id == c_id
        // and name == c_name
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).category_name.equals(c_naam) && categories.get(i).category_id == c_id) {
                categories.remove(i);
                System.out.println("Category Removed");
                break;
            }
        }
    }

    public void Add_Product() {
        System.out.println("Enter Category ID");
        Scanner cidtbd = new Scanner(System.in);
        int id_in_which_product_is_to_be_added = cidtbd.nextInt();

        for (int i = 0; i < categoryID.size(); i++) {
            if (categories.get(i).category_id == id_in_which_product_is_to_be_added) {

                System.out.println("Add a product");
                Product p = new Product();

                System.out.println("Product name:");
                Scanner p_name = new Scanner(System.in);
                String p_naam = p_name.nextLine();

                System.out.println("Product id:");
                Scanner p_i = new Scanner(System.in);
                String p_id = p_i.nextLine();

                System.out.println("Product price:");
                Scanner prizz = new Scanner(System.in);
                Double pp = prizz.nextDouble();

                System.out.println("Product details:");
                Scanner p_d = new Scanner(System.in);
                String details = p_d.nextLine();

                p.create_product(p_naam, p_id, pp, details);
                categories.get(i).product.add(p);
            }
        }
    }

    public void Delete_Product() {
        // Function for deleting product
        System.out.println("Enter Category name");
        Scanner cidtbd = new Scanner(System.in);
        String name_in_which_product_is_to_be_deleted = cidtbd.nextLine();

        System.out.println("Enter id of product to be deleted");
        Scanner cidtb = new Scanner(System.in);
        String product_is_to_be_deleted = cidtb.nextLine();

        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).category_name.equals(name_in_which_product_is_to_be_deleted)) {
                if (categories.get(i).product.size() == 0) {
                    System.out.println("Category can't be empty first add some product and then delete this one");
                } else {
                    Product p = new Product();
                    for (int j = 0; i < categories.get(i).product.size(); j++) {
                        if (categories.get(i).product.get(j).product_id.equals(product_is_to_be_deleted)) {
                            p = categories.get(i).product.get(j);
                            break;
                        }
                    }
                    categories.get(i).product.remove(p);
                }
            }
        }
    }

    public void Set_discount_on_product() {
        // Function for setting discount on product
        // double discount_percentage;
        Discount d = new Discount();

        System.out.println("Product ID:");
        Scanner proid = new Scanner(System.in);
        String pid = proid.nextLine();

        System.out.println("Discount percentage:");
        Scanner prid = new Scanner(System.in);
        Double dp = prid.nextDouble();

        System.out.println("Customer which can avail:");
        Scanner cust = new Scanner(System.in);
        String cust_cat = cust.nextLine();

        d.create_discount(pid, dp, cust_cat);
        offers.add(d);
    }

    public void Add_Giveaway_details() {
        // Function for adding givewaway details
        Giveaway g = new Giveaway();
        System.out.println("Product 1 ID:");
        Scanner proid = new Scanner(System.in);
        String pid1 = proid.nextLine();

        System.out.println("Product 2 ID:");
        Scanner prid = new Scanner(System.in);
        String pid2 = prid.nextLine();

        System.out.println("New Price:");
        Scanner prd = new Scanner(System.in);
        int p_i_d = proid.nextInt();

        g.create_giveaway(pid1, pid2, p_i_d);

        giveaway.add(g);
    }
}

class Customer{
    String name;
    String password;
    double wallet;
    String currentStatus;
    ArrayList<Product> cart = new ArrayList<Product>();
    ArrayList<Product> quantcart = new ArrayList<Product>();

    public void create_customer(String name, String password){
        this.name = name;
        this.password = password;
        this.wallet = 1000;
        this.currentStatus = "NORMAL";
    }

    public void browse_products(){
        // browse products
    }
    public void browse_deals(){
        // browse deals
    }
    public void add_a_product_to_cart(){
        // add a product to cart
        System.out.println("Enter category ID");
        Scanner ci = new Scanner(System.in);
        int cat_id = ci.nextInt();
        
        System.out.println("Enter Product ID");
        Scanner pi = new Scanner(System.in);
        String prodId = pi.nextLine();

        System.out.println("Enter Product quantity");
        Scanner pqi = new Scanner(System.in);
        int prodqu = pqi.nextInt();

        for(int i = 0; i < Admin.categories.size(); i++){
            if(Admin.categories.get(i).category_id == cat_id){
                for(int j = 0; j<Admin.categories.get(i).product.size(); i++){
                    if(Admin.categories.get(i).product.get(j).product_id.equals(prodId)){
                        this.cart.add(Admin.categories.get(i).product.get(j));

                        for(int k = 0; k<prodqu; k++){
                            this.quantcart.add(Admin.categories.get(i).product.get(j));
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("Product not Available");

    
    }
    public void add_products_in_deal_to_cart(){
        // add products in deal to cart
    }
    public void view_coupons(){
        // view coupons
    }
    public void check_account_balance(){
        // check account balance
        System.out.println("Your current Balance is : " + this.wallet);
    }
    public void view_cart(){
        // view cart
        for(int i  = 0; i<this.cart.size(); i++){
            System.out.println(this.cart.get(i).name);
            System.out.println(this.cart.get(i).product_id);
            System.out.println(this.cart.get(i).price);
            System.out.println("***********************");
        }
    }
    public void empty_cart(){
        // empty cart
        this.cart.clear();
        System.out.println("Cart Emptied");
    }
    public void checkout_cart(){
        // checkout cart
    }
    public void upgrade_customer_status(){
        // upgrade customer status
        System.out.println("Choose new Status");
        System.out.println("1.) ELIE");
        System.out.println("2.) PRIME ");

        Scanner sta = new Scanner(System.in);
        int status = sta.nextInt();

        if(status == 1  & this.currentStatus!="ELITE"){
            this.currentStatus = "ELITE";
            this.wallet = this.wallet - 300;

            System.out.println("Status Updated!");
        }
        else if(status == 2 & this.currentStatus!="PRIME"){
            this.currentStatus = "PRIME";
            this.wallet = this.wallet - 200;

            System.out.println("Status Updated!");
        }
    }
    public void Add_amount_to_wallet(){
        // Add amount to wallet
        System.out.println("Enter the amount to add");
        Scanner am = new Scanner(System.in);
        double amt = am.nextDouble();

        this.wallet = this.wallet + amt;

        System.out.println("Amount Added Successfully");
    }
}

interface naam {
    static void name() {
        while (true) {
            System.out.println("Welcome to FLIPZON");
            System.out.println("1.) Enter as Admin");
            System.out.println("2.) Explore the Product Catalog");
            System.out.println("3.) Show Available Deals");
            System.out.println("4.) Enter as Customer");
            System.out.println("5.) Exit the Application");

            Scanner sc = new Scanner(System.in);
            int inp = sc.nextInt();
            Admin adm = new Admin();
            if (inp == 1) {
                // Enter as Admin
                Scanner adm_name = new Scanner(System.in);
                String admin_name = adm_name.nextLine();

                String password = null;
                Console co;
                if ((co = System.console()) != null) {
                    char[] input = co.readPassword();
                    password = new String(input);
                } else {
                    System.out.println("No input");
                }
                adm.login(admin_name, password);

            } else if (inp == 2) {
                // Explore the product Catalog
            } else if (inp == 3) {
                // Show available Deals
                for (int i = 0; i < adm.offers.size(); i++) {

                    System.out.println(adm.offers.get(i).product_id);
                    System.out.println(adm.offers.get(i).discount_percentage);
                    System.out.println(adm.offers.get(i).customer);

                }

            } else if (inp == 4) {
                ArrayList<Customer> customerList = new ArrayList<Customer>();
                // Enter as Customer
                while(true){
                    System.out.println("1.) Sign Up");
                    System.out.println("2.) Login");
                    System.out.println("3.) Back");

                    Scanner xc = new Scanner(System.in);
                    int cust_inp = xc.nextInt();


                    if(cust_inp == 1){

                        // Login
                        Scanner cust_name = new Scanner(System.in);
                        String customer_name = cust_name.nextLine();

                        Scanner custPass = new Scanner(System.in);
                        String cust_password = custPass.nextLine();

                        Customer custo = new Customer();
                        custo.create_customer(customer_name, cust_password);
                        
                        customerList.add(custo);
                        System.out.println("Registered Successfully !");

                    }
                    else if(cust_inp == 2){
                        // signup
                        Scanner name_C = new Scanner(System.in);
                        String C_name  = name_C.nextLine();


                        Scanner pass_C = new Scanner(System.in);
                        String C_pass  = pass_C.nextLine();
                        Customer n = new Customer();
                        n.create_customer(C_name, C_pass);

                        int flag = 0 ;

                        for(int i= 0; i<customerList.size(); i++){
                            if(customerList.get(i).name.equals(C_name)){
                                flag = 1;
                                break;
                            }
                        }


                        if(flag == 1){
                            while(true){
                                System.out.println("Welcome  " + C_name);
                                System.out.println("1.) browse products");
                                System.out.println("2.) browse deals");
                                System.out.println("3.) add a product to cart");
                                System.out.println("4.) add products in deal to cart");
                                System.out.println("5.) view coupons");
                                System.out.println("6.) check account balance");
                                System.out.println("7.) view cart");
                                System.out.println("8.) empty cart");
                                System.out.println("9.) checkout cart");
                                System.out.println("10.) upgrade customer status");
                                System.out.println("11.) Add amount to wallet");
                                System.out.println("12.) back");

                                Scanner xs = new Scanner(System.in);
                                int cust_input = xs.nextInt();

                                if(cust_input == 1){
                                    // browse products
                                    n.browse_products();
                                }
                                else if(cust_input == 2){
                                    // browse deals
                                    n.browse_deals();
                                }
                                else if(cust_input == 3){
                                    // add a product to cart//
                                    n.add_a_product_to_cart();
                                }
                                else if(cust_input == 4){
                                    // add products in deal to cart
                                    n.add_products_in_deal_to_cart();
                                }
                                else if(cust_input == 5){
                                    // view coupons
                                    n.view_cart();
                                }
                                else if(cust_input == 6){
                                    // check account balance //
                                    n.check_account_balance();
                                }
                                else if(cust_input == 7){
                                    // view cart //
                                    n.view_cart();
                                }
                                else if(cust_input == 8){
                                    // empty cart //
                                    n.empty_cart();
                                }
                                else if(cust_input == 9){
                                    // checkout cart
                                    n.checkout_cart();
                                }
                                else if(cust_input == 10){
                                    // upgrade customer status //
                                    n.upgrade_customer_status();
                                }
                                else if(cust_input == 11){
                                    // Add amount to wallet //
                                    n.Add_amount_to_wallet();
                                }
                                else if(cust_input == 12){
                                    // back //
                                    break;
                                }
                                
                            }
                        }
                        else{
                            System.out.println("Invalid User");
                        }
                    }
                    else{
                        break;
                    }
                }
            } else if (inp == 5) {
                // Exit the Application
                break;
            }
        }

    }
}

public class AP2 {

    public static void main(String[] args) {

        // Main Function
        naam.name();

    }
}
