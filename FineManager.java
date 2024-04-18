import com.db.FinePrj;

import java.io.*;
import java.util.Scanner;

public class FineManager {

    public static void showMenu() {
        System.out.println("\n[과태료 관리 시스템]");
        System.out.println("[메뉴]");
        System.out.println("1. 등록");
        System.out.println("2. 조회");
        System.out.println("3. 삭제");
        System.out.println("4. 종료");
    }

    public static void main(String[] args) {
        FinePrj finePrj = new FinePrj();
        finePrj.connect();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            System.out.print("메뉴를 선택해주세요: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("이름을 입력해주세요: ");
                String name = scanner.nextLine();
                System.out.print("과태료 항목을 입력해주세요: ");
                String fineItem = scanner.nextLine();
                System.out.println("날짜를 입력해주세요(예:YYYY-MM-DD)");
                System.out.print(">> ");
                String fineDate = scanner.nextLine();
                System.out.print("과태료를 입력해주세요: ");
                int fineAmount = scanner.nextInt();
                scanner.nextLine();
                System.out.print("과태료 정보가 저장되었습니다.");
                finePrj.insert(name,fineItem,fineDate,fineAmount);
            } else if (choice.equals("2")) {
                System.out.print("조회할 이름을 입력해주세요: ");
                String searchName = scanner.nextLine();
                finePrj.select(searchName);
            } else if (choice.equals("3")) {
                System.out.print("삭제할 이름을 입력해주세요: ");
                String deleteName = scanner.nextLine();
                System.out.print("삭제할 항목을 입력해주세요: ");
                String deleteItem = scanner.nextLine();
                finePrj.delete(deleteName,deleteItem);
            } else if (choice.equals("4")) {
                System.out.println("프로그램을 종료합니다.");
                finePrj.disconnect();
                break;
            } else {
                System.out.println("올바른 메뉴를 입력해주세요.");
            }
        }
        scanner.close();
    }
}
