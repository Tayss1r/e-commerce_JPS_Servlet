package miniP.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import miniP.connection.DBCon;
import miniP.dao.OrderDao;
import miniP.model.*;


@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    
	    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		User auth = (User) request.getSession().getAttribute("auth");

	    if(cart_list != null && auth != null) {
			for(Cart c : cart_list) {
				Order order = new Order();
				order.setId(c.getId());
				order.setUid(auth.getId());
				order.setQunatity(c.getQuantity());
				order.setDate(formatter.format(date));
				
				try {
					OrderDao oDao = new OrderDao(DBCon.getConnection());
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cart_list.clear();
			response.sendRedirect("orders.jsp");

		} else {
			if(auth == null) {
				response.sendRedirect("login.jsp");
			} else {
				response.sendRedirect("cart.jsp");
			}
		}
	}


}