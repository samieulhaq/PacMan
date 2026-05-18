public class Main {
    static char[][] bb;
    public static void main(String[] args){
        /*
        Starting with a for loop in order to achieve a logic for multilevel
         */
        for (int l=0; l<3; l++) {

            /*
            Declaring the required variables and creating the necessary objects
             */
            BReaderByIndex b = new BReaderByIndex();
            bb = b.read();
            int lives = 2;
            int score = 0;
            boolean game_on = true;
            boolean end_game = false;
            boolean next_level = true;
            int m;
            int condition;

            // variables to keep track of the current positions before going to the next
            int cx;
            int cy;

            /*
            Creating the objects of Ghosts and Pac-Man
             */
            PacMan Pac_Man = new PacMan(BReaderByIndex.px, BReaderByIndex.py);
            Ghost[] ghosts = new Ghost[4];
            ghosts[0] = new Ghost(BReaderByIndex.g_indexes[0][0], BReaderByIndex.g_indexes[1][0]);
            ghosts[1] = new Ghost(BReaderByIndex.g_indexes[0][1], BReaderByIndex.g_indexes[1][1]);
            ghosts[2] = new Ghost(BReaderByIndex.g_indexes[0][2], BReaderByIndex.g_indexes[1][2]);
            ghosts[3] = new Ghost(BReaderByIndex.g_indexes[0][3], BReaderByIndex.g_indexes[1][3]);
            char[] g_replacer = {'·', '·', '·', '·'};

            /*
            Playing the game until ghost reaches the Pac-Man
             */
            while (true) {

                /*
                Printing the number of lives and the score
                 */
                System.out.print(" Lives: " + (lives + 1) + "     " + "Score: "+score + "      Level: ");
                System.out.println(BReaderByIndex.level);



                /*
                Clearing the screen at once in order to print the next board


                //String s = "033[H\033[2J";
                //System.out.println(s);
                //System.out.flush();
                */




                /*
                Printing the Board
                 */
                for (char[] i : bb) {
                    for (char c : i) {
                        System.out.print(" " + c);
                    }
                    System.out.println();
                }

                /*
                Reducing the number of lives when the ghost reaches the Pac-Man
                 */
                if (end_game) {
                    break;
                }

                /*
                Asking for the direction of motion for Pac-Man by calling the DirectionInput Function
                 */
                m = DirectionInput.Direction();

                /*
                Calling the move function for the Pac-Man and changing its position depending on the value returned by the
                move function
                 */
                cx = Pac_Man.getX();
                cy = Pac_Man.getY();
                condition = Pac_Man.move(m);
                if (condition == 2) {
                    bb[cx][cy] = ' ';
                    game_on = false;
                } else if (condition == 1) {
                    bb[cx][cy] = ' ';
                    if (bb[Pac_Man.getX()][Pac_Man.getY()] == '·') {
                        score++;
                    }
                    bb[Pac_Man.getX()][Pac_Man.getY()] = 'P';
                }

                /*
                Moving the ghosts in such a way they follow the Pac-Man
                 */
                for (int i=0;i<4;i++) {
                    cx = ghosts[i].getX();
                    cy = ghosts[i].getY();
                    condition = ghosts[i].move(Pac_Man, i);
                    if (condition == 1) {
                        bb[cx][cy] = g_replacer[i];
                        g_replacer[i] = bb[ghosts[i].getX()][ghosts[i].getY()];
                        bb[ghosts[i].getX()][ghosts[i].getY()] = 'G';
                        Ghost.dup[0][i] = ghosts[i].getX();
                        Ghost.dup[1][i] = ghosts[i].getY();
                    } else if (condition == 2) {
                        bb[cx][cy] = g_replacer[i];
                        bb[ghosts[i].getX()][ghosts[i].getY()] = 'G';
                        g_replacer[i] = ' ';
                        game_on = false;
                    }
                }

                if (!game_on) {
                    if (lives == 0) {
                        end_game = true;
                        next_level = false;
                    } else {
                        lives--;
                        game_on = true;
                        BReaderByIndex.PacManNewPosition(BReaderByIndex.b_arr, 1);
                        Pac_Man.setX(BReaderByIndex.px);
                        Pac_Man.setY(BReaderByIndex.py);
                        if (bb[Pac_Man.getX()][Pac_Man.getY()] == '·'){
                            BReaderByIndex.score_control--;
                        }
                        bb[BReaderByIndex.px][BReaderByIndex.py] = 'P';
                    }
                }

                /*
                Checking if game won
                 */
                if (score == BReaderByIndex.score_control) {
                    end_game = true;
                }
            }

            /*
            Check to move to the next level or end the game
             */

            if (!next_level){
                System.out.println("You Lost!");
                break;
            }else {
                System.out.println("Congratulations! You Won.");
            }
        }
    }
}