package org.kwin.management.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kwin.management.common.common.Const;
import org.kwin.management.common.enums.ResultEnum;
import org.kwin.management.common.exception.SysException;
import org.kwin.management.common.utils.KeyUtil;
import org.kwin.management.dao.mapper.ProductMapper;
import org.kwin.management.dto.CartDTO;
import org.kwin.management.entity.Product;
import org.kwin.management.form.ProductAddForm;
import org.kwin.management.form.ProductForm;
import org.kwin.management.service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> selectALl() {
        List<Product> products = productMapper.selectAll(Integer.parseInt(Const.CURRENT_USER));
        return products;
    }

    @Override
    public Product selectOne(String productId) {
        Product product = productMapper.selectByPrimaryKey(productId, Integer.parseInt(Const.CURRENT_USER));
        return product;
    }

    @Override
    public Product selectByTypeAndSizeAndDirection(ProductAddForm productAddForm) {
        String productType = productAddForm.getProductType();
        String productSize = productAddForm.getProductSize();
        Integer productDirection = productAddForm.getProductDirection();
        Product product = productMapper.selectByTypeAndSizeAndDirection(Integer.parseInt(Const.CURRENT_USER),
                productType, productSize, productDirection);
        return product;
    }

    @Override
    public void add(ProductForm productForm) {
        Product product = new Product();
        BeanUtils.copyProperties(productForm, product);
        product.setProductId(KeyUtil.getUniqueKey());
        try {
            productMapper.insert(product, Integer.parseInt(Const.CURRENT_USER));
        } catch (DuplicateKeyException e) {
            throw new SysException(ResultEnum.PRODUCT_EXIST);
        }
    }

    @Override
    public int deleteById(String productId) {
        int result = productMapper.deleteByPrimaryKey(productId, Integer.parseInt(Const.CURRENT_USER));
        return result;
    }

    @Override
    public void update(Product product) {
        try {
            productMapper.updateByPrimaryKeySelective(product, Integer.parseInt(Const.CURRENT_USER));
        } catch (DuplicateKeyException e) {
            throw new SysException(ResultEnum.PRODUCT_EXIST);
        }

    }

    @Override
    public void descStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Product product = productMapper.selectByPrimaryKey(cartDTO.getProductId(), Integer.parseInt(Const.CURRENT_USER));
            if (product == null) {
                throw new SysException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = product.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SysException(ResultEnum.STOCK_ERROR);
            }
            product.setProductStock(result);

            productMapper.updateByPrimaryKeySelective(product, Integer.parseInt(Const.CURRENT_USER));
        }
    }

    @Override
    public void incrStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Product product = productMapper.selectByPrimaryKey(cartDTO.getProductId(), Integer.parseInt(Const.CURRENT_USER));
            if (product == null) {
                throw new SysException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = product.getProductStock() + cartDTO.getProductQuantity();
            product.setProductStock(result);

            productMapper.updateByPrimaryKeySelective(product, Integer.parseInt(Const.CURRENT_USER));
        }
    }
}
