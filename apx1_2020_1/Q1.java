package apx1_2020_1;

import java.util.Scanner;

public class Q1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        while(!line.equals("FIM")) {
            String[] lineArray = line.split(" ");
            int indexOperators = 0, indexValues = 0;
            char[] operators = new char[lineArray.length];
            int[] values = new int[lineArray.length];
            for (int i = 0; i < lineArray.length; i++) {
                if (lineArray[i].equals("("));
                else if (lineArray[i].equals("+") ||
                        lineArray[i].equals("-") ||
                        lineArray[i].equals("*") ||
                        lineArray[i].equals("/") ||
                        lineArray[i].equals("%")) {
                    operators[indexOperators++] = lineArray[i].charAt(0); // chartAt returns a char instead of string
                } else if (lineArray[i].equals(")")) {
                    char operator = operators[--indexOperators];
                    int value = values[--indexValues];
                    if (operator == '+') value = values[--indexValues] + value;
                    else if (operator == '-') value = values[--indexValues] - value;
                    else if (operator == '*') value = values[--indexValues] * value;
                    else if (operator == '/') value = values[--indexValues] / value;
                    else if (operator == '%') value = values[--indexValues] % value;
                    values[indexValues++] = value;
                } else values[indexValues++] = Integer.parseInt(lineArray[i]);
            }
            int result = values [--indexValues];
            System.out.println(line + " = " + result);
            line = scan.nextLine();
        }
        scan.close();
    }
}

