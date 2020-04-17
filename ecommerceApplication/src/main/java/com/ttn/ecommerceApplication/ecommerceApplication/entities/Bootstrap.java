package com.ttn.ecommerceApplication.ecommerceApplication.entities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Component
public class Bootstrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryMetadataFieldValuesRepository categoryMetadataFieldValuesRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Set<User> users = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        Set<Address> addresses = new HashSet<>();
        Customer user = new Customer();
        user.setFirstName("himanshu");
        user.setLastName("bhansali");
        user.setEnabled(true);
        user.setActive(true);
        user.setContactNo("09711247133");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setUsername("admin@gmail.com");
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        users.add(user);
        role.setUsers(users);
        roles.add(role);
        user.setRoles(roles);
        Address address = new Address();
        address.setCity("delhi");
        address.setCountry("india");
        address.setLabel("office");
        address.setZipcode("110095");
        address.setState("delhi");
        address.setAddressLine("jhilmil colony");
        addresses.add(address);
        address.setUser(user);
        user.setAddresses(addresses);
        userRepository.save(user);

        Set<User> userSet = new HashSet<>();
        Set<Role> roleSet = new HashSet<>();
        Set<Address> addresses5 = new HashSet<>();
        Customer user1 = new Customer();
        user1.setFirstName("customer");
        user1.setLastName("customer");
        user1.setEnabled(true);
        user1.setActive(true);
        user1.setContactNo("09711247133");
        user1.setPassword(passwordEncoder.encode("customer"));
        user1.setUsername("customer@gmail.com");
        Role role5 = new Role();
        role5.setRole("ROLE_CUSTOMER");
        userSet.add(user1);
        role5.setUsers(userSet);
        roleSet.add(role5);
        user1.setRoles(roleSet);
        Address address5 = new Address();
        address5.setCity("delhi");
        address5.setCountry("india");
        address5.setLabel("office");
        address5.setZipcode("110095");
        address5.setState("delhi");
        address5.setAddressLine("jhilmil colony");
        addresses5.add(address5);
        address5.setUser(user1);
        user1.setAddresses(addresses5);
        userRepository.save(user1);






        Category category = new Category("Clothing");

        Set<Category> categories = new HashSet<>();

        Category category1 = new Category("Men");
        Category category2 = new Category("Women");
        //men
        Category category3 = new Category("Jeans");
        Category category4 = new Category("Shirts");
        Category category5 = new Category("Winter Wear");
        //women
        Category category6 = new Category("Western Wear");
        Category category7 = new Category("Ethnic Wear");
        Category category8 = new Category("Accessories");

        //add to set of categories
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);
        categories.add(category7);
        categories.add(category8);

        category1.setCategory(category);
        category2.setCategory(category);

        category3.setCategory(category1);
        category4.setCategory(category1);
        category5.setCategory(category1);

        category6.setCategory(category2);
        category7.setCategory(category2);
        category8.setCategory(category2);

        category.setCategories(categories);
        category1.setCategories(categories);
        category2.setCategories(categories);

        Set<Product> products = new HashSet<>();

        Product product = new Product("Casual Shirts", "Tommy Hilfiger");
        product.setDescription("Sunset pocket shirt in Yellow ang grey checks colour." +
                " Featuring the same 1902 sunset pocket shape as shirts from the levi's archives; ");
        product.setActive(true);
        product.setCancellable(true);
        product.setReturnable(true);

        Product product1 = new Product("Formal Shirts", "Van Heusen");
        product1.setDescription("This is Asia size,please confirm you size before purchase." +
                "Please allow 1-2cm measuring deviation due to manual measurement.");
        product1.setActive(false);
        product1.setCancellable(true);
        product1.setReturnable(true);

        Product product2 = new Product("Slim Fit", "Calvin Klein");
        product2.setDescription("This is a very nice slim fit jeans");
        product2.setActive(true);
        product2.setCancellable(true);
        product2.setReturnable(true);

        Product product3 = new Product("Regular Fit", "Calvin Klein");
        product3.setDescription("This is a very nice regular fit jeans");
        product3.setActive(true);
        product3.setCancellable(true);
        product3.setReturnable(true);

        Product product4 = new Product("Joggers", "Wrangler");
        product4.setDescription("Sport these jogger jeans and show off your casual side.");
        product4.setActive(true);
        product4.setCancellable(true);
        product4.setReturnable(true);

        Product product5 = new Product("Jackets", "Belstaff");
        product5.setDescription("This is a very nice jacket");
        product5.setActive(true);
        product5.setCancellable(true);
        product5.setReturnable(true);

        Product product6 = new Product("Sweaters", "Roadster");
        product6.setDescription("This is a very nice Sweater");
        product6.setActive(false);
        product6.setCancellable(true);
        product6.setReturnable(true);

        Product product7 = new Product("Tops", "Polo");
        product7.setDescription("Aubergine solid blouson top," +
                " has a mandarin collar with button closure, " +
                "three-quarter puff sleeves, elasticated hem");
        product7.setActive(true);
        product7.setCancellable(true);
        product7.setReturnable(true);

        Product product8 = new Product("Jumpsuits", "DressBerry");
        product8.setDescription("FotoableArc is an online fashion label which believes" +
                " in dressing the Indian woman for both her perfect and not-so-perfect moments. ");
        product8.setActive(true);
        product8.setCancellable(true);
        product8.setReturnable(true);

        Product product9 = new Product("Sarees", "Satya paul");
        product9.setDescription("ANNI DESIGNER women's Raniyal georgette saree with blouse.");
        product9.setActive(true);
        product9.setCancellable(true);
        product9.setReturnable(true);

        Product product10 = new Product("Kurta", "Biba");
        product10.setDescription("Here is the kurti by Vaamsi. Ideal for parties and festivals.");
        product10.setActive(true);
        product10.setCancellable(true);
        product10.setReturnable(true);

        Product product11 = new Product("Gloves", "Adidas");
        product11.setDescription("Diya Home hand gloves are Cotton made Thin, Soft to touch and comfy for wearing.");
        product11.setActive(true);
        product11.setCancellable(true);
        product11.setReturnable(true);

        Product product12 = new Product("Stoles", "Anekaant");
        product12.setDescription("When you're looking for that perfect " +
                "finishing touch to add-on your sweet sense of style, simply toss on the fashion scarf");
        product12.setActive(true);
        product12.setCancellable(true);
        product12.setReturnable(true);


        product.setCategory1(category4);
        product1.setCategory1(category4);

        product2.setCategory1(category3);
        product3.setCategory1(category3);
        product4.setCategory1(category3);

        product5.setCategory1(category5);
        product6.setCategory1(category5);

        product7.setCategory1(category6);
        product8.setCategory1(category6);

        product9.setCategory1(category7);
        product10.setCategory1(category7);

        product11.setCategory1(category8);
        product12.setCategory1(category8);

        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        products.add(product11);
        products.add(product12);

        category.setProducts(products);

        Seller seller = new Seller();
        Seller seller1 = new Seller();
        Seller seller2 = new Seller();
        Seller seller3 = new Seller();


        product.setSeller(seller);
        product1.setSeller(seller);
        product2.setSeller(seller);
        product3.setSeller(seller1);
        product4.setSeller(seller1);
        product5.setSeller(seller1);
        product6.setSeller(seller2);
        product7.setSeller(seller2);
        product8.setSeller(seller2);
        product9.setSeller(seller3);
        product10.setSeller(seller3);
        product11.setSeller(seller3);
        product12.setSeller(seller3);


        Role role1 = new Role("ROLE_SELLER");
            /*Role role2= new Role("ROLE_SELLER");
            Role role1= new Role("ROLE_SELLER");
            Role role1= new Role("ROLE_SELLER");*/

        seller.setProducts(products);

        seller.setFirstName("Priti");
        seller.setLastName("Bhati");
        seller.addRoles(role1);
        seller.setCreatedBy("Priti");
        seller.setEnabled(true);
        seller.setActive(true);
        seller.setGstNo("27AAACS8577K2ZO");
        seller.setCompanyContactNo("+919843928645");
        seller.setCompanyName("Tommy Hilfiger");
        seller.setPassword(passwordEncoder.encode("admins"));
        seller.setUsername("admins@gmail.com");
        Set<Address> addressSet= new HashSet<>();
        Address address1= new Address();
        address1.setAddressLine("sadar");
        address1.setState("delhi");
        address1.setZipcode("110010");
        address1.setCountry("India");
        address1.setCity("delhi");
        address1.setLabel("HOME");
        addressSet.add(address1);
        address1.setUser(seller);
        seller.setAddresses(addressSet);

        seller1.setFirstName("Himanshu");
        seller1.setLastName("Bhansali");
        seller1.setCreatedBy("Himanshu");
        seller1.setUsername("Himanshu123@gmail.com");
        seller1.addRoles(role1);
        seller1.setPassword(passwordEncoder.encode("Sel1@ler"));
        seller1.setCompanyContactNo("+917564392867");
        seller1.setCompanyName("Zara");
        seller1.setGstNo("27AAACS8677K2ZO");
        seller1.setEnabled(true);
        seller1.setActive(true);

        seller2.setFirstName("Ankit");
        seller2.setLastName("Sagar");
        seller2.setUsername("ankit123@gmail.com");
        seller2.setCreatedBy("Ankit");
        seller2.setGstNo("27AAACS9577K2ZO");
        seller2.addRoles(role1);
        seller2.setPassword(passwordEncoder.encode("Seller@2"));
        seller2.setCompanyContactNo("+919876543212");
        seller2.setCompanyName("Armani");
        seller2.setEnabled(true);
        seller2.setActive(true);

        seller3.setFirstName("Shivam");
        seller3.setLastName("Sharma");
        seller3.setUsername("shivam123@gmail.com");
        seller3.setCreatedBy("Shivam");
        seller3.setGstNo("27AAACS8578K2ZO");
        seller3.addRoles(role1);
        seller3.setPassword(passwordEncoder.encode("Seller@3"));
        seller3.setCompanyContactNo("+918964392869");
        seller3.setCompanyName("Prada");
        seller3.setEnabled(true);
        seller3.setActive(true);

        ProductVariation productVariation = new ProductVariation();
        Set<ProductVariation> productVariationSet = new HashSet<>();
        productVariation.setPrice(1234d);
        productVariation.setQuantity_available(3);
        productVariation.setProduct(product);
        productVariation.setActive(true);
        Map<String,Object> attributes = new HashMap<>();
        attributes.put("size","L");
        attributes.put("color","black");
        productVariation.setInfoAttributes(attributes);
        String  info = objectMapper.writeValueAsString(attributes);
        productVariation.setInfoJson(info);
        productVariationSet.add(productVariation);
        product.setProductVariations(productVariationSet);

        ProductVariation productVariation1 = new ProductVariation();
        productVariation1.setPrice(1500d);
        productVariation1.setQuantity_available(3);
        Map<String,Object> attributes1 = new HashMap<>();
        attributes.put("size","M");
        attributes.put("color","Blue");
        productVariation1.setInfoAttributes(attributes1);
        String  info1 = objectMapper.writeValueAsString(attributes);
        productVariation1.setInfoJson(info1);


        productVariation1.setProduct(product);
        productVariationSet.add(productVariation1);
        /*product.setProductVariations(productVariationSet);*/

        ProductVariation productVariation2 = new ProductVariation();
        productVariation2.setPrice(1000d);
        productVariation2.setQuantity_available(10);
        productVariation2.setProduct(product1);
        productVariationSet.add(productVariation2);
        product1.setProductVariations(productVariationSet);


        ProductVariation productVariation3 = new ProductVariation();
        productVariation3.setPrice(700d);
        productVariation3.setQuantity_available(20);
        productVariation3.setProduct(product1);
        productVariationSet.add(productVariation3);
        product1.setProductVariations(productVariationSet);


        ProductVariation productVariation4 = new ProductVariation();
        productVariation4.setPrice(1500d);
        productVariation4.setQuantity_available(3);
        productVariation4.setProduct(product2);
        productVariationSet.add(productVariation4);
        product2.setProductVariations(productVariationSet);


        ProductVariation productVariation5 = new ProductVariation();
        productVariation5.setPrice(1475d);
        productVariation5.setQuantity_available(5);
        productVariation5.setProduct(product2);
        productVariationSet.add(productVariation5);
        product2.setProductVariations(productVariationSet);


        ProductVariation productVariation6 = new ProductVariation();
        productVariation6.setPrice(1700d);
        productVariation6.setQuantity_available(10);
        productVariation6.setProduct(product3);
        productVariationSet.add(productVariation6);
        product3.setProductVariations(productVariationSet);

        ProductVariation productVariation7 = new ProductVariation();
        productVariation7.setPrice(800d);
        productVariation7.setQuantity_available(30);
        productVariation7.setProduct(product4);
        productVariationSet.add(productVariation7);
        product4.setProductVariations(productVariationSet);

        ProductVariation productVariation8 = new ProductVariation();
        productVariation8.setPrice(1900d);
        productVariation8.setQuantity_available(4);
        productVariation8.setProduct(product5);
        productVariationSet.add(productVariation8);
        product5.setProductVariations(productVariationSet);

        ProductVariation productVariation9 = new ProductVariation();
        productVariation9.setPrice(1800d);
        productVariation9.setQuantity_available(5);
        productVariation9.setProduct(product6);
        productVariationSet.add(productVariation9);
        product6.setProductVariations(productVariationSet);

        ProductVariation productVariation10 = new ProductVariation();
        productVariation10.setPrice(1185d);
        productVariation10.setQuantity_available(9);
        productVariation10.setProduct(product7);
        productVariationSet.add(productVariation10);
        product7.setProductVariations(productVariationSet);

        ProductVariation productVariation11 = new ProductVariation();
        productVariation11.setPrice(1700d);
        productVariation11.setQuantity_available(4);
        productVariation11.setProduct(product8);
        productVariationSet.add(productVariation11);
        product8.setProductVariations(productVariationSet);


        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);
        productRepository.save(product9);
        productRepository.save(product10);
        productRepository.save(product11);
        productRepository.save(product12);



        CategoryMetadataField categoryMetadataField = new CategoryMetadataField();
        categoryMetadataField.setName("SIZE");
        categoryMetadataFieldRepository.save(categoryMetadataField);

        CategoryMetadataFieldValuesId categoryMetadataFieldValuesId= new CategoryMetadataFieldValuesId();
        categoryMetadataFieldValuesId.setCid(category4.getId());
        categoryMetadataFieldValuesId.setMid(categoryMetadataField.getId());


        CategoryMetadataFieldValues categoryMetadataFieldValues= new CategoryMetadataFieldValues();
        categoryMetadataFieldValues.setFieldValues("L,M,S");
        categoryMetadataFieldValues.setCategoryMetadataFieldValuesId(categoryMetadataFieldValuesId);
        categoryMetadataFieldValues.setCategoryMetadataField(categoryMetadataField);
        categoryMetadataFieldValues.setCategory(category4);

        Set<CategoryMetadataFieldValues> categoryMetadataFieldValues1 = new HashSet<>();
        categoryMetadataFieldValues1.add(categoryMetadataFieldValues);
        category4.setCategoryMetadataFieldValues(categoryMetadataFieldValues1);

        categoryMetadataFieldValuesRepository.save(categoryMetadataFieldValues);


        CategoryMetadataField categoryMetadataField1 = new CategoryMetadataField();
        categoryMetadataField1.setName("COLOUR");
        categoryMetadataFieldRepository.save(categoryMetadataField1);

        CategoryMetadataFieldValuesId categoryMetadataFieldValuesId1= new CategoryMetadataFieldValuesId();
        categoryMetadataFieldValuesId1.setCid(category4.getId());
        categoryMetadataFieldValuesId1.setMid(categoryMetadataField1.getId());


        CategoryMetadataFieldValues categoryMetadataFieldValues2= new CategoryMetadataFieldValues();
        categoryMetadataFieldValues2.setFieldValues("BLACK,BLUE");
        categoryMetadataFieldValues2.setCategoryMetadataFieldValuesId(categoryMetadataFieldValuesId1);
        categoryMetadataFieldValues2.setCategoryMetadataField(categoryMetadataField1);
        categoryMetadataFieldValues2.setCategory(category4);

        Set<CategoryMetadataFieldValues> categoryMetadataFieldValues3 = new HashSet<>();
        categoryMetadataFieldValues3.add(categoryMetadataFieldValues2);
        category4.setCategoryMetadataFieldValues(categoryMetadataFieldValues3);

        categoryMetadataFieldValuesRepository.save(categoryMetadataFieldValues2);












       /* Set<CategoryMetadataFieldValues> categoryMetadataFieldsValues = new HashSet<>();
        CategoryMetadataField categoryMetadataField = new CategoryMetadataField();
        categoryMetadataField.setName("Size");
        CategoryMetadataFieldValues categoryMetadataFieldValues = new CategoryMetadataFieldValues();
        categoryMetadataFieldValues.setFieldValues("L,M,S,XS");
        categoryMetadataFieldsValues.add(categoryMetadataFieldValues);
        categoryMetadataFieldValues.setCategoryMetadataField(categoryMetadataField);
        categoryMetadataFieldValues.setCategory(category4);
        category4.setCategoryMetadataFieldValues(categoryMetadataFieldsValues);
        categoryMetadataField.setCategoryMetadataFieldValues(categoryMetadataFieldsValues);
        CategoryMetadataFieldValuesId categoryMetadataFieldValuesId = new CategoryMetadataFieldValuesId(category4.getId(),categoryMetadataField.getId());
        categoryMetadataFieldValues.setCategoryMetadataFieldValuesId(categoryMetadataFieldValuesId);
        categoryMetadataFieldRepository.save(categoryMetadataField);
        categoryMetadataFieldValuesRepository.save(categoryMetadataFieldValues);*/


