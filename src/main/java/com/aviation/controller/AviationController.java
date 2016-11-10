package com.aviation.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aviation.entity.Component;
import com.aviation.entity.ComponentHistory;
import com.aviation.entity.Filter;

import com.aviation.entity.Login;
import com.aviation.service.AviationService;
import com.aviation.vo.ComponentReport;
//import com.mysql.fabric.xmlrpc.base.Array;

import static com.aviation.util.PathConstants.*;

@RestController
public class AviationController {
	
	private List<Long> componentsIds;
	private String removalFromDate;
	private String removalToDate;
	private String optionEnd;
	private String optionStart;


	@Autowired
	private AviationService aviationService;

	@RequestMapping(value = LOAD_COMPONENT_BY_START_END_DATE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Component> loadComponentData(@PathVariable final String startDate, @PathVariable final String endDate)
			throws ParseException {

		final String pattern = DATEFORMAT;
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = new SimpleDateFormat(pattern).parse(startDate);
			eDate = new SimpleDateFormat(pattern).parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return aviationService.getComponent(sDate, eDate);
	}

	@RequestMapping(value = SAVEFILTER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveFilter(@RequestBody final Filter filter) throws ParseException {
		aviationService.saveFilter(filter);
	}
	
	@RequestMapping(value = UPDATE_FILTER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateFilter(@RequestBody final Filter filter) throws ParseException {
		aviationService.updateFilter(filter);
	}
	

	@RequestMapping(value = SAVE_DEFUALT_FILTER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveAsDefaultFilter(@RequestBody Filter filter) throws ParseException {
		aviationService.saveAsDefaultFilter(filter);
	}

	@RequestMapping(value = GET_FILTERS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Filter> getFilters() {
		List<Filter> test=aviationService.getFilters();
		for(Filter i:test){
			//system.out.println("hi test"+i);
		}
		
		//system.out.println("hi in load filter"+aviationService.getFilters());
		return aviationService.getFilters();
	}

	@RequestMapping(value = GET_DEFAULT_FILTER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Filter getDefaultFilter() {
		return aviationService.getDefaultFilter();
	}
	
	
	
	@RequestMapping(value = "/removalReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ComponentReport removalReport() {
		// TODOD:: Remove Hard coding 
	//	long[] components = {2312,2302,1642};
		//system.out.println("step value"+step);
		System.out.println("componentsIds");
		System.out.println("componentsIds"+componentsIds.size());
System.out.println("componentsIds"+componentsIds.toString());
System.out.println("componentsIds"+componentsIds.size());
		
		List<Long> compos1=componentsIds;

		ComponentReport componentRemovalRept =  aviationService.getComponents(compos1);

		
		
		 
		 return componentRemovalRept;
	}
	
	@RequestMapping(value = "/splashScreen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> showSplashScreen(/*@RequestBody   List<Long> componentIds*/) {
		// TODOD:: Remove Hard coding 
		
		
		
		List<String> dateRange=new ArrayList<String>();
		String pattern = DATEFORMATNEW;
		Date sDate=null;
		Date eDate=null;
		/*dateRange=dateInterval();
		//system.out.println("date Range"+dateRange);*/

		try {
			sDate =  new SimpleDateFormat(pattern).parse("2014-08-10");
			 eDate =  new SimpleDateFormat(pattern).parse("2016-08-10");
			 
		} catch (ParseException e) {
			e.printStackTrace();
			
		}

		List<Object> componentRemovalRept =  aviationService.getRemovedComponents(sDate, eDate);
		//List<Object> componentRemovalRept =  aviationService.getRemovedComponents(new Date("2014-08-10"), new Date("2016-08-10"));
		//system.out.println(componentRemovalRept);
		
		return componentRemovalRept;
	}
	
	

	
	
	@RequestMapping(value = "/splashScreenMFG", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> showSplashScreenCPN(/*@RequestBody   List<Long> componentIds*/) {
		// TODOD:: Remove Hard coding 
		
		
		
		
		String pattern = DATEFORMATNEW;
		Date sDate=null;
		Date eDate=null;
		try {
			sDate =  new SimpleDateFormat(pattern).parse("2014-08-10");
			 eDate =  new SimpleDateFormat(pattern).parse("2016-08-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		List<Object> componentRemovalRept =  aviationService.getRemovedComponentsMFG(sDate, eDate);
		//List<Object> componentRemovalRept =  aviationService.getRemovedComponentsCPN(new Date("2014-08-10"), new Date("2016-08-10"));
		//system.out.println("in cpn"+componentRemovalRept);
		
		return componentRemovalRept;
	}
	
	
	@RequestMapping(value = "/splashScreenCPNSerial", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> splashScreenCPNSerial(/*@RequestBody   List<Long> componentIds*/) {
		// TODOD:: Remove Hard coding 
		
		
		
		
		String pattern = DATEFORMATNEW;
		Date sDate=null;
		Date eDate=null;
		try {
			sDate =  new SimpleDateFormat(pattern).parse("2014-08-10");
			 eDate =  new SimpleDateFormat(pattern).parse("2016-08-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		List<Object> componentRemovalRept =  aviationService.getRemovedComponentsCPNSerial(sDate, eDate);
		//system.out.println("in cpn"+componentRemovalRept);
		
		return componentRemovalRept;
	}
	

	@RequestMapping(value = "/splashScreenTail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> splashScreenTail(/*@RequestBody   List<Long> componentIds*/) {
		// TODOD:: Remove Hard coding 
		
		
		
		/*
		String pattern = DATEFORMATNEW;
		Date sDate=null;
		Date eDate=null;
		try {
			sDate =  new SimpleDateFormat(pattern).parse("2014-08-10");
			 eDate =  new SimpleDateFormat(pattern).parse("2016-08-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		List<Object> sent=new ArrayList<Object>();
		
		
		List<Object> componentRemovalRept =  aviationService.getRemovedComponentsTail(sDate, eDate);
		
		for(Object i:componentRemovalRept){
			
			
			ComponentHistory temp=new ComponentHistory();
	
			temp=(ComponentHistory) i;
			
			Date fromDate=temp.getFromDate();
			Date toDate=temp.getTodate();
			
			if(toDate == null){
			
				toDate=eDate;
		
			}
			
			long diff = toDate.getTime()-fromDate.getTime();
			long diffDays = (diff / ( 60 * 60 * 1000))+1;
			List<Object> tempArr= new ArrayList<Object>();
			tempArr.add(temp.getTailNo());
			tempArr.add(diffDays);
			sent.add(tempArr);

	
	
		}
		
		
	
	
		return sent;*/
		
		
		
		
		String pattern = DATEFORMATNEW;
		Date sDate=null;
		Date eDate=null;
		try {
			sDate =  new SimpleDateFormat(pattern).parse("2014-08-10");
			 eDate =  new SimpleDateFormat(pattern).parse("2016-08-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		List<Object> componentRemovalRept =  aviationService.getRemovedComponentsTailNoOfRemoval(sDate, eDate);
		//system.out.println("in cpn"+componentRemovalRept);
		
		return componentRemovalRept;
		
		
		
		
		
		
		
	}
	
    public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.DATE) +"/" +
                (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
    }
  

    public  List<String> dateInterval()
    {
    	 Calendar cal = Calendar.getInstance();
         cal.setTimeZone(TimeZone.getTimeZone("GMT"));
         String toDate=getDate(cal);
         cal.add(Calendar.DATE, -30);
         
         String fromDate=getDate(cal);
         toDate=toDate.replaceAll("/", "-");
         fromDate=fromDate.replaceAll("/", "-");
         DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
         List<String> dateRange=new ArrayList<String>();
     	try {
     		Date frmDate= df.parse(fromDate);
             Date tDate= df.parse(toDate);
             
             //system.out.println("Current date"+toDate+ "30 days back"+fromDate);
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             fromDate=formatter.format(frmDate);
             toDate=formatter.format(tDate);
             dateRange.add(fromDate);
             dateRange.add(toDate);
 	} catch (ParseException e) {
 		e.printStackTrace();
 		
 	}
		return dateRange;
 	
         
    }
    
    

	@RequestMapping(value = LOGIN_VERIFICATON, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Login LoginVerification(@PathVariable final String username, @PathVariable final String password)
			{

		//system.out.println("username "+username+" pass "+password);
	
		boolean loginValidRes =  aviationService.isValidLogin(username, password);
		Login sample=new Login();
		sample.setResult(loginValidRes);
		return sample;
	}
	
    
    
    
	@RequestMapping(value = "/postComponentIds/{components}/{fromDate}/{toDate}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void getComponentsIds(@RequestBody List<Component> components,@PathVariable final String fromDate, @PathVariable final String toDate) throws ParseException {

		System.out.println("in post");
		
		componentsIds = new ArrayList<Long>();
		//componentsIds=components;
		for (Component component : components) {
			componentsIds.add(component.getComponentID());
			//system.out.println(component.getComponentID());
		}
		System.out.println("in component id"+componentsIds.size());	
		
		
		
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	      System.out.println("fromdate "+fromDate+" to date "+toDate);
	      optionEnd=toDate;
	      optionStart=fromDate;
 		Date frmDate= df.parse(fromDate);
         Date tDate= df.parse(toDate);
		
		
		//system.out.println("to date and from date"+frmDate+" "+tDate);
		
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 String  startDate=formatter.format(frmDate);
      String  endDate=formatter.format(tDate);
      //system.out.println("after"+startDate+" "+endDate);
		
      removalFromDate=startDate.replaceAll("-", "/");
      removalToDate=endDate.replaceAll("-", "/");
      
	}

    
    
    
    
	
	@RequestMapping(value = "/paginationStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> paginationStatus(/*@PathVariable List<Component> componentIds*/) {
		// TODOD:: Remove Hard coding 

		
		List<String> status =  new ArrayList<String>();
		 
		
		
		System.out.println("in status"+status);
		status.add(removalFromDate);
		status.add(removalToDate);
		status.add(optionEnd);
		status.add(optionStart);
		System.out.println("in status"+status);
		return status;
	}
	
    
    
    
	
    
    
    
    
	@RequestMapping(value = "/testUnitFilter/{fromDate}/{toDate}/{componentIds}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void test(@PathVariable final String fromDate, @PathVariable final String toDate,@PathVariable final List<String> componentIds) {
		// TODOD:: Remove Hard coding 
		
		/*componentsIds = new ArrayList<Long>();
		for (Component component : components) {
			componentsIds.add(component.getComponentID());
			//system.out.println(component.getComponentID());
		}*/
		System.out.println("components"+fromDate+" "+toDate);
		System.out.println("components"+componentIds.size());
		System.out.println("components"+componentIds.toString());
		//System.out.println("in component id"+componentsIds.size());	
		
	}
    
	
}
