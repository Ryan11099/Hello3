package org.imooc;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.imooc.bean.Ad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test {

    public static void main(String[] args) throws Exception {
        Ad ad=new Ad();
        ad.setTitle("ssss");
        ad.setId(1l);
        String s="aaa";

        System.out.println(s);
        System.out.println( s.equals("aaa"));
    }

}
