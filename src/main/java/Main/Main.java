package Main;

import dao.StudentsDAO;
import model.Students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Students> studentsList = new ArrayList<>();
    public static StudentsDAO studentsDAO = new StudentsDAO();

    private static void mainMenu() {
        System.out.println("--- QUẢN LÝ THÔNG TIN SINH VIÊN---");
        System.out.println("1. Danh sách sinh viên");
        System.out.println("2. Nhập 1 sinh viên mới");
        System.out.println("3. Xóa một sinh viên theo mã:");
        System.out.println("4. Cập nhật thông tin sinh viên");
        System.out.println("5. Tìm một sinh viên theo hộ tên hoặc mã:");
        System.out.println("6. Sắp xếp sinh viên theo điểm số GPA tăng dần");
        System.out.println("7. In ra tất cả các sinh viên nữ ở Hà N có GPA trên 2.5");
        System.out.println("8. Sắp xếp sinh viên theo họ tên, sắp xếp theo bảng chữ cái abc");
        System.out.println("9. Xóa hãng sản xuất theo mã");
    }

    private static void option1(){
    studentsList = studentsDAO.getAll();
        String leftAlignFormat = "| %-11s | %-18s | %-9d | %11s  | %n";
        System.out.format("+-------------+--------------------+-----------+--------------+%n");
        System.out.format("|Mã Sinh viên |     Họ tên         | Giới tính | Địa chỉ      |%n");
        System.out.format("+-------------+--------------------+-----------+--------------+%n");
        for (int i = 0; i < studentsList.size(); i++) {
            System.out.format(leftAlignFormat, studentsList.get(i).getId(), studentsList.get(i).getFull_name(), studentsList.get(i).getGender(), studentsList.get(i).getAddress());
        }
        System.out.format("+-------------+--------------------+-----------+--------------+%n");
    }

    private static void option2(Scanner in){
        Students students = new Students();
        System.out.print("\t nhập id sinh viên: ");
        students.setId(in.nextLine());
        System.out.print("\t nhập tên: ");
        students.setFull_name(in.nextLine());
        System.out.print("\t nhập giới tính: ");
        students.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("\t nhập ngày sinh: ");
        students.setBirth_day(in.nextLine());
        System.out.print("\t nhập địa chỉ: ");
        students.setAddress(in.nextLine());
        System.out.print("\t nhập số đt: ");
        students.setPhone_number(in.nextLine());
        System.out.print("\t nhập email: ");
        students.setEmail(in.nextLine());
        System.out.print("\t nhập điểm GPA: ");
        students.setGpa(in.nextDouble());
        studentsDAO.insert(students);
        System.out.println("thêm thành công");
    }

    private static void option3(Scanner in){
        System.out.println("nhập vào mã sinh viên muốn xóa: ");
        String id = in.nextLine();
        Students tmp = studentsDAO.getById(id);
        if(tmp == null){
            System.out.println("sinh viên không tồn tại");
            return;
        }
        studentsDAO.delete(id);
        System.out.println("xóa thành công");
    }

    private static void option4(Scanner in){
        System.out.print("\t nhập vào id sinh viên cần update: ");
        String id = in.nextLine();
        Students tmp = studentsDAO.getById(id);
        if(tmp == null){
            System.out.println("sinh viên không tồn tại");
            return;
        }
        Students students = new Students();
        System.out.print("\t nhập tên sinh viên: ");
        students.setFull_name(in.nextLine());
        System.out.print("\t nhập giới tính: ");
        students.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("\t nhập ngày sinh :");
        students.setBirth_day(in.nextLine());
        System.out.print("\t nhập địa chỉ: ");
        students.setAddress(in.nextLine());
        System.out.print("\t nhập số đt: ");
        students.setPhone_number(in.nextLine());
        System.out.print("\t nhập email: ");
        students.setEmail(in.nextLine());
        System.out.print("\t nhập diem GPA: ");
        students.setGpa(in.nextDouble());

        studentsDAO.update(students, id);
        System.out.println("update thành công");
    }

    private static void option5(Scanner in){
        System.out.println("nhập vào ID sinh viên cần tiìm");
        String id = in.nextLine();
        Students tmp = studentsDAO.getById(id);
        if(tmp == null){
            System.out.println("sinh viên không tồn tại");
            return;
        }
        studentsDAO.getById(id);
        System.out.println(tmp);
    }

    private static void option6(){
        List<Students> sortStudent = new ArrayList<>();
        sortStudent = studentsDAO.getAll();
        Collections.sort(sortStudent);
        sortStudent.stream().forEach(students -> {
            System.out.println("ID: " + students.getId() + "--- Name: "  +students.getFull_name() + " ----- GPA = " + students.getGpa());
        });
    }

    private static void option7(){
        studentsList = studentsDAO.getAll();
        studentsList.stream().filter(students -> students.getGender() == 0 && students.getAddress().toLowerCase().equalsIgnoreCase("hà nội") && students.getGpa() > 2.5)
                .forEach(students -> {
                    System.out.println("ID: " + students.getId() + "--- Name: "  +students.getFull_name() + " ----- GPA = " + students.getGpa() + "---- Gender: " + students.getGender() + "----- Address: " + students.getAddress());
                });
    }

    private static void option8(){
        studentsList = studentsDAO.getAll();
        studentsList.stream().sorted((o1, o2) -> {
            return  o1.getFull_name().compareTo(o2.getFull_name());
        }).forEach(System.out::println);
    }
    public static void main(String[] args) {
        int option = -1;
        Scanner in = new Scanner(System.in);
        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng!");
                continue;
            }
            if (option < 1 || option > 8) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            // Xu ly cac TH thoa man
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    option5(in);
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    option7();
                    break;
                case 8:
                    option8();
            }

        }
        while (option != 8);
        in.close();
    }
}
