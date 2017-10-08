package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SpecialityDAO;
import dao.VetDAO;
import entity.Speciality;
import entity.Vet;

@WebServlet("/VetServlet")
public class VetServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if ("toAdd".equals(m)) {
			toAdd(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if ("add".equals(m)) {
			add(request, response);
		} else if ("search".equals(m)) {
			search(request, response);
		}
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SpecialityDAO specDAO = new SpecialityDAO();
			request.setAttribute("specs", specDAO.getAll());
			request.getRequestDispatcher("/vetadd.jsp").forward(request,
					response);

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/vetsearch.jsp").forward(request,
					response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String vetName = request.getParameter("name");
			if (vetName == null||"".equals(vetName)) {
				throw new Exception("请输入医生姓名");
			}
			String specIds[] = request.getParameterValues("specId");
			if (specIds == null||specIds.length==0) {
				throw new Exception("请选择至少一项专业特长");
			}
			Vet vet = new Vet();
			vet.setName(request.getParameter("name"));

			for (int i = 0; i < specIds.length; i++) {
				Speciality spec = new Speciality();
				spec.setId(Integer.valueOf(specIds[i]));
				vet.getSpecs().add(spec);
			}
			VetDAO vetDAO = new VetDAO();
			vetDAO.save(vet);
			request.setAttribute("msg", "操作成功");
			request.getRequestDispatcher("/vetsearch.jsp").forward(request,
					response);

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			toAdd(request, response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String vetName = request.getParameter("vetName");
			String specName = request.getParameter("specName");
			VetDAO vetDAO = new VetDAO();
			List<Vet> vets = vetDAO.search(vetName, specName);
			if (vets.size() == 0) {
				request.setAttribute("msg", "没有找到相关医生信息");
				request.getRequestDispatcher("/vetsearch.jsp").forward(request,
						response);
			} else {
				request.setAttribute("vets", vets);
				request.getRequestDispatcher("/vetsearch_result.jsp").forward(
						request, response);
			}

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/vetsearch.jsp").forward(request,
					response);
		}

	}

}
