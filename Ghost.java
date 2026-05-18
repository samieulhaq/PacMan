import java.util.Arrays;
public class Ghost {
    int x;
    int y;
    int inc = 1;
    boolean flag = true;
    static int[][] dup = BReaderByIndex.g_indexes;
    int[][] a = BReaderByIndex.hurdles_i_arr.stream().map(I->I.stream().mapToInt(i->i).toArray()).toArray(int[][]::new);
    /*
    Defining an array which will have the direction of motion for the ghosts in an order of less distance
     */
    int[] direction_s= new int[4];
    int[] possibility_to_move= new int[4];

    double d1;
    double d2;
    double d3;
    double d4;

    public Ghost(int dx, int dy) {
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

    /*
    A function to find that is it possible to move in that direction or not
     */
    public void PossibleDirections(int n) {
        /*
        Checking for the upward direction
         */
        flag = true;
        for (int i = 0; i < a[0].length; i++) {
            if ((x - inc == a[0][i]) && (y == a[1][i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < 4; i++) {
                if (i != n) {
                    if ((x - inc == dup[0][i]) && ((y == dup[1][i]))) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (flag){
            possibility_to_move[0] = 1;
        }else {
            possibility_to_move[0]= 0;
        }

        /*
        Checking for the downward direction of motion
         */
        flag = true;
        for (int i = 0; i < a[0].length; i++) {
            if ((x + inc == a[0][i]) && (y == a[1][i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < 4; i++) {
                if (i != n) {
                    if ((x + inc == dup[0][i]) && (y == dup[1][i])) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (flag){
            possibility_to_move[1] = 1;
        }else {
            possibility_to_move[1]= 0;
        }

        /*
        Checking for the rightward direction of motion
         */
        flag = true;
        for (int i = 0; i < a[0].length; i++) {
            if ((x == a[0][i]) && (y + inc == a[1][i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < 4; i++) {
                if (i != n) {
                    if ((x == dup[0][i]) && (y + inc == dup[1][i])) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (flag){
            possibility_to_move[2] = 1;
        }else {
            possibility_to_move[2]= 0;
        }

        /*
        Checking for the leftward possible motion
         */
        flag = true;
        for (int i = 0; i < a[0].length; i++) {
            if ((x == a[0][i]) && (y - inc == a[1][i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < 4; i++) {
                if (i != n) {
                    if (((x == dup[0][i])) && (y - inc == dup[1][i])) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (flag){
            possibility_to_move[3] = 1;
        }else {
            possibility_to_move[3]= 0;
        }
    }

    /*
    This function is to find the direction of possible motion for the ghost so that the distance between Pac-Man and Ghost decreases
     */
    public int Direction(int xx, int yy, int n){
        // Calling the PossibleDirection function to set the array of possibility to move
        PossibleDirections(n);

        // Finding the distance for every possible motion
        d1 = Math.sqrt(((x-1- xx)*(x-1- xx))+((y- yy)*(y- yy))); // 4 up
        d2 = Math.sqrt(((x+1- xx)*(x+1- xx))+((y- yy)*(y- yy))); // 3 down
        d3 = Math.sqrt(((x- xx)*(x- xx))+((y+1- yy)*(y+1- yy))); // 2 right
        d4 = Math.sqrt(((x- xx)*(x- xx))+((y-1- yy)*(y-1- yy))); // 1 left

        int m = 0;
        double[] d = {d1, d2, d3, d4};
        Arrays.sort(d);


        if ((d[0]==d1)&&(possibility_to_move[0]==1)){
            m = 4;
        } else if ((d[0]==d2)&&(possibility_to_move[1]==1)) {
            m = 3;
        } else if ((d[0]==d3)&&(possibility_to_move[2]==1)) {
            m = 2;
        } else if ((d[0]==d4)&&(possibility_to_move[3]==1)) {
            m = 1;
        } else if ((d[1]==d1)&&(possibility_to_move[0]==1)){
            m = 4;
        } else if ((d[1]==d2)&&(possibility_to_move[1]==1)) {
            m = 3;
        } else if ((d[1]==d3) &&(possibility_to_move[2]==1)){
            m = 2;
        } else if ((d[1]==d4)&&(possibility_to_move[3]==1)) {
            m = 1;
        } else if ((d[2]==d1)&&(possibility_to_move[0]==1)){
            m = 4;
        } else if ((d[2]==d2)&&(possibility_to_move[1]==1)) {
            m = 3;
        } else if ((d[2]==d3) &&(possibility_to_move[2]==1)){
            m = 2;
        } else if ((d[2]==d4)&&(possibility_to_move[3]==1)) {
            m = 1;
        } else if ((d[3]==d1)&&(possibility_to_move[0]==1)){
            m = 4;
        } else if ((d[3]==d2)&&(possibility_to_move[1]==1)) {
            m = 3;
        } else if ((d[3]==d3) &&(possibility_to_move[2]==1)){
            m = 2;
        } else if ((d[3]==d4)&&(possibility_to_move[3]==1)) {
            m = 1;
        }
        return m;
    }

    public int move(PacMan man, int n){// n the ghost number
        int var_to_return = 0;

        // Checking the direction of motion by calling the Direction function
        int m = Direction(man.getX(), man.getY(), n);

        if (m == 4){
            if (x != 0) {
                x = x - inc;
                var_to_return = 1;
            }
        }
        else if(m == 3) {
            if (x != BReaderByIndex.b_arr.length - 1) {
                x = x + inc;
                var_to_return = 1;
            }
        }
        else if(m == 1) {
            if (y != 0) {
                y = y - inc;
                var_to_return = 1;
            }
        }
        else if(m == 2) {
            if (y != BReaderByIndex.b_arr[0].length - 1) {
                y = y + inc;
                var_to_return = 1;
            }
        }

        /*
        Checking collision of Ghost with Pac-Man
         */
        if ((x == man.getX())&&(y == man.getY())){
            var_to_return = 2;
        }

        /*
        Returning the value of variable to perform the specific operation
         */
        return var_to_return;
    }
}
