package com.hcl.pmoautomation.AddAction.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.pmoautomation.AddAction.dao.AddActiondao;
import com.hcl.pmoautomation.AddAction.vo.ActionAdd;


	@Controller      
	@RequestMapping("pmoAutomation/ATStatus")
	public class StatusController {
		@Autowired
	 JdbcTemplate jdbcTemplate;
		
	  	    
	    @RequestMapping(value = "/oopen.php", method = RequestMethod.GET)
	    public String open(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
	    		AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.StatusOPEN(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/oopenmedium.php", method = RequestMethod.GET)
	    public String openmedium(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
	    		AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.StatusOPENmedium(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/oopenlow.php", method = RequestMethod.GET)
	    public String openlow(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
	    		AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.StatusOPENlow(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/cclose.php", method = RequestMethod.GET)
	    public String close(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.StatusClose(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/cclosemedium.php", method = RequestMethod.GET)
	    public String closemedium(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.StatusClosemedium(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/ccloselow.php", method = RequestMethod.GET)
	    public String closelow(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.StatusCloselow(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/overdue.php", method = RequestMethod.GET)
	    public String overdue(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusoverdue(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/overduemedium.php", method = RequestMethod.GET)
	    public String overduemedium(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusoverduemedium(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/overduelow.php", method = RequestMethod.GET)
	    public String overduelow(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusoverduelow(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/ontrack.php", method = RequestMethod.GET)
	    public String ontrack(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusontrack(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/ontrackmedium.php", method = RequestMethod.GET)
	    public String ontrackmedium(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusontrackmedium(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/ontracklow.php", method = RequestMethod.GET)
	    public String ontracklow(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusontracklow(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	
	    }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/assigned.php", method = RequestMethod.GET)
	    public String assigned(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusassigned(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	 }
			 catch(Exception e){
				 return "forward:../../pmoAutomation/Login/errorPage.php";
			 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/assignedmedium.php", method = RequestMethod.GET)
	    public String assignedmedium(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusassignedmedium(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	 }
			 catch(Exception e){
				 return "forward:../../pmoAutomation/Login/errorPage.php";
			 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/assignedlow.php", method = RequestMethod.GET)
	    public String assignedlow(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusassignedlow(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	 }
			 catch(Exception e){
				 return "forward:../../pmoAutomation/Login/errorPage.php";
			 }
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/unassigned.php", method = RequestMethod.GET)
	    public String unassigned(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusunassigned(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	 }
			 catch(Exception e){
				 return "forward:../../pmoAutomation/Login/errorPage.php";
			 }
	    	
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/unassignedmedium.php", method = RequestMethod.GET)
	    public String unassignedmedium(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusunassignedmedium(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	 }
			 catch(Exception e){
				 return "forward:../../pmoAutomation/Login/errorPage.php";
			 }
	    	
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
	    @RequestMapping(value = "/unassignedlow.php", method = RequestMethod.GET)
	    public String unassignedlow(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
	    	try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid= aad.Statusunassignedlow(sapid, jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			boolean resultflag=actionEdid.isEmpty();
			if (resultflag) {
				request.setAttribute("clickrecordsfound", "No Records Found");

			} else {
				request.setAttribute("clickrecordsfound", "Records found");
			}
	    	 }
			 catch(Exception e){
				 return "forward:../../pmoAutomation/Login/errorPage.php";
			 }
	    	
	    	
	    		return "ActionTrack/StatusViewPage";

	    	}
}
