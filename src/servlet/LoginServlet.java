package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		String msg = null;
		String realcode = request.getSession(true).getAttribute("realcode").toString();
		String inputcode = request.getParameter("checkcode");
		if (realcode.equalsIgnoreCase(inputcode)) {
			UserDAO userDAO = new UserDAO();
			try {
				User user = userDAO.getByName(request.getParameter("name"));
				if (user == null) {
					url = "/index.jsp";
					msg = "用户名不存在";
				} else if (!user.getPwd().equals(request.getParameter("pwd"))) {
					url = "/index.jsp";
					msg = "密码错误";
				} else {
					request.getSession(true).setAttribute("user", user);
					if(user.getRole().equals("admin")){
						url = "/vetsearch.jsp";
					}else if(user.getRole().equals("customer")){
						url="/custindex.jsp";
					}
					msg = "登录成功";
				}
			} catch (Exception e) {
				url = "/index.jsp";
				msg = e.toString();
			}
		} else {
			url = "/index.jsp";
			msg = "验证码输入有误";
		}

		request.setAttribute("msg", msg);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
