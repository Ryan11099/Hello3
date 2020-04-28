package org.imooc.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.imooc.bean.Ad;
import org.imooc.dao.AdDao;
import org.imooc.dto.AdDto;
import org.imooc.service.AdService;
import org.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdDao adDao;

    @Override
    public void downExcel() throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        HttpServletRequest request = requestAttributes.getRequest();

        List<Ad> adList = this.adDao.querylist();
        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
        SXSSFWorkbook workBook = new SXSSFWorkbook(1000);//内存中最多存放一千条数据，防止内存溢出
        HSSFSheet sheet = workbook.createSheet("广告列表"); //创建一个工作表
        HSSFRow hssfRow = sheet.createRow(0); //添加表头行
        HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格格式居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("id");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("title");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("imgFileName");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("link");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("weight");
        headCell.setCellStyle(cellStyle);

// 添加数据内容
        for (int i = 0; i < adList.size(); i++) {
            hssfRow = sheet.createRow((int) i + 1);
            Ad ad = adList.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(ad.getId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(ad.getTitle());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(ad.getImgFileName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(3);
            cell.setCellValue(ad.getLink());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(ad.getWeight());
            cell.setCellStyle(cellStyle);
        }

        try {
            String name = "广告数据";
            String codedFileName = new String(name.getBytes("gbk"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            // 响应类型,编码
            response.setContentType("application/octet-stream;charset=UTF-8");
            // 形成输出流
            OutputStream osOut = response.getOutputStream();
            // 将指定的字节写入此输出流
            workbook.write(osOut);
            // 刷新此输出流并强制将所有缓冲的输出字节被写出
            osOut.flush();
            // 关闭流
            osOut.close();
            workBook.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