/*
        Category category = new Category();
        category.setName("Fashion");
        Set<Category> categories = new HashSet<>();
        Category category1 = new Category();
        Category category2 = new Category();
        category1.setName("men");
        category2.setName("women");
        categories.add(category1);
        categories.add(category2);
        category1.setCategory(category);
        category2.setCategory(category);
        category.setCategories(categories);

        Set<Product> products = new HashSet<>();
        Product product = new Product();
        product.setBrand("polo shirt");
        product.setProductname("abcd");
        product.setCategory1(category2);
        Product product1 = new Product();
        product1.setProductname("sdfg");
        product1.setBrand("dfgdj");
        product1.setCategory1(category2);
        products.add(product);
        products.add(product1);
        category2.setProducts(products);
        Seller seller = new Seller();
        product.setSeller(seller);
        product1.setSeller(seller);
        seller.setProducts(products);
        seller.setFirstName("himanshuf");
        seller.setLastName("bhansalis");
        seller.setEmail("himanshub@gmail.com");
        seller.setEnabled(true);
        seller.setActive(true);
        seller.setPassword(passwordEncoder.encode("admins"));
        seller.setUsername("admins");

        Set<ProductVariation> productVariations = new HashSet<>();
        ProductVariation productVariation = new ProductVariation();
        productVariation.setPrice(23.3);
        productVariations.add(productVariation);
        productVariation.setProduct(product);
        product.setProductVariations(productVariations);

        productRepository.save(product);
        productRepository.save(product1);

       *//* Customer customer = new Customer();
        customer.setName("himanshu");
        HashSet<PhoneNumber> numbers = new HashSet<>();
        PhoneNumber ph1 = new PhoneNumber();
        ph1.setNumber("45678");
        ph1.setType("cell");
        *//**//*  ph1.setCustomer(customer);
         *//**//*  numbers.add(ph1);
        PhoneNumber ph2 = new PhoneNumber();
        ph2.setNumber("45678");
        ph2.setType("home");
        *//**//*   ph2.setCustomer(customer);
         *//**//*    numbers.add(ph2);
        customer.setNumbers(numbers);
        customerRepository.save(customer);

*//*
    }*/
    }
}
