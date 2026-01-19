/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package j2eebt1;

import Book.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author phamq
 */
public class J2EEBT1 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
    List<Book> listBook = new ArrayList<>();
    Scanner x = new Scanner(System.in);
    String msg = """
        Chương trình quản lý sách
        1. Thêm 1 cuốn sách
        2. Xóa 1 cuốn sách
        3. Thay đổi sách
        4. Xuất thông tin
        5. Tìm sách Lập trình
        6. Lấy sách tối đa theo giá
        7. Tìm kiếm theo tác giả
        0. Thoát
        Chọn chức năng:""";

    int chon = 0;
    do {
        System.out.printf(msg);
        chon = x.nextInt();
        switch (chon) {
            case 1 -> {
                Book newBook = new Book();
                newBook.input();
                listBook.add(newBook);
            }
            case 2 -> {
                System.out.print("Nhập vào mã sách cần xóa: ");
                int bookid = x.nextInt();
                // kiểm tra mã sách
                Book find = listBook.stream()
                        .filter(p -> p.getId() == bookid)
                        .findFirst()
                        .orElseThrow();
                listBook.remove(find);
                System.out.print("Đã xóa sách thành công");
            }
            case 3 -> {
                System.out.print("Nhập vào mã sách cần điều chỉnh: ");
                int bookid = x.nextInt();
                Book find = listBook.stream()
                        .filter(p -> p.getId() == bookid)
                        .findFirst()
                        .orElseThrow();
                // Bạn có thể thêm lệnh find.input() ở đây để cập nhật thông tin
            }
            case 4 -> {
                System.out.println("\n Xuất thông tin danh sách ");
                listBook.forEach(p -> p.output());
            }
            case 5 -> {
                List<Book> list5 = listBook.stream()
                        .filter(u -> u.getTitle().toLowerCase().contains("lập trình"))
                        .toList();
                list5.forEach(Book::output);
            }
        }
        
    } while (chon != 0);
}
    
}
