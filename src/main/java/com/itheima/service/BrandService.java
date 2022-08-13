package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Wzq
 * @version : 1.0
 * @Project : brand-case
 * @Package : com.itheima.service
 * @ClassName : BrandService.java
 * @createTime : 2022/8/13 16:16
 * @Email : zqwang21@163.com
 * @Description :
 */
public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);
    void deleteByIds(@Param("ids") int[] ids);
    PageBean<Brand> selectByPage(int currentPage,int pageSize);
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

}
