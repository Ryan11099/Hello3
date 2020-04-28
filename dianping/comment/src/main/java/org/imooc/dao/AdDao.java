package org.imooc.dao;

import org.imooc.bean.Ad;

import java.util.List;

public interface AdDao {
    List<Ad> querylist();

    List<Ad> querylistByTitle(String title);

    void saveAd(Ad ad);

    Ad selectAdById(String id);

    void updateAdById(Ad ad);

    void deletedAdById(Long valueOf);
}