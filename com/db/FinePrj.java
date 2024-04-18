package com.db;

import java.sql.*;
import java.util.Scanner;

public class FinePrj {
    Statement stmt = null;
    PreparedStatement pstm = null;
    Connection con = null;
    ResultSet rs = null;
    public void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Scanner scanner = new Scanner(System.in);
            System.out.print("아이디를 입력하세요: ");
            String user = scanner.nextLine();
            System.out.print("비밀번호를 입력하세요: ");
            String pw = scanner.nextLine();
            System.out.println("관리 시스템 로그인 성공!!");

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.createStatement();

            // System.out.println("연결 성공");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            con.close();
            System.out.printf("DB 연결 해제\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String name, String fineitem,String finedate, int fine) {    // 정보 입력
        String sql = "INSERT INTO FINE"
                + " VALUES (?, ?, ?, ?)";
//        System.out.println(sql);
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2,  fineitem);
            pstm.setString(3,  finedate);
            pstm.setInt(4,  fine);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(String name, String fineitem) {
        String sql = "DELETE FROM FINE WHERE name = ? AND fineitem = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, fineitem);

            int affected = pstm.executeUpdate();
            if (affected > 0) {
                System.out.println("과태료를 납부하여 삭제되었습니다.");
            } else {
                System.out.println("일치한 정보가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void select(String name) {       // 정보 조회
        String sql = "SELECT * FROM FINE WHERE NAME=? ORDER BY FINEDATE ASC";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, name);

            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                System.out.println("일치하는 정보가 없습니다.");
            } else {
                do {
                    System.out.println(rs.getString("NAME") + "님께서는 "+rs.getString("FINEDATE")+ "에 " + rs.getString("FINEITEM")
                            + "을(를) 해서 " + rs.getString("FINE") + "원 과태료가 나왔습니다.");
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
