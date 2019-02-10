package org.kwin.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.kwin.management.entity.Product;
import org.kwin.management.form.ProductForm;
import org.kwin.management.service.ProductService;
import org.kwin.management.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> model) {
        List<Product> productList = productService.selectALl();
        model.put("productList", productList);
        return new ModelAndView("product/list", model);
    }

    @ResponseBody
    @GetMapping("/selectOne/{productId}")
    public ModelMap selectOne(@PathVariable String productId) {
        ModelMap model = new ModelMap();
        Product product = productService.selectOne(productId);
        model.addAttribute("product", product);
        return model;
    }

    @PostMapping("/update")
    public String modify(ProductForm productForm) {
        Product product = productService.selectOne(productForm.getProductId());
        BeanUtils.copyProperties(productForm, product);
        productService.update(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{productId}")
    public String delete(@PathVariable String productId) {
        productService.deleteById(productId);
        return "redirect:/product/list";
    }

    @PostMapping("/add")
    public String add(@Valid ProductForm productForm, BindingResult result) {
        Product product = new Product();
        BeanUtils.copyProperties(productForm, product);
        product.setProductId(KeyUtil.getUniqueKey());
        productService.add(product);
        return "redirect:/product/list";
    }
}
