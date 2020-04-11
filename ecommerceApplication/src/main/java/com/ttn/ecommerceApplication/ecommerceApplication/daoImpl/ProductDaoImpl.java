package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CategoryRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.SellerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CategoryRepository categoryRepository;

  /*  @Override
    public Iterable<Product> getAllProducts(Long id) {
       List<Long> longList = productRepository.ids(id);
       return  productRepository.findAllById(longList);
   }
*/

    @Override
    public void save(Product product) {

    }


    @Override
    public void addProduct(Product product,String category) {
        Long id =  categoryRepository.getIdOfParentCategory(category);
        String sellername = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(sellername);
        Set<Product> productSet = new HashSet<>();
        Product product1 = new Product();
        product1.setBrand(product.getBrand());
        product1.setActive(product.isActive());
        product1.setProductname(product.getProductname());
        product1.setSeller(seller);
        product1.setCategory1(categoryRepository.findById(id).get());
        productSet.add(product1);
        productRepository.save(product1);
    }

    @Override
    public List<Object[]> getProductDetails() {
        String username = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(username);
        List<Object[]> objects = productRepository.getProductss(seller.getId());
        return objects;
    }

    @Override
    public void deleteProduct(String productName)
    {
        Optional<Product> product = productRepository.findById(productRepository.findProduct(productName));
        Product product1 = product.get();
        String seller= getCurrentUser.getUser();
        Seller seller1 = sellerRepository.findByUsername(seller);
        if ((product1.getSeller().getUsername()).equals(seller1.getUsername()))
        {
            productRepository.deleteProductVariation(product1.getID());
            productRepository.deleteProduct(product1.getID(), productName);

        }
        else {
            throw  new NullException("You cant delete this product");
        }


    }


    @Override
    public void editProduct(Product product, String productName) {

            String seller= getCurrentUser.getUser();
            Seller seller1= sellerRepository.findByUsername(seller);
            Optional<Product> productOptional = productRepository.findById(productRepository.findProduct(productName));
            Product product1 = productOptional.get();

            if ((product1.getSeller().getUsername()).equals(seller1.getUsername()))
            {
                product1.setDescription(product.getDescription());
                product1.setReturnable(product.getisReturnable());
                product1.setActive(product.getisActive());
                product1.setCancellable(product.getisCancellable());
                productRepository.save(product1);
            }
            else {
                throw new NullException("You cannot edit this product");
            }
    }


    public Long getid(String productName) {
        Long id = productRepository.getProductVariationid(productName);
        return id;
    }

    public void setStatus( String productName)
    {
        String seller = getCurrentUser.getUser();
        Seller seller1 = sellerRepository.findByUsername(seller);
        List<Object[]> objects = getProductDetails();
        Optional<Product> product = productRepository.findById(productRepository.findProduct(productName));
        Product product1 = product.get();
        if ((product1.getSeller().getUsername()).equals(seller1.getUsername()))
        {
            for (Object[] object : objects) {

                if (object[0].equals(productName))
                {
                    productRepository.setActiveStatusOfProduct(productName);
                    productRepository.setActiveStatusOfProductAndProductVariation(product1.getID());
                }
            }
        }
        else
        {
            throw  new NullException("You cannot edit the status of this product");
        }

    }


}
