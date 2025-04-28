package miniP.dao;

import java.sql.*;
import java.util.*;

import miniP.model.Cart;
import miniP.model.Product;

public class ProductDao {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ProductDao(Connection con) {
		super();
		this.con = con;
	}

	public List<Product> getAllProducts() {
		List<Product> book = new ArrayList<>();
		try {

			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));

				book.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return book;
	}

	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query = "select * from products where id=? ";

			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return row;
	}

	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					query = "select price from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						sum += rs.getDouble("price") * item.getQuantity();
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sum;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> book = new ArrayList<>();
		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						book.add(row);
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return book;
	}
	
	public void insertProduct(Product product) {
		try {
			query = "INSERT INTO products (name, category, price, image) VALUES (?, ?, ?, ?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, product.getName());
			pst.setString(2, product.getCategory());
			pst.setDouble(3, product.getPrice());
			pst.setString(4, product.getImage());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Insert Error: " + e.getMessage());
		}
	}

	public boolean deleteProduct(int id) {
		boolean result = false;
		try {
			query = "DELETE FROM products WHERE id = ?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			int rowsAffected = pst.executeUpdate();
			result = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Error: " + e.getMessage());
		}
		return result;
	}

	public boolean updateProduct(Product product) {
		boolean result = false;
		try {
			query = "UPDATE products SET name=?, category=?, price=?, image=? WHERE id=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, product.getName());
			pst.setString(2, product.getCategory());
			pst.setDouble(3, product.getPrice());
			pst.setString(4, product.getImage());
			pst.setInt(5, product.getId());
			int rowsAffected = pst.executeUpdate();
			result = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Error: " + e.getMessage());
		}
		return result;
	}

}
