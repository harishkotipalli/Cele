package com.hcl.pmoautomation.AddAction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.hcl.pmoautomation.AddAction.dao.DataDao;

import com.hcl.pmoautomation.AddAction.vo.ActionAdd;
import com.hcl.pmoautomation.AddAction.vo.autocompleteVo;
import com.hcl.pmoautomation.rnc.dao.NewOdcAccessImpl;

@Controller      
@RequestMapping("pmoAutomation/ActionTrack")
public class autoController {
	@Autowired
	 JdbcTemplate jdbcTemplate;
	
	    	
	    	
	    	 @RequestMapping(value = "/autocompleteboxlatest.php", method = RequestMethod.GET)
	 	    public void autocompleteboxlatest(HttpServletRequest request,
	 	    		HttpServletResponse response) throws ServletException, IOException 
	 	    {

	    			response.setContentType("application/json");
	    	        try {
	    	                String term = request.getParameter("term");
	    	                System.out.println("Data from ajax call " + term);
	    	System.out.println("1111111111");
	    	                DataDao dataDao = new DataDao();
	    	         //       String s[]={"Sam","Tom","Jerry"};
	    	            //   List<autocompleteVo> list = dataDao.getFrameWork(term,jdbcTemplate);
	    	               List<String> list = dataDao.getFrameWork(term);
	    	          //      ArrayList<String> list = new ArrayList<String>(Arrays.asList(s));
	    	                
	    	                System.out.println("hiiiiiiii"+list);
	    	                String searchList = new Gson().toJson(list);
	    	                System.out.println(searchList);
	    	                response.getWriter().write(searchList);
	    	        } catch (Exception e) {
	    	                System.err.println(e.getMessage());
	    	        }
	 
	    	
	    		
	    		  
	    			

	    	
	    }
	    	 
	    	 @RequestMapping(value = "/autodbjspcom.php", method = RequestMethod.GET)
	 	    public String autocompletedbjspcom(HttpServletRequest request,
	 	    		HttpServletResponse response) throws ServletException, IOException 
	 	    {
	 			return "ActionTrack/auto";

	 	    	
	 	    	
	 	    	}
	    	 
	    	 
	    		@RequestMapping(value = "/getemployeeSapId.php", method = RequestMethod.GET)
	    		public void getemployeeSapId(HttpServletRequest request,
	    				HttpServletResponse response) throws Exception,ServletException, IOException {

	    		 DataDao datadao= new DataDao();
	    	    			response.getWriter().print(
	    					datadao.getemployeeSapId(jdbcTemplate,
	    							(String) request.getParameter("on")));
	    			
	    		}
	    		
	    		
	    		
	    		
	    		@RequestMapping(value = "/getemployeeMailId.php", method = RequestMethod.GET)
	    		public void getemployeeMailId(HttpServletRequest request,
	    				HttpServletResponse response) throws Exception,ServletException, IOException {

	    			DataDao datadao= new DataDao();
	    	    			response.getWriter().print(
	    					datadao.getemployeeMailId(jdbcTemplate,
	    							(String) request.getParameter("on")));
	    			
	    		}
	  
	    		
	    		@RequestMapping(value = "/gettotalactions.php", method = RequestMethod.GET)
	    		public void getactions(HttpServletRequest request,
	    				HttpServletResponse response) throws Exception,ServletException, IOException {

	    		 DataDao datadao= new DataDao();
	    		 String s=request.getParameter("on");
	    	String l=datadao.getemployeetotalactions(jdbcTemplate, s);
	    	System.out.println("heloooooooooo "+l+"hiiiii "+s);
	    			response.getWriter().print(
	    					datadao.getemployeetotalactions(jdbcTemplate,
	    							(String) request.getParameter("on")));
	    			
	    		}
	    		
	    		@RequestMapping(value = "/gettotalhours.php", method = RequestMethod.GET)
	    		public void gethours(HttpServletRequest request,
	    				HttpServletResponse response) throws Exception,ServletException, IOException {

	    		 DataDao datadao= new DataDao();
	    			response.getWriter().print(
	    					datadao.getemployeetotalhours(jdbcTemplate,
	    							(String) request.getParameter("on")));
	    			
	    		}
	
	
}
