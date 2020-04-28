package org.imooc.service.impl;

import org.imooc.bean.Business;
import org.imooc.bean.Page;
import org.imooc.constant.CategoryConst;
import org.imooc.dao.BusinessDao;
import org.imooc.dto.BusinessDto;
import org.imooc.dto.BusinessListDto;
import org.imooc.service.BusinessService;
import org.imooc.util.CommonUtil;
import org.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessDao businessDao;
    @Override
    public void save(Business business) {
        businessDao.saveBusiness(business);
    }

    @Override
    public Business selectBusinessById(Integer id) {
        Business business=businessDao.selectBusinessById(id);
        return business;
    }

    @Override
    public void updateBusiness(Business business) {
        businessDao.updateBusiness(business);
    }

    @Override
    public void deleteBusinessById(String id) {
        businessDao.deleteBusinessById(id);
    }
}
