package com.xueyongzhang.team.ui;

import java.util.*;

public class TSUtility {
    private static Scanner scanner = new Scanner(System.in);

	public static char readMenuSelection() {
        char c;
        while (true) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' &&
                c != '3' && c != '4') {
                System.out.print("Wrong selection(1-4), please type again:");
            } else break;
        }
        return c;
    }

    public static void readReturn() {
        System.out.print("Press enter to continue...");
        readKeyBoard(0, true);
    }

    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Wrong number, please type again:");
            }
        }
        return n;
    }

    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Wrong selection(Y or N), please type again:");
            }
        }
        return c;
    }

    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) {return line;}
                else {
                    System.out.print("You can't type nothing, please type again:");
                    return readKeyBoard(limit, blankReturn);
                }
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("The length can't exceed" + limit + ", please type again:");
                return readKeyBoard(limit, blankReturn);
            }

        return line;
    }
}

