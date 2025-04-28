package miniP.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import miniP.connection.DBCon;
import miniP.dao.ProductDao;
import miniP.model.Product;

import java.io.IOException;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
	    String category = request.getParameter("category");
	    String priceParam = request.getParameter("price");
	    String image = request.getParameter("image");

		// Validation simple
		if (name == null || category == null || priceParam == null || image == null ||
			name.trim().isEmpty() || category.trim().isEmpty() || priceParam.trim().isEmpty() || image.trim().isEmpty()) {
			response.getWriter().println("All fields are required.");
			return;
		}

		double price = 0;
		try {
			price = Double.parseDouble(priceParam.trim());
		} catch (NumberFormatException e) {
			response.getWriter().println("Invalid price format.");
			return;
		}

		Product product = new Product();
		product.setName(name);
		product.setCategory(category);
		product.setPrice(price);
		product.setImage(image);

		try {
			ProductDao pd = new ProductDao(DBCon.getConnection());
			pd.insertProduct(product);
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error adding product: " + e.getMessage());
		} 
		}

}
