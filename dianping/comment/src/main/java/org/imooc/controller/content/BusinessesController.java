package org.imooc.controller.content;

import org.apache.ibatis.annotations.Param;
import org.imooc.bean.Business;
import org.imooc.bean.Dic;
import org.imooc.constant.DicTypeConst;
import org.imooc.constant.PageCodeEnum;
import org.imooc.dao.BusinessDao;
import org.imooc.dto.BusinessDto;
import org.imooc.service.BusinessService;
import org.imooc.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/businesses")
public class BusinessesController {
    @Autowired
    private BusinessDao businessDao;
    @Autowired
    private BusinessService businessService;
    @RequestMapping
    public String init(HttpServletRequest request) {
        List<Business> list=businessDao.selectBusiness();
        request.setAttribute("list",list);

        return "/content/businessList";
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String search(HttpServletRequest request) {
        String title = request.getParameter("title");
        System.out.println("+++++++++++++++++++++++++++"+title);
        List<Business> list= businessDao.selectBusinessByCondition("%" + title + "%");
        request.setAttribute("list",list);
        return "/content/businessList";
    }

    @RequestMapping(value = "addPage")
    public String addBusiness(HttpServletRequest request){
        List<Dic> cityList=businessDao.selectCity();
        List<Dic> categoryList=businessDao.selectCategory();
        request.setAttribute("cityList",cityList);
        request.setAttribute("categoryList",categoryList);
        return "/content/businessAdd";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String saveBusiness(HttpServletRequest request){
        Business business=new Business();
        business.setCategory(request.getParameter("category"));
        business.setTitle(request.getParameter("title"));
        business.setSubtitle(request.getParameter("subtitle"));
        business.setCity(request.getParameter("city"));
        business.setImgFileName(request.getParameter("imgFile"));
        business.setPrice(Double.valueOf(request.getParameter("price")));
        business.setDistance(Integer.valueOf(request.getParameter("distance")));
        business.setDesc(request.getParameter("desc"));
        businessService.save(business);
        return "redirect:/businesses";
    }

    @RequestMapping(value = "{id}")
    public String toUpdateBusiness(HttpServletRequest request,@PathVariable("id")String id){

        List<Dic> cityList=businessDao.selectCity();
        List<Dic> categoryList=businessDao.selectCategory();
        request.setAttribute("cityList",cityList);
        request.setAttribute("categoryList",categoryList);
        Business business=businessService.selectBusinessById(Integer.valueOf(id));
        request.setAttribute("modifyObj",business);
        return "/content/businessModify";
    }

    @RequestMapping(value = "modify/{id}")
    public String updateBusiness(HttpServletRequest request,@PathVariable("id")String id){

        Business business=businessService.selectBusinessById(Integer.valueOf(id));
        business.setCategory(request.getParameter("category"));
        business.setTitle(request.getParameter("title"));
        business.setSubtitle(request.getParameter("subtitle"));
        business.setCity(request.getParameter("city"));
        business.setImgFileName(request.getParameter("imgFile"));
        business.setPrice(Double.valueOf(request.getParameter("price")));
        business.setDistance(Integer.valueOf(request.getParameter("distance")));
        business.setDesc(request.getParameter("desc"));
        business.setId(Long.valueOf(id));
        businessService.updateBusiness(business);
        return "redirect:/businesses";
    }

    @RequestMapping(value = "remove/{id}")
    public String deleteBusiness(@PathVariable("id")String id){

        businessService.deleteBusinessById(id);
        return "redirect:/businesses";
    }

}