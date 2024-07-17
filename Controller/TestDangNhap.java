/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author TieuSoi
 */
public class TestDangNhap {
    public static void main(String[] args) {
//        String password = "123";
        String password = "123";

        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println("BCrypt hash: " + hash);
        boolean valuate = BCrypt.checkpw(password, "$2a$12$0ycgDWUni7rECMduMU64vuGzrM/.3d1kqn6S6weyt1CKKV4tIBrcO");
        System.out.println(valuate);
        MailController.sendOTP("phamnguyenkali@gmail.com","12345");
    }
}
