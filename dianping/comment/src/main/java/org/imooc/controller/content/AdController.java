package org.imooc.controller.content;

import ch.qos.logback.core.util.TimeUtil;
import org.apache.ibatis.annotations.Param;
import org.imooc.bean.Ad;
import org.imooc.dao.AdDao;
import org.imooc.service.AdService;
import org.imooc.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdDao adDao;
    @Autowired
    private AdService adService;

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    HttpServletRequest autowiredRequest;
    @RequestMapping("addInit")
    public String addAd() throws InterruptedException {
        System.out.println(autowiredRequest.hashCode()+"=========save方法的request哈希值");
        System.out.println(autowiredRequest.toString()+"=========save方法的request地址值");
        System.out.println("把key设置进request域中");
        autowiredRequest.setAttribute("key","11111");
        System.out.println("key设置完成，线程睡眠10s");
        Thread.sleep(10000);
        System.out.println("睡10s以后的值"+autowiredRequest.getAttribute("key"));
        return null;
    }
    @RequestMapping(value = "modifyInit")
    public String editAd() {
        System.out.println("尝试从request域中获取key的值"+autowiredRequest.getAttribute("key"));
        System.out.println("把5555设置给key");
        autowiredRequest.setAttribute("key","5555");
        System.out.println(autowiredRequest.hashCode()+"==========eidt方法的request哈希值");
        System.out.println(autowiredRequest.toString()+"===========edit方法的request地址值");
        return null;
    }
    @RequestMapping("search")
    public  String serachAdByTitle() {
        System.out.println(autowiredRequest.hashCode()+"==========search方法的request哈希值");
        System.out.println(autowiredRequest.toString()+"==========search方法的request地址值");
        return null;

    }


//    @RequestMapping("addInit")
//    public String addAd(HttpServletRequest request) throws InterruptedException {
//        System.out.println("把key设置进request域中");
//        request.setAttribute("key","11111");
//        System.out.println("key设置完成，线程睡眠10s");
//        Thread.sleep(10000);
//        System.out.println("睡10s以后的值"+request.getAttribute("key"));
//        logger.info(request.hashCode()+"=========save方法的request哈希值");
//        logger.info(request.toString()+"=========save方法的request地址值");
//        return null;
//    }
//    @RequestMapping(value = "modifyInit")
//    public String editAd(HttpServletRequest request) {
//        logger.info(request.hashCode()+"==========eidt方法的request哈希值");
//        logger.info(request.toString()+"===========edit方法的request地址值");
//        return null;
//    }
//    @RequestMapping("search")
//    public String serachAdByTitle(HttpServletRequest request) {
//        logger.info(request.hashCode()+"==========search方法的request哈希值");
//        logger.info(request.toString()+"==========search方法的request地址值");
//        return null;
//    }



//    @RequestMapping("search")
//    public String serachAdByTitle(HttpServletRequest request) {
//        String title = request.getParameter("title");
//        List<Ad> list = adDao.querylistByTitle("%" + title + "%");
//        request.setAttribute("list", list);
//        return "/content/adList";
//    }
    private FileUtil fileUtil=new FileUtil();
    @RequestMapping
    public String init(HttpServletRequest request) {
        List<Ad> list = adDao.querylist();
        request.setAttribute("list", list);
        return "/content/adList";
    }

//    @RequestMapping("addInit")
//    public String addAd() {
//        return "/content/adAdd";
//    }

//    @RequestMapping("add")
//    public String SaveAd(HttpServletRequest request, @RequestParam MultipartFile imgFile) {
//        Ad ad = new Ad();
//        ad.setTitle(request.getParameter("title"));
//        ad.setImgFileName(request.getParameter("imgFileName"));
//        ad.setLink(request.getParameter("link"));
//        ad.setWeight(Long.valueOf(request.getParameter("weight")));
//        adDao.saveAd(ad);
//        return "redirect:/ad";
//    }


//    @RequestMapping(value = "modifyInit")
//    public String editAd(HttpServletRequest request) {
//        String id = request.getParameter("id");
//        Ad ad = adDao.selectAdById(id);
//        request.setAttribute("modifyObj", ad);
//
//        return "/content/adModify";
//    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String updateAd(HttpServletRequest request,@Param("title") String title, @Param("imgFile") MultipartFile imgFile
    ,@Param("link") String link,@Param("weight") Long weight,@Param("id") Long id) {

        Ad ad = adDao.selectAdById(id.toString());
        ad.setTitle(request.getParameter("title"));
        ad.setImgFileName(request.getParameter("imgFileName"));
        ad.setLink(request.getParameter("link"));
        ad.setWeight(Long.valueOf(request.getParameter("weight")));
        ad.setId(Long.valueOf(request.getParameter("id")));
        adDao.updateAdById(ad);

        return "redirect:/ad";
    }

    @RequestMapping("remove")
    public String deletedById(HttpServletRequest request){
        String id = request.getParameter("id");
        adDao.deletedAdById(Long.valueOf(id));
        return "redirect:/ad";
    }

    @RequestMapping("downExcel")
    public String downExcel() throws IOException {
        adService.downExcel();
        return "redirect:/ad";
   }


}
