package com.controller.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.lookup.PolymorphicMethodBinding;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		
		//Uri command경로 읽어 들이기
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		//service객체 선언
		Action action = null;
		ActionForward forward = null;
		
		PrintWriter out = response.getWriter();
		out.print("<h3>RequestURI = "+RequestURI+"</h3>");
		out.print("<h3>contextPath = "+contextPath+"</h3>");
		out.print("<h3>command = "+command+"</h3>");
		
		//command 프로퍼티 파일 읽어 들이기
		Properties prop = new Properties();
		
		//request.getRealPath("/")는 deprecated 되었다 -> request.getSession().getServletContext().getRealPath("/")
		FileInputStream fis = new FileInputStream(request.getRealPath("/")+"WEB-INF\\command.properties");
		
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		
		
		prop.load(fis);
		fis.close();
		String value = prop.getProperty(command).trim(); //trim 공백제거
		
		System.out.println("prop.getProperty="+value);
		
		if(value.substring(0, 7).equals("execute")) {
			try {
				StringTokenizer st = new StringTokenizer(value,"|");
				String url_1 = st.nextToken();
				String url_2 = st.nextToken();
				System.out.println("url_1="+url_1);
				System.out.println("url_2="+url_2);
				Class url = Class.forName(url_2);
				action = (Action)url.newInstance();
				forward = action.execute(request,response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			forward = new ActionForward();
			forward.setRedirect(false); //redirect 하지않음 -> forward
			forward.setPath(value);
			System.out.println("value="+value);
		}
		
		//command 처리
		//뷰로 이동처리 부분
		if(forward!=null) {
			if(forward.isRedirect()) { //리다이렉트일경우
				response.sendRedirect(forward.getPath());
			}else { //포워드일경우
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				System.out.println("forward.getpath()="+forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}
	}
}
