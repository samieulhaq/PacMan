import java.util.Scanner;
public class DirectionInput {
    public static int Direction(){
        Scanner sc = new Scanner(System.in);
        int no = 0;
        System.out.println("Enter W-Up, A-Left, D-Right, S-Down)");
        String s = sc.nextLine();
        no = switch (s) {
            case "w" -> 4;
            case "a" -> 1;
            case "s" -> 3;
            case "d" -> 2;
            default -> no;
        };
        return no;
    }
}

