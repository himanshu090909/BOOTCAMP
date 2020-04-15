package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ViewCategoriesDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.*;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryDaoImpl implements CategoryDao
{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    CategoryMetadataFieldValuesRepository categoryMetadataFieldValuesRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Object[]> getAllCategory()
    {

        List<Object[]> list = categoryRepository.getMainCategory();
        if (list.isEmpty())
        {
            throw new NullException("no categories available");
        }
        return list;
    }

    @Override
    public List<Object[]> getAllSubCategory(String mainCategory) {
       return categoryRepository.getSubCategory(mainCategory);
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void addNewSubCategory(Long parentCategory_id,Category category)
    {
        int result = categoryRepository.checkIfLeaf(parentCategory_id);
        if (result==1)
        {
            Optional<Category> category1 = categoryRepository.findById(parentCategory_id);
            if (category1.isPresent()) {
                category.setCategory(category1.get());
                categoryRepository.save(category);
            }
            else
            {
                throw new NotFoundException("category with this id is not present");
            }
        }
        else
        {
            throw new NullPointerException("parent category you selected is already a leaf node");
        }
    }

    @Override
    public ResponseEntity addMainCategory(Category category)
    {
        categoryRepository.save(category);
        return ResponseEntity.ok().body("category added");
    }

    @Override
    public List<Object[]> getSubcategory() {
            List<Object[]> objects = sellerRepository.getSubcategory();
            return objects;
        }






    public List<Object[]> getFilteringDetails(Long category_id)
    {
        List<Long> longList = categoryMetadataFieldValuesRepository.getMetadataId(category_id);
        List<Object[]> list = new ArrayList<>();
        Object[] o = new Object[1];
        o[0] = "metadata Field";
        list.add(o);
        for (Long l : longList)
        {
            List<Object[]> list1 = categoryMetadataFieldRepository.getMetadataField(l);
            list.addAll(list1);
        }
        o[0]="metadata field values";
        list.add(o);
        List<Object[]> list1 = categoryMetadataFieldValuesRepository.getValues(category_id);
        List<Object[]> list2 = productRepository.getBrands(category_id);
        list.addAll(list1);
        list.addAll(list2);

        return list;
    }

    public List<Object[]> viewACategory(Long category_id)
    {
        Optional<Category> category = categoryRepository.findById(category_id);
        List<Object[]> list = new ArrayList<>();
        if (category.isPresent())
        {
         list.add(categoryRepository.getNameOfCategory(category_id));
         Long cid = category_id;
         while (categoryRepository.getIdOfParent(cid)!=null)
         {
             list.add(categoryRepository.getNameOfCategory(categoryRepository.getIdOfParent(cid)));
             cid = categoryRepository.getIdOfParent(cid);
         }
            Collections.reverse(list);
         if (categoryRepository.checkIfLeaf(category_id)==0)
         {
             List<Long> longList = categoryMetadataFieldValuesRepository.getMetadataId(category_id);
             for (Long l : longList)
             {
                 List<Object[]> list1 = categoryMetadataFieldRepository.getMetadataField(l);
                 list.addAll(list1);
             }
             List<Object[]> list1 = categoryMetadataFieldValuesRepository.getValues(category_id);
             list.addAll(list1);

         }
         else
         {
             list.addAll(categoryRepository.getSubCategoryOfCategory(category_id));
         }
         return list;

        }
        else
        {
            throw new NotFoundException("category with this id is not present");
        }
    }

    @Override
    public void updateCategory(Category category, Long categoryId) {

        if (categoryRepository.findById(categoryId).isPresent()) {
            Category category1 = categoryRepository.findById(categoryId).get();
            category1.setName(category.getName());
            categoryRepository.save(category1);

        } else {
            throw new NotFoundException("This category ID is wrong as no entry is present for this ID");

        }
    }


    @Override
    public void addMetadataValues(CategoryMetadataFieldValues categoryMetadataFieldValues, Long categoryId, Long metadataId) {

        if (categoryRepository.findById(categoryId).isPresent()&&categoryRepository.checkIfLeaf(categoryId)==0) {
            if (categoryMetadataFieldRepository.findById(metadataId).isPresent()) {
                CategoryMetadataFieldValuesId categoryMetadataFieldValuesId = new CategoryMetadataFieldValuesId();
                categoryMetadataFieldValuesId.setCid(categoryRepository.findById(categoryId).get().getId());
                categoryMetadataFieldValuesId.setMid(categoryMetadataFieldRepository.findById(metadataId).get().getId());

                categoryMetadataFieldValues.setCategoryMetadataFieldValuesId(categoryMetadataFieldValuesId);
                categoryMetadataFieldValues.setCategory(categoryRepository.findById(categoryId).get());
                String[] valuesArray = categoryMetadataFieldValues.getFieldValues().split(",");
                Set<String> s = new HashSet<>(Arrays.asList(valuesArray));
                if (s.size()==valuesArray.length&&s.size()>=1&&valuesArray[0]!="")
                categoryMetadataFieldValues.setFieldValues(categoryMetadataFieldValues.getFieldValues());
                else
                    throw new NullException("values are not unique");
                categoryMetadataFieldValues.setCategoryMetadataField(categoryMetadataFieldRepository.findById(metadataId).get());
                categoryMetadataFieldValuesRepository.save(categoryMetadataFieldValues);
            } else {
                throw new NotFoundException("This metadata ID is wrong because no " +
                        "metadata is present for this ID");
            }
        } else {
            throw new NotFoundException("Category ID is wrong as no data is present for this ID");
        }


    }

    @Override
    public void updateMetadataValues(CategoryMetadataFieldValues categoryMetadataFieldValues, Long categoryId, Long metadataId) {

        if (categoryRepository.findById(categoryId).isPresent())
        {
            if (categoryMetadataFieldRepository.findById(metadataId).isPresent())
            {
                if (categoryMetadataFieldValuesRepository.getFieldValues(categoryId, metadataId) != null) {
                    CategoryMetadataFieldValues categoryMetadataFieldValues1 = categoryMetadataFieldValuesRepository.getFieldValues(categoryId, metadataId);
                    categoryMetadataFieldValues1.setFieldValues(categoryMetadataFieldValues.getFieldValues());
                    categoryMetadataFieldValuesRepository.save(categoryMetadataFieldValues1);
                } else {
                    throw new NotFoundException("This combination of category and metadata " +
                            "is wrong as no entry is present for this combination");
                }
            }
            else {
                throw new NotFoundException("This metadata ID is wrong");
            }
        }

        else {
            throw new NotFoundException("This Category Id is wrong");
        }



    }

    public List<ViewCategoriesDTO> viewAllCategoriesForSeller()
    {
         List<Object[]> list =  sellerRepository.getSubcategory();
         List<ViewCategoriesDTO> list1 = new ArrayList<>();
         for (Object[] objects:list)
         {
             ViewCategoriesDTO viewCategoriesDTO = new ViewCategoriesDTO();
             viewCategoriesDTO.setName(objects[0].toString());
             Long categoryId = categoryRepository.getIdOfParentCategory(objects[0].toString());
             Optional<Category> category = categoryRepository.findById(categoryId);
             Set<CategoryMetadataFieldValues> set = category.get().getCategoryMetadataFieldValues();
             List<String> fields = new ArrayList<>();
             for (CategoryMetadataFieldValues categoryMetadataFieldValues:set)
             {
                 fields.add(categoryMetadataFieldValues.getFieldValues());
                 viewCategoriesDTO.setValues(fields);
             }
             List<Long> longList = categoryMetadataFieldValuesRepository.getMetadataId(category.get().getId());
             List<String > fields1 = new ArrayList<>();
             for (Long l : longList)
             {
                 Optional<CategoryMetadataField> categoryMetadataField = categoryMetadataFieldRepository.findById(l);
                 fields1.add(categoryMetadataField.get().getName());
                 viewCategoriesDTO.setFieldName(fields1);
             }
             list1.add(viewCategoriesDTO);

         }
         return list1;
    }












    }


