package com.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Parker;

/**
 * Servlet implementation class Select
 * 
 * @return the result i.e. parking spot information on the online servelet
 */
@WebServlet("/Select")
public class Select extends HttpServlet implements Servlet {
	
	
	/**
	 * doPost does actual implementation of servelet using exception handlers.
	 * 
	 * @param HttpServletRequest stands for user request.
	 * @param HttpServletResponse codes for expected response.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Parker pm = new Parker();
		//String lat = request.getParameter("lat");
		//String lng = request.getParameter("long");	
		String day = request.getParameter("day");
		String adr = request.getParameter("adr");
		
		
		/*List result = null;
		try {
			result = pm.mainFunc(lat, lng, day);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		ArrayList<String> result = null;
		try {
			//result = pm.mainFunc(lat, lng, day);
			result = pm.mainFunc2(adr, day);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		request.setAttribute("spaces",result);		
		 
	   	RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
		dispatcher.forward(request,response);
		
		
	}

}