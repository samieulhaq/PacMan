import java.nio.file.attribute.BasicFileAttributes;

public class PacMan {
    int x;
    int y;
    int inc = 1;
    boolean flag = true;
    int[][] a = BReaderByIndex.hurdles_i_arr.stream().map(I->I.stream().mapToInt(i->i).toArray()).toArray(int[][]::new);
    /*
    Special Flag for level three
     */
    boolean level_3_flag = true;

    public PacMan(int dx, int dy) {
        x = dx;
        y = dy;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int dx) {
        x = dx;
    }
    public void setY(int dy) {
        y = dy;
    }

    public int move(int m){
        int var_to_return = 0;

        if (m == 4) {
            if (x == 0){
                x = BReaderByIndex.b_arr.length-1;
                if (BReaderByIndex.level == 3){
                    y = 14;
                }
                var_to_return = 1;
            }else {
                for (int i = 0; i < a[0].length; i++) {
                    if (x - inc == a[0][i] && y == a[1][i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    x = x - inc;
                    var_to_return = 1;
                }
            }
        }
        else if(m == 3) {
            if (x == BReaderByIndex.b_arr.length - 1) {
                x = 0;
                if (BReaderByIndex.level == 3){
                    if (level_3_flag){
                        y = 9;
                        level_3_flag = false;
                    }else {
                        y = 19;
                        level_3_flag = true;
                    }
                }
                var_to_return = 1;
            } else {
                for (int i = 0; i < a[0].length; i++) {
                    if (x + inc == a[0][i] && y == a[1][i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    x = x + inc;
                    var_to_return = 1;
                }
            }
        }
        else if(m == 1) {
            if (y == 0) {
                y = BReaderByIndex.b_arr[0].length - 1;
                var_to_return = 1;
            } else {
                for (int i = 0; i < a[0].length; i++) {
                    if (x == a[0][i] && y - inc == a[1][i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    y = y - inc;
                    var_to_return = 1;
                }
            }
        }
        else if(m == 2) {
            if (y == BReaderByIndex.b_arr[0].length - 1) {
                y = 0;
                var_to_return = 1;
            } else {
                for (int i = 0; i < a[0].length; i++) {
                    if (x == a[0][i] && y + inc == a[1][i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    y = y + inc;
                    var_to_return = 1;
                }
            }
        }
        /*
        Checking collision with the ghosts
         */
        for (int i = 0;i<BReaderByIndex.g_indexes[0].length; i++){
            if ((x == BReaderByIndex.g_indexes[0][i])&&(y == BReaderByIndex.g_indexes[1][i])){
                var_to_return = 2;
                break;
            }
        }
        flag = true;

        /*
        Returning the value of variable to perform the specific operation
         */
        return var_to_return;
    }
}

