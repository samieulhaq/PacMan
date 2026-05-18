import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BReaderByIndex {

    static int l_no = 128;
    static ArrayList<ArrayList<Integer>> hurdles_i_arr = new ArrayList<>();
    static int px = 0; static int py = 0;
    static int[][] g_indexes = new int[2][4];
    static char[][] b_arr = new char[0][0];
    static char[][] o_arr = new char[0][0];
    static int score_control = 0;
    static int level = 0;

    public static void PacManNewPosition(char[][] arr, int n){
        Random r = new Random();
        boolean b = true;
        while (b){
            px = r.nextInt(1, arr.length);
            py = r.nextInt(1, arr[0].length);
            if (!(arr[px][py] == '■')){
                if (n==1) {
                    if (Main.bb[px][py] != 'G') {
                        Main.bb[px][py] = 'P';
                        b = false;
                    }
                }else{
                    o_arr[px][py] = 'P';
                    b = false;
                }
            }
        }

    }

    public char[][] read() {
        hurdles_i_arr.add(new ArrayList<>());
        hurdles_i_arr.add(new ArrayList<>());
        int xp = 0;
        int yp = 0;

        try {
            FileReader file = new FileReader("FBs.txt");
            Scanner sc = new Scanner(file);

            int j = 0;
            while (sc.hasNextInt()) {
                int in_t1 = sc.nextInt();
                int in_t2 = sc.nextInt();

                if ((j-l_no) == 0){
                    level = in_t1;
                    score_control = in_t2;

                } else if ((j-l_no) == 1) {
                    b_arr = new char[in_t1][in_t2];
                    o_arr = new char[in_t1][in_t2];

                }else if((j-l_no)>1) {
                    if (((in_t1 == 2)||(in_t1 == 3))&&(in_t2>50)){
                        break;
                    }

                    hurdles_i_arr.get(0).add(in_t1);
                    hurdles_i_arr.get(1).add(in_t2);
                    b_arr[in_t1][in_t2] = '■';
                    o_arr[in_t1][in_t2] = '■';
                }
                j++;
            }
            l_no = j;
            sc.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        /*
        Adding food at the places where hurdles are not present
         */
        for (int i = 0; i<o_arr.length; i++){
            for (int j = 0; j<o_arr[0].length; j++){
                if (!(o_arr[i][j]=='■')){
                    o_arr[i][j] = '·';
                }
            }
        }

        /*
        Adding Pac-Man at Random Position
         */
        PacManNewPosition(b_arr, 0);

        /*
        Adding 4 Ghosts at the Random Positions
         */
        Random r = new Random();
        for (int i = 0; i<4; i++) {
            boolean b = true;
            while (b) {
                xp = r.nextInt(1, o_arr.length);
                yp = r.nextInt(1, o_arr[0].length);
                if ((!(o_arr[xp][yp] == '■'))&&(!(o_arr[xp][yp] == 'P'))&&(!(o_arr[xp][yp] == 'G'))) {
                    o_arr[xp][yp] = 'G';
                    b_arr[xp][yp] = 'G';
                    b = false;
                }
            }
            g_indexes[0][i] = xp;
            g_indexes[1][i] = yp;
        }




        /*
        Edit the array to remove the extra boxes
        Not for the level two coz its fancy with boxes
        */
        if (!((level % 2) == 0)) {
            for (int i = 0; i < b_arr.length; i++) {
                for (int j = 0; j < b_arr[0].length; j++) {
                    if ((b_arr[i][j] == '■') && ((!((i == 0) || (i == (b_arr.length - 1)))) && (!((j == 0) || (j == (b_arr[0].length - 1)))))) {
                        if ((b_arr[i - 1][j] == '■') && (b_arr[i + 1][j] == '■') && (b_arr[i][j - 1] == '■') && (b_arr[i][j + 1] == '■'))
                            o_arr[i][j] = ' ';
                    }
                }
            }
        }
        return o_arr;

    }
}

