package com.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.StudentService;
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;  
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        // TODO Auto-generated method stub 
	    	// 处理响应数据的格式和编码，通知浏览器数据的显示方式
	     response.setCharacterEncoding("utf-8");
	     response.setContentType("text/html;charset=utf-8");
	     PrintWriter pw = response.getWriter();  
         pw.println("doGet!"); 
         System.out.println ("doGet1");  
         String Name=request.getParameter("name");   
         StudentService ws = new StudentService();
	     List list =  ws.GetStudentInfo(Name); 
	     request.setAttribute("list",list);  
	     request.getRequestDispatcher("/student.jsp").forward(request, response);  
	    }  

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        // TODO Auto-generated method stub  
	    }  


}
