package miniP.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import miniP.connection.DBCon;
import miniP.dao.ProductDao;
import miniP.model.Product;

/**
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/edit-product")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ProductDao productDao = new ProductDao(DBCon.getConnection());
			Product product = productDao.getSingleProduct(id);
			
			if (product != null) {
				request.setAttribute("product", product);
				request.getRequestDispatcher("edit-product.jsp").forward(request, response);
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect("index.jsp");
		} catch (NumberFormatException e) {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			double price = Double.parseDouble(request.getParameter("price"));
			String image = request.getParameter("image");
			
			Product product = new Product();
			product.setId(id);
			product.setName(name);
			product.setCategory(category);
			product.setPrice(price);
			product.setImage(image);
			
			ProductDao productDao = new ProductDao(DBCon.getConnection());
			productDao.updateProduct(product);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("index.jsp");
	}

}
