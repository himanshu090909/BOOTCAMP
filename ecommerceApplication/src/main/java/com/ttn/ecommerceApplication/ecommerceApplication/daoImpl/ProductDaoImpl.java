package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ProductDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CategoryRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.SellerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    @Lazy
    @Autowired
    NotificationService notificationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ModelMapper modelMapper;

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
    public void addProduct(Product product, Long categoryid) {
        String sellername = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(sellername);
        Set<Product> productSet = new HashSet<>();
        Product product1 = new Product();
        product1.setBrand(product.getBrand());
        product1.setActive(false);
        product1.setCancellable(product.getisCancellable());
        product1.setDescription(product.getDescription());
        product1.setProductname(product.getProductname());
        product1.setSeller(seller);
        product1.setCategory1(categoryRepository.findById(categoryid).get());
        productSet.add(product1);
        productRepository.save(product1);
    }



    @Override
    public void addNewProduct(ProductDTO product, Long categoryId)
    {
        int result = categoryRepository.checkIfLeaf(categoryId);
        if (result==1)
        {
            if (product.getProductname().equals(categoryRepository.findById(categoryId).get().getName())||product.getProductname().equals(product.getBrand()))
            {
                throw new NullException("name of category,product and brand cannot be same");
            }
            Product product1 = modelMapper.map(product,Product.class);
            addProduct(product1,categoryId);
        }
        else
        {
            throw new NotFoundException("category is not a leaf category");
        }
        Long productId =productRepository.findProduct(product.getProductname());
        Long id = userRepository.findAdmin();
        User user= userRepository.findById(id).get();
        String text= "Product Brand: "+product.getBrand()+"\n"+"Product Description: "+
                product.getDescription()+"\n"+"Product ID: "+productId;
        notificationService.sendToAdmin(user,text);
    }

    @Override
    public List<Object[]> getProductDetails() {
        String username = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(username);
        List<Object[]> objects = productRepository.getProductss(seller.getId());
        return objects;
    }

    @Override
    public void deleteProduct(Long productId)
    {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            System.out.println("1");
            Product product1 = product.get();
            String seller = getCurrentUser.getUser();
            Seller seller1 = sellerRepository.findByUsername(seller);
            if ((product1.getSeller().getUsername()).equals(seller1.getUsername())) {
                productRepository.deleteProductVariation(product1.getID());
                productRepository.deleteProduct(product1.getID(), product1.getProductname());

            } else {
                throw new NullException("You cant delete this product");
            }
        }
        else
        {
            throw new NotFoundException("product with this id is not present");
        }


    }


    @Override
    public void editProduct(ProductDTO product, Long id) throws IllegalAccessException {

            String seller= getCurrentUser.getUser();
            Seller seller1= sellerRepository.findByUsername(seller);
            Optional<Product> productOptional = productRepository.findById(id);
          if (productOptional.isPresent()) {
              Product product1 = productOptional.get();

              if ((product1.getSeller().getUsername()).equals(seller1.getUsername()))
              {
                  if (product.getProductname().equals(product1.getBrand())||product.getProductname().equals(product1.getCategory1().getName())||product.getProductname().equals(seller1.getFirstName())||product.getProductname().equals(product.getBrand()))
                  {
                      throw new NullException("productName should be unique with respect to brand catgeory and seller");
                  }
                  if (product.getProductname()!=null)
                  {
                      product1.setProductname(product.getProductname());
                  }
                  if (product.getBrand()!=null)
                  {
                      product1.setBrand(product.getBrand());
                  }
                  if (product.getisCancellable()!=null)
                  {
                      product1.setCancellable(product.getisCancellable());
                  }
                  if (product.getisReturnable()!=null)
                  {
                      product1.setReturnable(product.getisReturnable());
                  }
                  if (product.getDescription()!=null)
                  {
                      product1.setDescription(product.getDescription());
                  }
                  if (product.getisActive()!=null)
                  {
                      product1.setActive(product.getisActive());
                  }
                  productRepository.save(product1);
              }
              else {
                  throw new NullException("You cannot edit this product");
              }
          }
          else
          {
              throw new NotFoundException("product with this is is not present");
          }
    }


    public Long getid(String productName) {
        Long id = productRepository.getProductVariationid(productName);
        return id;
    }



    @Override
    public List<Object[]> viewProduct(Long product_id) {
        return null;
    }

    public List<Object[]> viewSingleProduct(Long productId)
    {
        String sellername= getCurrentUser.getUser();
        Seller seller= sellerRepository.findByUsername(sellername);
        Optional<Product> product=  productRepository.findById(productId);
        if (product.isPresent())
        {
            if (product.get().isActive()) {
                if ((product.get().getSeller().getUsername()).equals(seller.getUsername())) {
                    return productRepository.getSingleProduct(productId);
                } else {
                    throw new NotFoundException("You cannot view this product");
                }
            }
            else
            {
                throw new NullException("product is not active right now");
            }
        }
        else
        {
            throw new NotFoundException("product with this id is not present");
        }
    }

    @Override
    public List<Object[]> viewSingleProductForAdmin(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent())
        {
            return productRepository.getSingleProduct(productId);
        } else {
            throw new NotFoundException("This product ID is wrong");
        }
    }



    @Override
    public void deactivate(Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            if (product.getisActive() == true) {
                String seller = productRepository.getThatSeller(productId);
                System.out.println(seller);
                Seller seller1 = sellerRepository.findByUsername(seller);
                System.out.println(product.getBrand());
                productRepository.setActiveStatusOfProduct(productId);
                productRepository.setActiveStatusOfProductAndProductVariation(product.getID());
                System.out.println(seller1.getUsername());
                String subject = "Regarding deactivation";
                String text = product.getProductname() + "  got deactivated by admin";
                notificationService.sendToSeller(seller1, subject, text);
            } else {
                throw new NotFoundException("This product is already de-activated");
            }
        } else {
            throw new NotFoundException("This product is not found");
        }


    }

    @Override
    public void activateProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            if (product.getisActive() == false) {
                String seller = productRepository.getThatSeller(productId);
                System.out.println(seller);
                Seller seller1 = sellerRepository.findByUsername(seller);
                System.out.println(product.getBrand());
                productRepository.activateTheProduct(productId);
                System.out.println(seller1.getUsername());
                String subject = "Regarding Activation";
                String text = product.getProductname() + "  got activated by admin";
                notificationService.sendToSeller(seller1, subject, text);
            } else {
                throw new NotFoundException("This product is already activated");
            }
        } else {
            throw new NotFoundException("This product is not found");
        }

    }


}
