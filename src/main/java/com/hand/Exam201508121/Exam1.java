package com.hand.Exam201508121;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Exam1 {

	public static void main(String[] args) {
		System.out.println("请输入Country ID:");
		Scanner scanner = new Scanner(System.in);
		int countryID = scanner.nextInt();
		
		String sql = "SELECT * FROM sakila.city"; 	//查找所有的用户信息
		String sql2 = "SELECT * FROM sakila.country";
		Connection conn = null;          			//代表当前的数据库连接
		Statement st = null;						//用于向数据库发表SQL语句
		ResultSet rs = null;						//表示结果集，封装了从数据库查询到的数据
		
		try {
			Class.forName("com.mysql.jdbc.Driver");                      
//		用于注册MySQL JDBC的驱动程序  forName方法用来初始化参数指定的类
//		并创建对应的实例对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","");
//		获取MySQL数据库连接三个参数：MySQL数据库的URL，用户名，密码。
			st =  conn.createStatement();
			
//		创建statement对象使用其对象的executeQuery方法来发送SQL语句，返回一个resultset对象
			rs = st.executeQuery(sql2);
			while( rs.next()){
				if (countryID==rs.getInt("country_id")) {
					System.out.println("Country"+rs.getString("country")+"的城市");
				}
			}
			System.out.print("城市ID"+"  |");
			System.out.print("城市名称");
			
			rs = st.executeQuery(sql);
			
			while( rs.next()){
				if (countryID==rs.getInt("country_id")) {
					System.out.println();
					System.out.print(rs.getInt("city_id")+" |");
					System.out.print(rs.getString("city")+" ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {	
				scanner.close();
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
