package com.ttn.ecommerceApplication.ecommerceApplication.dto;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Address;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class FullDetailDTO
{
    ViewProductDTO productDTO;
    String sellerName;
    List<AddressDTO> addressDTO;
    List<ProductVariationDTO> productVariationDTO;
    List<String> links;

    public List<ProductVariationDTO> getProductVariationDTO() {
        return productVariationDTO;
    }

    public void setProductVariationDTO(List<ProductVariationDTO> productVariationDTO) {
        this.productVariationDTO = productVariationDTO;
    }

    public ViewProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ViewProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<AddressDTO> getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(List<AddressDTO> addressDTO) {
        this.addressDTO = addressDTO;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
