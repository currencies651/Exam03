package com.hand.Exam201508121;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Exam2 {

	public static void main(String[] args) {
		System.out.println("请输入Customer ID:");
		Scanner scanner = new Scanner(System.in);
		 int storeId =0;
		 int manager_Staff_Id=0;
		 int inventory_Id = 0;
		int CustomerID = scanner.nextInt();

		String sql = "SELECT * FROM sakila.customer"; 		//查找所有的用户信息
		String sql2 = "SELECT * FROM sakila.store";
		String sql3 = "SELECT * FROM sakila.inventory";
		Connection conn = null;          			//代表当前的数据库连接
		Statement st = null;						//用于向数据库发表SQL语句
		ResultSet rs = null;						//表示结果集，封装了从数据库查询到的数据
		
		try {
			Class.forName("com.mysql.jdbc.Driver");                      
//		用于注册MySQL JDBC的驱动程序  forName方法用来初始化参数指定的类
//		并创建对应的实例对象
			conn = (Connection) DriverManager
		.getConnection("jdbc:mysql://localhost:3306/sakila","root","");
//		获取MySQL数据库连接三个参数：MySQL数据库的URL，用户名，密码。
			st =  (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
//		创建statement对象使用其对象的executeQuery方法来发送SQL语句，返回一个resultset对象
			while( rs.next()){
				if (CustomerID==rs.getInt("customer_id")) {
					  storeId = rs.getInt("store_id");
				}
				rs = st.executeQuery(sql2);
				while( rs.next()){
					if (storeId==rs.getInt("store_id")) {
						manager_Staff_Id = rs.getInt("manager_staff_id");
					}
					rs = st.executeQuery(sql3);
					if (manager_Staff_Id==rs.getInt("manager_staff_id")) {
						inventory_Id = rs.getInt("inventory_d");
						System.out.println();
						System.out.print(rs.getInt("city_id")+" |");
						System.out.print(rs.getString("city")+" ");
					}
				System.out.print(rs.getInt("id")+" ");
				System.out.print(rs.getString("name")+" ");
				System.out.print(rs.getString("password")+" ");
				System.out.print(rs.getString("email")+" ");
				System.out.println();
			}
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {	
				rs.close();
			} catch (Exception e2) {
			}
			
			try {
				st.close();
			} catch (Exception e3) {
			} 
				
			try {
				conn.close();
			} catch (Exception e4) {
			} 
		}
	}
}
