package com.hcl.pmoautomation.AddAction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.pmoautomation.AddAction.dao.AddActiondao;
import com.hcl.pmoautomation.AddAction.dao.actionmaildao;
import com.hcl.pmoautomation.AddAction.dao.assignedStatusByMedao;

import com.hcl.pmoautomation.AddAction.vo.ActionAdd;
import com.hcl.pmoautomation.AddAction.vo.statusVO;
import com.hcl.pmoautomation.AddAction.vo.templatevo;
import com.hcl.pmoautomation.email.App;
import com.hcl.pmoautomation.ot.dao.mailDao;
import com.hcl.pmoautomation.sk.vo.skillSearch;


@Controller  
@SessionAttributes
@RequestMapping("pmoAutomation/ActionTrack")
	public class AddAction 
	{
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("errorMessage", ex.getMessage());
	    modelAndView.addObject("errorDetails", ExceptionUtils.getStackTrace(ex));
	    modelAndView.setViewName("Login/Error");

	    return modelAndView;
	}
	@Autowired
 JdbcTemplate jdbcTemplate;
	
    private AddActiondao addactiondao;
    java.sql.PreparedStatement ps;
    Connection conn;
  
    
    @RequestMapping(value = "/view.php", method = RequestMethod.GET)
    public String view(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException 
    {

    		return "ActionTrack/ViewPage";

    	}
   
    
    @RequestMapping(value = "/viewsearch.php", method = RequestMethod.POST)
public String viewSearch(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	int sapid=(int) request.getSession().getAttribute("managerId");
	//String sap=Integer.toString(sapid);
	
	

	 	String 	raid=request.getParameter("ddraid");
		
		String 	cat=request.getParameter("cate");
		
		String 	status=request.getParameter("ddsta");
		String 	md=request.getParameter("md");
	
		
		Date date = null;
		int actionType = 0;
		String actionVal = null;
		try 
	    {  
	    //  String datestr="06/27/2007";
			SimpleDateFormat formatter; 
	      
	      formatter = new SimpleDateFormat("MM/dd/yyyy");
	      if(!(md=="")){
	      date = (Date)formatter.parse(md);
	    
	      }
	      else
	      {
	    	  md=null;
	    	
	      }
	     
	    } 
	    catch (Exception e)
	    {
	    	
	    	e.printStackTrace();
	    }
		
		String adhoc = null;
		adhoc=request.getParameter("adhoc" );
		if (adhoc != null) actionVal = adhoc;
		
		String meeting =null;
		meeting=request.getParameter("meeting" );
		if (meeting != null && adhoc != null) 
			actionVal = actionVal + "','" + meeting;
		if (meeting != null && adhoc == null)
			actionVal = meeting;
		
		
		if (adhoc != null)
		{
			actionType = 1;			
		}
		if (meeting != null)
			actionType += 1;
		
	
		
		
		AddActiondao aad=new AddActiondao();
		//List<ActionAdd> action=aad.list(raid, cat, status, date, adhoc, meeting, jdbcTemplate);
		List<ActionAdd> action=aad.list(raid, cat, status, date, actionType, actionVal, sapid,jdbcTemplate);
		
		
		request.setAttribute("action",action);
		boolean resultflag=action.isEmpty();
		if (resultflag) {
			request.setAttribute("clickrecordsfound", "No Records Found");

		} else {
			request.setAttribute("clickrecordsfound", "Records found");
		}
	return "ActionTrack/ViewPage";

}

@RequestMapping(value = "/edit.php", method = RequestMethod.POST)
public String edit(Model model,HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException ,Exception
{
	try{
	String 	radio=request.getParameter("rd");
	
	String 	raid[]=request.getParameterValues("1");
	
	String 	cat[]=request.getParameterValues("2");
	
	String 	status[]=request.getParameterValues("3");
	String 	md[]=request.getParameterValues("4");
	String 	type[]=request.getParameterValues("5");
	String[] 	sl=request.getParameterValues("6");
	
	String 	loop=request.getParameter("loop");
	int lp=Integer.parseInt(loop);
	for(int z=0;z<=lp-1;z++){
		
		if(radio.equalsIgnoreCase(sl[z])){
			
			System.out.println("serialaddaction----------"+sl[z]);
			AddActiondao aad=new AddActiondao();
			
			List<ActionAdd> actionEdid=aad.edit(raid[z], cat[z], status[z], type[z], sl[z], jdbcTemplate);
			
			request.setAttribute("actionEdid",actionEdid);
			
			
		}
	}
	 }
	 catch(Exception e){
		 return "forward:../../pmoAutomation/Login/errorPage.php";
	 }
		return "ActionTrack/edit";

	}





@RequestMapping(value = "/update.php", method = {RequestMethod.GET,RequestMethod.POST })
public String viewupdate(Model model,HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException,Exception 
{

	try 
	{
		String loginname=(String) request.getSession().getAttribute("LOGINNAME");
		System.out.println("loginname in update  "+loginname);
	String 	desc=request.getParameter("desc1");
	String 	sta=request.getParameter("ddsta1");
	
	String 	on=request.getParameter("on1");
	
	String 	os=request.getParameter("os1");
	int i=Integer.parseInt(os);
	
	String 	om=request.getParameter("om1");
		
	
	String 	rem=request.getParameter("rem1");
	
	String 	su=request.getParameter("su1");
	
	String sl=request.getParameter("sl");
	int slno=Integer.parseInt(sl);
	System.out.println("actionserialno---------"+slno);
	
	
		String val1=request.getParameter("rd1");
		String val2=request.getParameter("ac1");
	
		
		String RecentUpd=request.getParameter("recentUpd");
	
		
			String addRem = RecentUpd+"\n"+new java.util.Date() + " : " + rem +" updated by "+loginname+"\n";
			
			String updQuery = "UPDATE mydb.gat SET  description=?,status=?,ownername=? ,ownersap=? ,ownermail=?,Re_baselinedate=? ,ActualClosureDate=?,Remarks=?,Status_Update=?,RecentUpdate=? where sl=?";
			this.jdbcTemplate.update(updQuery,  new Object[] {desc,sta,on,i,om,convert2Date(val1),convert2Date(val2),rem,su,addRem,slno});
	System.out.println("updateactintrack------------"+updQuery);
			if(sta.equalsIgnoreCase("close")){
				String hclmailid=(String) request.getSession().getAttribute("hclmail");
				String adminMail="gokulkaarthik.d@hcl.com";
				String[] mails = {adminMail,hclmailid};
				System.out.println("when closed trigged with "+hclmailid);
				App mailtrigger = new App();
				
				String[] strs = { os,om,rem,su };
				List<String> actionListtemp = new ArrayList<String>(Arrays.asList(strs));


			System.out.println("closed dataaa------"+actionListtemp);
			for(int j=0;j<=mails.length-1;j++){
				mailtrigger.Mailtrigger("pmoappadmin_ou@hcl.com",mails[j],  "Action closed for the below resource", "harish", "/ActionStatusupdatetemp.jsp", actionListtemp, null);
			}	/*int sapid=(int) request.getSession().getAttribute("managerId");
				if(sapid==12345678){
					String updQuery1 = "UPDATE mydb.gat SET gat.status=? where sl=?";
					this.jdbcTemplate.update(updQuery1,  new Object[] {sta,slno});
			System.out.println("updateactintrack------------"+updQuery);
				}*/
				
				
			}

	
	} catch (Exception   e) {
		
		return "forward:../../pmoAutomation/Login/errorPage.php";
	}
	
	
	return "forward:../../pmoAutomation/ActionTrack/ActionStatus.php";

	}

	Date convert2Date(String date)
	{

		SimpleDateFormat simDate = null;
		java.util.Date dateobj1 = null;
		java.sql.Date newDate = null;

		try 
		{
			
			if (date.indexOf("/")>0)
			{
				simDate=new SimpleDateFormat("MM/dd/yyyy");
				dateobj1 = simDate.parse(date);		
				newDate = new java.sql.Date(dateobj1.getTime());
			
			}
			else if(date.indexOf("-")>0)
			{
				simDate = new SimpleDateFormat("yyyy-MM-dd");
				dateobj1 = simDate.parse(date);		
				newDate = new java.sql.Date(dateobj1.getTime());
		
				
			}
			else{
				date=null;
			}
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
		return newDate;
	}
	
	@RequestMapping(value = "/add.php", method = RequestMethod.GET)
    public String add(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException 
    {

    		return "ActionTrack/AddAction";

    	}
	
	
	
	@RequestMapping(value = "/addaction.php", method = RequestMethod.POST)
	public String addaction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		String 	cat=null;
		String 	raid=null;
		String 	om=null;
		String 	sta=null;
		String 	sdesc=null;
		String Bussinesscriticality=null;
		String loginname=(String) request.getSession().getAttribute("LOGINNAME");
		try 
		{
			int loginsap=(int) request.getSession().getAttribute("managerId");
			String type=null;
			String meet = null;
			String tcd=null;
			String rd=null;
			String ac=null;
			
			
			 type	=request.getParameter("chkPassPort");
		
	  	if(type.equals("Adhoc")){
				 meet	=request.getParameter("meet");
				
			}
	  	if(type.equals("Meeting")){
			 meet	=request.getParameter("meet1");
		
		}
			
				cat=request.getParameter("cate");
		
			 	raid=request.getParameter("ddraid");
		
				sdesc=request.getParameter("sdesc");
		
			String 	desc=request.getParameter("desc");
			 Bussinesscriticality=request.getParameter("bussinesscric");
			 	sta=request.getParameter("ddsta");
			 	
			String 	tc=request.getParameter("tc");
			String 	on=request.getParameter("on");
			
			String 	os=request.getParameter("os");
		
			int i=Integer.parseInt(os);
			
			om=request.getParameter("om");
			String AH=request.getParameter("AssignHours");
			int Assignhours=Integer.parseInt(AH);
			String 	md=request.getParameter("date_ex");
	    	tcd=request.getParameter("date_ex1");
			
		 	rd=request.getParameter("rd");
		 
		 	ac=request.getParameter("ac");
			String 	rem=request.getParameter("remark");
			
			String 	su=request.getParameter("su");
			
			String addRem = new java.util.Date() + " : " + rem+" updated by "+loginname+"\n";
				String addQuery = "insert into mydb.gat (meeting_adhoc,category ,raid ,Shortdesc,description,Business_Criticality,status,tracks_clustures,ownername ,ownersap ,ownermail,meetingdate,targetclosuredate,Re_baselinedate ,ActualClosureDate,Remarks,Status_Update,type,RecentUpdate,Assigned_sapid,Assigned_hrs) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				this.jdbcTemplate.update(addQuery,  new Object[] {meet,cat,raid,sdesc,desc,Bussinesscriticality,sta,tc,on,i,om,convert2Date(md),convert2Date(tcd),convert2Date(rd),convert2Date(ac),rem,su,type,addRem,loginsap,Assignhours});
		
				
 
		
		} catch (Exception   e) {
			
			e.printStackTrace();
		}
		try{
		App addactionmailtrigger = new App();
		actionmaildao actmail=new actionmaildao();
		String hclmailid=(String) request.getSession().getAttribute("hclmail");
		System.out.println("hclmaillllllllllll"+hclmailid+" giving mail"+om);
		String[] stringArray = {om,hclmailid};
		List<String> stringList = new ArrayList<String>(Arrays.asList(sdesc,Bussinesscriticality,loginname));
		System.out.println("shortdescccc-------"+stringList);
		for(int i=0;i<=stringArray.length-1;i++){
			System.out.println("employeeeee "+stringArray[i]);
			//List<templatevo> tempmailaction=actmail.actionmail(cat, raid, om, sta, jdbcTemplate);
											  
			addactionmailtrigger.Mailtrigger("celeritas@hcl.com",stringArray[i], "Action Assigned","Harish","/actionmail.vm",stringList,null);
			
		//s.method("shyamsunder.p@hcl.com",stringArray[i], "Action Assingned", "/actionmail.vm",tempmailaction);
	}
		}
		catch(Exception e){
			 //JOptionPane.showMessageDialog(null, "The Server is not accessible to send mail", "ERROR", JOptionPane.ERROR_MESSAGE);
			  e.getMessage(); 

			
		}
		  
		
		return "forward:../../pmoAutomation/ActionTrack/ActionStatus.php";





		}
	/* @RequestMapping(value = "/ActionStatus.php", method = RequestMethod.GET)
	    public String AStatus(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {

	    		return "ActionTrack/ActionStatus";

	    	}*/
	 
	 @RequestMapping(value = "/ActionStatus.php")
	    public String statuspage(HttpServletRequest request,
	    		HttpServletResponse response) throws ServletException, IOException 
	    {
		 try{
	    	int sapid=(int) request.getSession().getAttribute("managerId");
	    	String loginName = (String)request.getSession().getAttribute("LOGINNAME");
			request.setAttribute("LOGINNAME", loginName);
	    	AddActiondao aad=new AddActiondao();
	    	List<statusVO> actionstatopen=aad.actionStatusopen(sapid, jdbcTemplate);
	    	List<statusVO> actionstatopenmedium=aad.actionStatusopenmedium(sapid, jdbcTemplate);
	    	List<statusVO> actionstatopenlow=aad.actionStatusopenlow(sapid, jdbcTemplate);
	    	List<statusVO> actionstatclose=aad.actionStatusclose(sapid, jdbcTemplate);
	    	List<statusVO> actionstatoverdue=aad.actionStatusoverdue(sapid, jdbcTemplate);
	    	List<statusVO> actionstatontrack=aad.actionStatusontrack(sapid, jdbcTemplate);
	    	List<statusVO> actionstatassigned=aad.actionStatusassigned(sapid, jdbcTemplate);
	    	List<statusVO> actionstatunassigned=aad.actionStatusunassigned(sapid, jdbcTemplate);
	    	List<statusVO> actionstatclosemedium=aad.actionStatusclosemedium(sapid, jdbcTemplate);
	    	List<statusVO> actionstatoverduemedium=aad.actionStatusoverduemedium(sapid, jdbcTemplate);
	    	List<statusVO> actionstatontrackmedium=aad.actionStatusontrackmedium(sapid, jdbcTemplate);
	    	List<statusVO> actionstatassignedmedium=aad.actionStatusassignedmedium(sapid, jdbcTemplate);
	    	List<statusVO> actionstatunassignedmedium=aad.actionStatusunassignedmedium(sapid, jdbcTemplate);
	    	List<statusVO> actionstatcloselow=aad.actionStatuscloselow(sapid, jdbcTemplate);
	    	List<statusVO> actionstatoverduelow=aad.actionStatusoverduelow(sapid, jdbcTemplate);
	    	List<statusVO> actionstatontracklow=aad.actionStatusontracklow(sapid, jdbcTemplate);
	    	List<statusVO> actionstatassignedlow=aad.actionStatusassignedlow(sapid, jdbcTemplate);
	    	List<statusVO> actionstatunassignedlow=aad.actionStatusunassignedlow(sapid, jdbcTemplate);
	    	System.out.println("open high total "+actionstatopen);
	    	
	    	
	    	assignedStatusByMedao assign=new assignedStatusByMedao();
	    	List<statusVO> assignactionstatopen=assign.actionStatusopen(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatopenmedium=assign.actionStatusopenmedium(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatopenlow=assign.actionStatusopenlow(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatclose=assign.actionStatusclose(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatclosemedium=assign.actionStatusclosemedium(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatcloselow=assign.actionStatuscloselow(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatoverdue=assign.actionStatusoverdue(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatoverduemedium=assign.actionStatusoverduemedium(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatoverduelow=assign.actionStatusoverduelow(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatontrack=assign.actionStatusontrack(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatontrackmedium=assign.actionStatusontrackmedium(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatontracklow=assign.actionStatusontracklow(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatassigned=assign.actionStatusassigned(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatassignedmedium=assign.actionStatusassignedmedium(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatassignedlow=assign.actionStatusassignedlow(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatunassigned=assign.actionStatusunassigned(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatunassignedmedium=assign.actionStatusunassignedmedium(sapid, jdbcTemplate);
	    	List<statusVO> assignactionstatunassignedlow=assign.actionStatusunassignedlow(sapid, jdbcTemplate);
	    	System.out.println("assigned open high total "+assignactionstatopen);
	    	
	    	request.setAttribute("statusactionopen",actionstatopen);
	    	request.setAttribute("statusactionopenmedium",actionstatopenmedium);
	    	request.setAttribute("statusactionopenlow",actionstatopenlow);
	    	request.setAttribute("statusactionclose",actionstatclose);
	    	request.setAttribute("statusactionclosemedium",actionstatclosemedium);
	    	request.setAttribute("statusactioncloselow",actionstatcloselow);
	    	request.setAttribute("statusactionoverdue",actionstatoverdue);
	    	request.setAttribute("statusactionoverduemedium",actionstatoverduemedium);
	    	request.setAttribute("statusactionoverduelow",actionstatoverduelow);
	    	request.setAttribute("statusactionontrack",actionstatontrack);
	    	request.setAttribute("statusactionontrackmedium",actionstatontrackmedium);
	    	request.setAttribute("statusactionontracklow",actionstatontracklow);
	    	request.setAttribute("statusactionassigned",actionstatassigned);
	    	request.setAttribute("statusactionassignedmedium",actionstatassignedmedium);
	    	request.setAttribute("statusactionassignedlow",actionstatassignedlow);
	    	request.setAttribute("statusactionunassigned",actionstatunassigned);	
	    	request.setAttribute("statusactionunassignedmedium",actionstatunassignedmedium);
	    	request.setAttribute("statusactionunassignedlow",actionstatunassignedlow);
	    	
	    	
	    	
	    	request.setAttribute("assignstatusactionopen",assignactionstatopen);
	    	request.setAttribute("assignstatusactionopenmedium",assignactionstatopenmedium);
	    	request.setAttribute("assignstatusactionopenlow",assignactionstatopenlow);
	    	request.setAttribute("assignstatusactionclose",assignactionstatclose);
	    	request.setAttribute("assignstatusactionclosemedium",assignactionstatclosemedium);
	    	request.setAttribute("assignstatusactioncloselow",assignactionstatcloselow);
	    	request.setAttribute("assignstatusactionoverdue",assignactionstatoverdue);
	    	request.setAttribute("assignstatusactionoverduemedium",assignactionstatoverduemedium);
	    	request.setAttribute("assignstatusactionoverduelow",assignactionstatoverduelow);
	    	request.setAttribute("assignstatusactionontrack",assignactionstatontrack);
	    	request.setAttribute("assignstatusactionontrackmedium",assignactionstatontrackmedium);
	    	request.setAttribute("assignstatusactionontracklow",assignactionstatontracklow);
	    	request.setAttribute("assignstatusactionassigned",assignactionstatassigned);
	    	request.setAttribute("assignstatusactionassignedmedium",assignactionstatassignedmedium);
	    	request.setAttribute("assignstatusactionassignedlow",assignactionstatassignedlow);
	    	request.setAttribute("assignstatusactionunassigned",assignactionstatunassigned);
	    	request.setAttribute("assignstatusactionunassignedmedium",assignactionstatunassignedmedium);
	    	request.setAttribute("assignstatusactionunassignedlow",assignactionstatunassignedlow);
		 }
		 catch(Exception e){
			 return "forward:../../pmoAutomation/Login/errorPage.php";
		 }
	    		return "ActionTrack/ActionStatus";

	    	}

	 
	
}


	

