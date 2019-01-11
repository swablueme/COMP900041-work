
import java.util.*;

public class BackgammonTest {
    final static String success = "CORRECT";
    final static String fail = "BUG";

    public static void main(String[] args) {
        Testing t = new Testing();

        String result = (
        t.AssertEquals(t.moveEmpty(new BackgammonBoard()), new int[] { 0, 1 }) &&
        t.AssertEquals(t.moveControlled(new BackgammonBoard()), new int[] { 0, 2 }) &&
        t.AssertEquals(t.moveMultControlled(new BackgammonBoard()), new int[] { 0, 3 }) &&
        t.AssertEquals(t.captureEnemyW(new BackgammonBoard()), new int[] { 0, 1, 1 }) &&
        t.AssertEquals(t.captureEnemyB(new BackgammonBoard()), new int[] { 1, 0, 1 }) &&
        t.AssertEquals(t.whiteBack(new BackgammonBoard()), new int[] { 0, 1 })&&
        t.AssertEquals(t.blackForwards(new BackgammonBoard()), new int[] { 1, 0 })&&
        t.AssertEquals(t.cannotCaptureEnemy(new BackgammonBoard()), new int[] { 1, 2, 0 })&&
        t.AssertEquals(t.moveMoreThanSix(new BackgammonBoard()), new int[] { 1, 0 }) &&
        t.AssertEquals(t.moveOutOfBounds(new BackgammonBoard(),  22, 24), new int[] {1, 0 })&&
        t.AssertEquals(t.moveOutOfBounds(new BackgammonBoard(),  0, -1), new int[] {1, 0 })&&
        t.AssertEquals(t.moveSequence(new BackgammonBoard()), new int[] {1, 1, 1, 1})&&
        t.moveSequence2(new BackgammonBoard()) == false &&
        t.AssertEquals(t.nothing(new BackgammonBoard()), new int[] {0})
        ) == true ? success : fail;
        System.out.println(result);
    }
}

class Testing {
    public static int[] nothing (BackgammonBoard board) {
        board.move(0, 1);
        return new int[] { board.getPointCount(0)};
    }
    public static Boolean moveSequence2 (BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.setPoint(2, 1, true);
        board.setPoint(3, 1, true);
        board.move(0, 3);
        board.move(2, 3);

        return board.getPointBlack(3);
    }
    // sequence
    public static int[] moveSequence (BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.setPoint(1, 1, false);
        board.setPoint(3, 1, true);
        board.setPoint(6, 1, true);
        board.move(0, 3);
        board.move(6, 1);

        return new int[] { board.getPointCount(3), board.getPointCount(1), board.getBarBlackCount(),  board.getBarWhiteCount()};
    }
    
    // move out of bounds 
    public static int[] moveOutOfBounds(BackgammonBoard board, int ptx, int pty) {
        board.setPoint(ptx, 1, false);
        board.move(ptx, pty);
        //System.out.printf("moveEmpty %d %d \n", board.getPointCount(0), board.getPointCount(8));
        return new int[] { board.getPointCount(ptx), board.getPointCount(pty) };
    }
    // move more than six points
    public static int[] moveMoreThanSix(BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.move(0, 8);
        //System.out.printf("moveEmpty %d %d \n", board.getPointCount(0), board.getPointCount(8));
        return new int[] { board.getPointCount(0), board.getPointCount(8) };
    }

    // move black forwards
    public static int[] blackForwards(BackgammonBoard board) {
        board.setPoint(0, 1, true);
        board.move(0, 1);
       // System.out.printf("blackForwards %d %d \n", board.getPointCount(0), board.getPointCount(1));
        return new int[] { board.getPointCount(0), board.getPointCount(1) };
    }

    // move white backwards
    public static int[] whiteBack(BackgammonBoard board) {
        board.setPoint(1, 1, false);
        board.move(1, 0);
        //System.out.printf("whiteBack %d %d \n", board.getPointCount(0), board.getPointCount(1));
        return new int[] { board.getPointCount(0), board.getPointCount(1) };
    }

    // move to point with multiple enemies
    public static int[] cannotCaptureEnemy(BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.setPoint(1, 2, true);
        board.move(0, 1);
        //System.out.printf("captureEnemy %d %d %d \n", board.getPointCount(0), board.getPointCount(1),
        //        board.getBarBlackCount());
        return new int[] { board.getPointCount(0), board.getPointCount(1), board.getBarBlackCount() };
    }
    // moving to enemy spot
    public static int[] captureEnemyB(BackgammonBoard board) {
        board.setPoint(1, 1, true);
        board.setPoint(0, 1, false);
        board.move(1, 0);
        //System.out.printf("captureEnemy %d %d %d \n", board.getPointCount(0), board.getPointCount(1),
        //        board.getBarBlackCount());
        return new int[] { board.getPointCount(0), board.getPointCount(1), board.getBarWhiteCount() };
    }
    // moving to enemy spot
    public static int[] captureEnemyW(BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.setPoint(1, 1, true);
        board.move(0, 1);
        //System.out.printf("captureEnemy %d %d %d \n", board.getPointCount(0), board.getPointCount(1),
        //        board.getBarBlackCount());
        return new int[] { board.getPointCount(0), board.getPointCount(1), board.getBarBlackCount() };
    }

    // moving to own multi controlled spot
    public static int[] moveMultControlled(BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.setPoint(1, 2, false);
        board.move(0, 1);
        //System.out.printf("moveControlled %d %d \n", board.getPointCount(0), board.getPointCount(1));
        return new int[] { board.getPointCount(0), board.getPointCount(1) };
    }

    // moving to own colour controlled spot
    public static int[] moveControlled(BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.setPoint(1, 1, false);
        board.move(0, 1);
        //System.out.printf("moveControlled %d %d \n", board.getPointCount(0), board.getPointCount(1));
        return new int[] { board.getPointCount(0), board.getPointCount(1) };
    }

    // empty space check
    public static int[] moveEmpty(BackgammonBoard board) {
        board.setPoint(0, 1, false);
        board.move(0, 1);   
        //System.out.printf("moveEmpty %d %d \n", board.getPointCount(0), board.getPointCount(1));
        return new int[] { board.getPointCount(0), board.getPointCount(1) };
    }

    public static boolean AssertEquals(int[] testing_value, int[] expected_value) {
        Boolean result = Arrays.equals(testing_value, expected_value) ? true : false;
        return result;
    }
}