package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import entity.User;

@WebFilter("*.jsp")
public class AuthenticFilter implements Filter {

	private static List<String> adminAuths = new ArrayList<String>();

	static {
		adminAuths.add("/visitsearch_result.jsp");
		adminAuths.add("/visitadd.jsp");
		adminAuths.add("/vetsearch_result.jsp");
		adminAuths.add("/vetsearch.jsp");
		adminAuths.add("/vetedit.jsp");
		adminAuths.add("/petadd.jsp");
		adminAuths.add("/customersearch.jsp");
		adminAuths.add("/customersearch_result.jsp");
		adminAuths.add("/customerdetail.jsp");
		adminAuths.add("/customeradd.jsp");
	}

	public void destroy() {
		System.out.println("身份验证过滤器销毁");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpreq = (HttpServletRequest) request;
		HttpSession session = httpreq.getSession(true);
		String requestURI = httpreq.getRequestURI();
		requestURI = requestURI.substring(requestURI.lastIndexOf("/"));
		if (adminAuths.contains(requestURI)) {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				request.setAttribute("msg", "请先登录");
				request.getRequestDispatcher("/index.jsp").forward(httpreq,
						response);
			} else if (user.getRole().equals("admin")) {
				chain.doFilter(request, response);
			} else {
				request.setAttribute("msg", "该页面只有管理员能够访问");
				request.getRequestDispatcher("/index.jsp").forward(httpreq,
						response);
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("身份验证过滤器启动");
	}

}
