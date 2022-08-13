package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Wzq
 * @version : 1.0
 * @Project : brand-case
 * @Package : com.itheima.service.impl
 * @ClassName : BrandServiceImpl.java
 * @createTime : 2022/8/13 16:18
 * @Email : zqwang21@163.com
 * @Description :
 */
public class BrandServiceImpl implements BrandService {
    //1.创建SqlSessionFactory工厂对象
    SqlSessionFactory sqlSessionFactory =  SqlSessionFactoryUtils.getSqlSessionFactory();



    @Override
    public List<Brand> selectAll() {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage -1)*pageSize;
        int size = pageSize;
        List<Brand> rows = mapper.selectByPage(begin, size);
        int totalCount = mapper.selectTotalCount();

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize,Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage -1)*pageSize;
        int size = pageSize;
        String brandName = brand.getBrandName();
        if(brandName!=null&&brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if(companyName!=null&&companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        List<Brand> rows = mapper.selectByPageAndCondition(begin,size,brand);
        int totalCount = mapper.selectTotalCountByCondition(brand);

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }
}
