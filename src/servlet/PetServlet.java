package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.PetDAO;
//import dao.VisitDAO;
import entity.Pet;

@MultipartConfig
@WebServlet("/PetServlet")
public class PetServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if ("toAdd".equals(m)) {
			toAdd(request, response);
		} else if ("delete".equals(m)) {
			delete(request, response);
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int petId = Integer.parseInt(request.getParameter("petId"));
			PetDAO petDAO = new PetDAO();
			petDAO.delete(petId);
			request.setAttribute("msg", "删除成功");
			request.getRequestDispatcher(
					"/CustomerServlet?m=showDetail&cid="
							+ request.getParameter("cid")).forward(request,
					response);
			;
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher(
					"/CustomerServlet?m=showDetail&cid="
							+ request.getParameter("cid")).forward(request,
					response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if ("add".equals(m)) {
			add(request, response);
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Part part = request.getPart("photo");
			String filename = getFileName(part);
			String photo;
			if (filename != null) {
				long currentTimeMillis = System.currentTimeMillis();
				photo = "photo/" + currentTimeMillis
						+ filename.substring(filename.lastIndexOf("."));
				filename = getServletContext().getRealPath("/") + "/" + photo;
				part.write(filename);
			} else {
				photo = "photo/default.jpg";
			}
			Pet pet = new Pet();
			pet.setName(request.getParameter("name"));
			pet.setBirthdate(request.getParameter("birthdate"));
			pet.setPhoto(photo);
			pet.setOwnerId(Integer.parseInt(request.getParameter("cid")));
			PetDAO petDAO = new PetDAO();
			petDAO.save(pet);
			request.setAttribute("msg", "添加成功");
			response.sendRedirect("CustomerServlet?m=showDetail&cid="
					+ pet.getOwnerId());
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/petadd.jsp").forward(request,
					response);
		}
	}

	private String getFileName(Part part) {
		// 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名
		String cotentDesc = part.getHeader("content-disposition");
		String fileName = null;
		Pattern pattern = Pattern.compile("filename=\".+\"");
		Matcher matcher = pattern.matcher(cotentDesc);
		if (matcher.find()) {
			fileName = matcher.group();
			fileName = fileName.substring(10, fileName.length() - 1);
		}
		return fileName;
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/petadd.jsp").forward(request, response);
	}

}
