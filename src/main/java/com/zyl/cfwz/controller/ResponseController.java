package com.zyl.cfwz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ResponseController {

    @GetMapping("/req")
    public String res(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setAttribute("key1","LiLotus");
            request.getRequestDispatcher("/reqjump").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @GetMapping("/reqjump")
    public String reqjump(HttpServletRequest request){
        String key1 = (String) request.getAttribute("key1");
        return "login";
    }

    @GetMapping("/res")
    public String res(HttpServletResponse response){
        try {
            response.sendRedirect("/jump");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @GetMapping("/jump")
    public String jump(){
        return "login";
    }

    @GetMapping("/download")
    public String download(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String filename = request.getParameter("filename");
        //第一步，得到文件路径

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);

        //PrintWriter printWriter= response.getWriter();
        ServletOutputStream servletOutputStream= null;
        try {
            servletOutputStream = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String filepath="D:\\fileupload";
        File file = new File(filepath+"//"+filename);
        if(file.exists()){
            InputStream inputStream=new BufferedInputStream(new FileInputStream(file));
            int len=0;
            byte[] buffer = new byte[1024];
            while ((len=inputStream.read(buffer))!=-1){
                // printWriter.print(new String(buffer,0,len));
                servletOutputStream.write(buffer,0,len);
            }
            inputStream.close();
            servletOutputStream.close();

        }

        return "login";
    }
}
