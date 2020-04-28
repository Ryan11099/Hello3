package org.imooc.dao;

import org.imooc.bean.Business;
import org.imooc.bean.Dic;

import java.util.List;

public interface BusinessDao {
    List<Business> selectBusiness();

    List<Business> selectBusinessByCondition(String s);

    List<Dic> selectCity();

    List<Dic> selectCategory();

    void saveBusiness(Business business);

    Business selectBusinessById(Integer id);

    void updateBusiness(Business business);

    void deleteBusinessById(String id);
}
