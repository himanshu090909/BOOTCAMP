package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CartDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.OrdersDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Cart;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.ProductVariation;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CartRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CustomerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductVariationRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDaoImpl implements CartDao
{
    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrdersDao ordersDao;

    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void addToCart(Long product_variation_id,int quantity) {
        String username = getCurrentUser.getUser();
        Customer user = customerRepository.findByUsername(username);
        Cart cart = new Cart();
        Optional<ProductVariation> productVariation = productVariationRepository.findById(product_variation_id);
        cart.setProductVariation(productVariation.get());
        cart.setQuantity(quantity);
        cart.setCustomer(user);
        productVariation.get().addCarts(cart);
        cartRepository.save(cart);
    }
    @Override
    public void deleteFromCart(Long product_variation_id)
    {
        cartRepository.deleteByProductVariationId(product_variation_id);
    }
    @Override
    public void emptyCart()
    {
        String username = getCurrentUser.getUser();
        Customer customer = customerRepository.findByUsername(username);
        cartRepository.emptyUserCart(customer.getId());
    }
    @Override
    public List<Object[]> viewCart() {
        String username = getCurrentUser.getUser();
        Customer customer = customerRepository.findByUsername(username);
        List<Object[]> list = cartRepository.viewCart(customer.getId());
        if (list.isEmpty())
        {
            System.out.println("cart is empty");
        }
        return list;

    }

    @Override
    public void placeOrderFromCart(Long cartId,String paymentMethod,Long AddressId)
    {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        Cart cart= cartOptional.get();
        ProductVariation productVariation = cart.getProductVariation();
        int quantity= cart.getQuantity();
        ordersDao.placeNewOrder(productVariation.getId(),quantity,paymentMethod,AddressId);
        deleteFromCart(productVariation.getId());


    }

    @Override
    public void orderWholeCart( Long AddressId)
    {
        for(Cart cart : cartRepository.findAll())
        {
            int quantity= cart.getQuantity();
            ProductVariation productVariation =cart.getProductVariation();
            ordersDao.placeNewOrder(productVariation.getId(),quantity,"COD",AddressId);
            emptyCart();

        }
    }



}
