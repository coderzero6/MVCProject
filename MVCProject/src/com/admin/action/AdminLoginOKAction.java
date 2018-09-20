package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdminDAO;
import com.controller.action.Action;
import com.controller.action.ActionForward;

public class AdminLoginOKAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String admin_id = request.getParameter("admin_id").trim();
		String admin_pwd = request.getParameter("admin_pwd").trim();
		
		AdminDAO dao = AdminDAO.getInstance();
		int re = dao.adminCheck(admin_id, admin_pwd);
		if(re==1) { // id/pwd 일치
			session.setAttribute("admin_id", admin_id);
			//view page 포워딩
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);//*.jsp 이동
			forward.setPath("./admin/admin_main.jsp");
			return forward;
		}else if(re==-1) {
			out.println("<script>");
			out.println("alert('등로되지 않은 관리자 입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else if(re==0) {
			out.println("<script>");
			out.println("alert('비번이 틀립니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return null;
	}

}
