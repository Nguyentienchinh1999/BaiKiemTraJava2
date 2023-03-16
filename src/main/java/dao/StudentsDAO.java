package dao;

import connection.MyConnection;
import model.Students;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {

    public List<Students> getAll() {
        final String sql = "SELECT * FROM `students`";

        List<Students> studentsList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Students students = new Students();
                students.setId(rs.getString("id"));
                students.setFull_name(rs.getString("full_name"));
                students.setGender(rs.getInt("gender"));
                students.setAddress(rs.getString("address"));
                students.setGpa(rs.getDouble("gpa"));
                studentsList.add(students);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentsList;
    }

    public Students getById(String id) {
        final String sql = "SELECT * FROM `students` WHERE  `id` =  '" +id+ "'";
        Students students = null;

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                students = new Students();
                students.setId(rs.getString("id"));
                students.setFull_name(rs.getString("full_name"));
                students.setGender(rs.getInt("gender"));
                students.setAddress(rs.getString("address"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public void insert(Students students){
        final String sql = String.format("INSERT  INTO `students` VALUES ( '%s','%s','%d','%s','%s','%s','%s','%f' ) ",
                students.getId(), students.getFull_name(), students.getGender(), students.getBirth_day(),
                students.getAddress(), students.getPhone_number(), students.getEmail(), students.getGpa());
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Thêm thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Students students, String id) {

        final String sql = String.format("UPDATE `students` SET `full_name`='%s',`gender`='%d',`birth_day`='%s',`address`='%s',`phone_number`='%s', `email`='%s',`gpa`='%f' WHERE `id` = '%s'",
                students.getFull_name(), students.getGender(), students.getBirth_day(), students.getAddress(), students.getPhone_number(), students.getEmail(), students.getGpa(), id
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void delete(String id) {
        Students students = getById(id);

        final String sql = "DELETE FROM `students` WHERE  `id` = '" + id + "'";
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
