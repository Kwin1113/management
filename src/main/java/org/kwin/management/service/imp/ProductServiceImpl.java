package org.kwin.management.service.imp;

import org.kwin.management.dao.ProductMapper;
import org.kwin.management.dto.CartDTO;
import org.kwin.management.entity.Product;
import org.kwin.management.enums.ResultEnum;
import org.kwin.management.exception.SysException;
import org.kwin.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> selectALl() {
        List<Product> products = productMapper.selectAll();
        return products;
    }

    @Override
    public Product selectOne(String productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        return product;
    }

    @Override
    public int add(Product product) {
        int result = productMapper.insert(product);
        return result;
    }

    @Override
    public int deleteById(String productId) {
        int result = productMapper.deleteByPrimaryKey(productId);
        return result;
    }

    @Override
    public int update(Product product) {
        int result = productMapper.updateByPrimaryKeySelective(product);
        return result;
    }

    @Override
    public void descStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Product product = productMapper.selectByPrimaryKey(cartDTO.getProductId());
            if (product == null) {
                throw new SysException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = product.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SysException(ResultEnum.STOCK_ERROR);
            }
            product.setProductStock(result);

            productMapper.updateByPrimaryKeySelective(product);
        }
    }

    @Override
    public void incrStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Product product = productMapper.selectByPrimaryKey(cartDTO.getProductId());
            if (product == null) {
                throw new SysException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = product.getProductStock() + cartDTO.getProductQuantity();
            product.setProductStock(result);

            productMapper.updateByPrimaryKeySelective(product);
        }
    }
}
