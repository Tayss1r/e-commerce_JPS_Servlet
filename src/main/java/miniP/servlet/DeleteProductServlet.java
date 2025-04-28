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

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ProductDao productDao = new ProductDao(DBCon.getConnection());
			boolean result = productDao.deleteProduct(id);
			
			if (result) {
				request.getSession().setAttribute("message", "Product deleted successfully!");
				request.getSession().setAttribute("messageType", "success");
			} else {
				request.getSession().setAttribute("message", "Product not found or already deleted!");
				request.getSession().setAttribute("messageType", "warning");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("message", "Error deleting product: " + e.getMessage());
			request.getSession().setAttribute("messageType", "danger");
		} catch (NumberFormatException e) {
			request.getSession().setAttribute("message", "Invalid product ID!");
			request.getSession().setAttribute("messageType", "danger");
		}
		
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
