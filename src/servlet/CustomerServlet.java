package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PetDAO;
import dao.UserDAO;
import entity.Pet;
import entity.User;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if ("toAdd".equals(m)) {
			toAdd(request, response);
		} else if ("showDetail".equals(m)) {
			showDetail(request, response);
		}

	}



	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if ("search".equals(m)) {
			search(request, response);
		} else if ("add".equals(m)) {
			add(request, response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setPwd("123456");
			user.setRole("customer");
			user.setTel(request.getParameter("tel"));
			user.setAddress(request.getParameter("address"));
			UserDAO userDAO = new UserDAO();
			userDAO.save(user);
			request.setAttribute("msg", "添加用户成功");
			request.getRequestDispatcher("/customersearch.jsp").forward(
					request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/customeradd.jsp").forward(request,
					response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserDAO userDAO = new UserDAO();
			List<User> users = userDAO.searchCustomer(request
					.getParameter("customerName"));
			if (users.size() == 0) {
				request.setAttribute("msg", "没有找到相关客户信息");
				request.getRequestDispatcher("/customersearch.jsp").forward(
						request, response);
			}else{
				request.setAttribute("users", users);
				request.getRequestDispatcher("/customersearch_result.jsp").forward(
						request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/customersearch.jsp").forward(
					request, response);
		}

	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/customeradd.jsp").forward(request,
				response);
	}

	private void showDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserDAO userDAO=new UserDAO();
			PetDAO petDAO=new PetDAO();
			int ownerId=Integer.valueOf(request.getParameter("cid"));
			User user=userDAO.getById(ownerId);
			List<Pet> pets=petDAO.getPetsByOwnerId(ownerId);
			user.setPets(pets);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/customerdetail.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/customersearch.jsp").forward(
					request, response);
		}
		
	}
}
