package com.hhd.cellular.automaton;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 自动细胞机
 *
 * @author hengda.hu
 * @date 2020/12/16 13:41
 */
public class CellularAutomatonTest {
    private static int[][] oldStatus;
    private static int[][] newStatus;

    public static void main(String[] args) throws Exception {
        int width = 10;
        int height = 10;
        oldStatus = new int[height][width];
        newStatus = new int[height][width];

//        oldStatus = randomStart(width, height);
//        oldStatus = maichongxingStart();
//        oldStatus = huaxiangzheStart();
        oldStatus = qingliangjifeichuanStart();
        printStatus(oldStatus);
        for (;;) {
            oldStatus = automaton(oldStatus);
            System.out.println("-------------------------------------");
            printStatus(oldStatus);
            TimeUnit.SECONDS.sleep(2);
        }
    }

    /**
     * 随机
     * @param height
     * @param width
     * @return
     */
    private static int[][] randomStart(int height, int width) {
        Random random = new Random();
        int[][] startStatus = new int[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                startStatus[i][j] = random.nextInt(2);
            }
        }
        return startStatus;
    }

    private static int[][] automaton(int[][] oldStatus) {
        int[][] newStatus = new int[oldStatus.length][oldStatus[0].length];
        for (int i = 0; i < oldStatus.length; i++) {
            for (int j = 0; j < oldStatus[i].length; j++) {
                int sum = 0;
                if (i != 0) {
                    sum += oldStatus[i - 1][j];
                }
                if (j != 0) {
                    sum += oldStatus[i][j - 1];
                }
                if (i != oldStatus.length - 1) {
                    sum += oldStatus[i + 1][j];
                }
                if (j != oldStatus[i].length - 1) {
                    sum += oldStatus[i][j + 1];
                }
                if (i != 0 && j != 0) {
                    sum += oldStatus[i - 1][j - 1];
                }
                if (i != 0 && j != oldStatus[i].length - 1) {
                    sum += oldStatus[i - 1][j + 1];
                }
                if (i != oldStatus.length - 1 && j != 0) {
                    sum += oldStatus[i + 1][j - 1];
                }
                if (i != oldStatus.length - 1 && j != oldStatus[i].length - 1) {
                    sum += oldStatus[i + 1][j + 1];
                }
                if (oldStatus[i][j] == 1) {
                    if (sum < 2 || sum > 3) {
                        newStatus[i][j] = 0;
                    }
                    if (sum == 2 || sum == 3) {
                        newStatus[i][j] = 1;
                    }
                }
                if (oldStatus[i][j] == 0) {
                    if (sum == 3) {
                        newStatus[i][j] = 1;
                    } else {
                        newStatus[i][j] = 0;
                    }
                }
            }
        }
        return newStatus;
    }

    private static void printStatus(int[][] oldStatus) {
        for (int i = 0; i < oldStatus.length; i++) {
            for (int j = 0; j < oldStatus[i].length; j++) {
                if (oldStatus[i][j] == 0) {
                    System.out.print("· ");
                }
                if (oldStatus[i][j] == 1) {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 脉冲星
     * @return
     */
    private static int[][] maichongxingStart() {
        int width = 17;
        int height = 17;
        int[][] startStatus = new int[height][width];
        startStatus[4][2] = 1;
        startStatus[5][2] = 1;
        startStatus[11][2] = 1;
        startStatus[12][2] = 1;
        startStatus[5][3] = 1;
        startStatus[6][3] = 1;
        startStatus[10][3] = 1;
        startStatus[11][3] = 1;
        startStatus[2][4] = 1;
        startStatus[5][4] = 1;
        startStatus[7][4] = 1;
        startStatus[9][4] = 1;
        startStatus[11][4] = 1;
        startStatus[14][4] = 1;
        startStatus[2][5] = 1;
        startStatus[3][5] = 1;
        startStatus[4][5] = 1;
        startStatus[6][5] = 1;
        startStatus[7][5] = 1;
        startStatus[9][5] = 1;
        startStatus[10][5] = 1;
        startStatus[12][5] = 1;
        startStatus[13][5] = 1;
        startStatus[14][5] = 1;
        startStatus[3][6] = 1;
        startStatus[5][6] = 1;
        startStatus[7][6] = 1;
        startStatus[9][6] = 1;
        startStatus[11][6] = 1;
        startStatus[13][6] = 1;
        startStatus[4][7] = 1;
        startStatus[5][7] = 1;
        startStatus[6][7] = 1;
        startStatus[10][7] = 1;
        startStatus[11][7] = 1;
        startStatus[12][7] = 1;

        startStatus[4][14] = 1;
        startStatus[5][14] = 1;
        startStatus[11][14] = 1;
        startStatus[12][14] = 1;
        startStatus[5][13] = 1;
        startStatus[6][13] = 1;
        startStatus[10][13] = 1;
        startStatus[11][13] = 1;
        startStatus[2][12] = 1;
        startStatus[5][12] = 1;
        startStatus[7][12] = 1;
        startStatus[9][12] = 1;
        startStatus[11][12] = 1;
        startStatus[14][12] = 1;
        startStatus[2][11] = 1;
        startStatus[3][11] = 1;
        startStatus[4][11] = 1;
        startStatus[6][11] = 1;
        startStatus[7][11] = 1;
        startStatus[9][11] = 1;
        startStatus[10][11] = 1;
        startStatus[12][11] = 1;
        startStatus[13][11] = 1;
        startStatus[14][11] = 1;
        startStatus[3][10] = 1;
        startStatus[5][10] = 1;
        startStatus[7][10] = 1;
        startStatus[9][10] = 1;
        startStatus[11][10] = 1;
        startStatus[13][10] = 1;
        startStatus[4][9] = 1;
        startStatus[5][9] = 1;
        startStatus[6][9] = 1;
        startStatus[10][9] = 1;
        startStatus[11][9] = 1;
        startStatus[12][9] = 1;
        return startStatus;
    }

    /**
     * 滑翔者
     * @return
     */
    private static int[][] huaxiangzheStart() {
        int width = 10;
        int height = 10;
        int[][] startStatus = new int[height][width];
        startStatus[0][2] = 1;
        startStatus[1][0] = 1;
        startStatus[1][2] = 1;
        startStatus[2][1] = 1;
        startStatus[2][2] = 1;

        return startStatus;
    }

    /**
     * 轻量级飞船
     * @return
     */
    private static int[][] qingliangjifeichuanStart() {
        int width = 10;
        int height = 6;
        int[][] startStatus = new int[height][width];
        startStatus[0][1] = 1;
        startStatus[0][2] = 1;
        startStatus[1][0] = 1;
        startStatus[1][1] = 1;
        startStatus[1][2] = 1;
        startStatus[1][3] = 1;
        startStatus[2][0] = 1;
        startStatus[2][1] = 1;
        startStatus[2][3] = 1;
        startStatus[2][4] = 1;
        startStatus[3][2] = 1;
        startStatus[3][3] = 1;

        return startStatus;
    }
}
