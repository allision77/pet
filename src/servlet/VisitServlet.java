package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VetDAO;
import dao.VisitDAO;
import entity.Vet;
import entity.Visit;

@WebServlet("/VisitServlet")
public class VisitServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Visit visit = new Visit();
			visit.setPetId(Integer.parseInt(request.getParameter("petId")));
			visit.setVetId(Integer.parseInt(request.getParameter("vetId")));
			visit.setDescription(request.getParameter("description"));
			visit.setTreatment(request.getParameter("treatment"));
			VisitDAO visitDAO = new VisitDAO();
			visitDAO.save(visit);
			request.setAttribute("msg", "病历添加成功");
			response.sendRedirect("CustomerServlet?m=showDetail&cid="+request.getParameter("cid"));
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/customersearch.jsp").forward(request,
					response);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if ("showHistory".equals(m)) {
			showHistory(request, response);
		} else if ("toAdd".equals(m)) {
			toAdd(request, response);
		}
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			VetDAO vetDAO = new VetDAO();
			List<Vet> vets = vetDAO.getAll();
			request.setAttribute("vets", vets);
			request.getRequestDispatcher("/visitadd.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			showHistory(request, response);
		}
	}

	private void showHistory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			VisitDAO vistDAO = new VisitDAO();
			List<Visit> visits = vistDAO.getVisitsByPetId(Integer
					.parseInt(request.getParameter("petId")));
			request.setAttribute("visits", visits);
			if (visits.size() == 0) {
				request.setAttribute("msg", "没有找到历史病历");
			}
			request.getRequestDispatcher("/visitsearch_result.jsp").forward(
					request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/customersearch.jsp").forward(request,
					response);
		}
	}

}
