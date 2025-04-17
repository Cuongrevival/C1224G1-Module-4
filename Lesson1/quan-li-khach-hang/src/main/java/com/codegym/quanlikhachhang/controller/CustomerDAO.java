package com.codegym.quanlikhachhang.controller;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection conn;
    public CustomerDAO() {
        conn = DBConnection.getConnection();
    }
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from Customer";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public void updateCustomer(Customer customer) {
        String sql = "update Customer set name=?, email=?, address=? where id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setInt(4, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql = "select * from Customer where id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
