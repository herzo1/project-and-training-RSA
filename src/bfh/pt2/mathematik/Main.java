package bfh.pt2.mathematik;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        int num = RSA.power(145, 69, 667);
        System.out.println(num);

        /*
        System.out.println("Solution to 5a:");
        List<Integer> solution = RSA.encryptMessage(RSA.M_1, RSA.P_1, RSA.Q_1, RSA.E_1);
        solution.forEach(System.out::println);

        System.out.println("Solution to 5b:");
        solution = RSA.signMessage(RSA.NAME, RSA.P_2, RSA.Q_2, RSA.E_1);
        solution.forEach(System.out::println);
         */
    }
}
