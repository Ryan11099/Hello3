package org.imooc.service;


import org.imooc.bean.Business;

public interface BusinessService {
    void save(Business business);

    Business selectBusinessById(Integer id);

    void updateBusiness(Business business);

    void deleteBusinessById(String id);
}
