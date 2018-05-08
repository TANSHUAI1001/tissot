package cn.shuai.tissot.service;

import cn.shuai.tissot.dto.Exposure;
import cn.shuai.tissot.dto.ProductExecution;
import cn.shuai.tissot.entity.Product;
import cn.shuai.tissot.exception.ProductCloseExcption;
import cn.shuai.tissot.exception.ProductException;
import cn.shuai.tissot.exception.RepeatKillException;

import java.util.List;

/**
 * Created by shuai on 2016/12/31.
 */
public interface ProductService {
    List<Product> getProductList();

    Product getProductById(long productId);

    Exposure exportProductUrl(long productId);

    ProductExecution executeProduct(long productId, long userPhone, String md5)
            throws ProductException,RepeatKillException,ProductCloseExcption;
}
