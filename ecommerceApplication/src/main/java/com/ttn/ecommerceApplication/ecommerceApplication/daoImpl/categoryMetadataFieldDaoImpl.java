package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryMetadataFieldDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataField;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CategoryMetadataFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryMetadataFieldDaoImpl implements CategoryMetadataFieldDao
{

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;


    public List<CategoryMetadataField> viewCategoryMetadataField(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc(sortBy)));

        if (categoryMetadataFieldRepository.findAll(paging).isEmpty()) {
            throw new NotFoundException("This list is empty because no metadata is present");
        } else {
            Page<CategoryMetadataField> pageResult = categoryMetadataFieldRepository.findAll(paging);
            if (pageResult.hasContent()) {
                return pageResult.getContent();
            } else {
                throw new NotFoundException("This page has no content");
            }
        }
    }

    @Override
    public void deleteCategoryMetadataField(Long id) {

        if (categoryMetadataFieldRepository.findById(id).isPresent())
        {
            categoryMetadataFieldRepository.dele(id);
            categoryMetadataFieldRepository.deleteMetadatField(id);

        } else {
            throw new NotFoundException("This Category Metadata is not present");
        }
    }
}
