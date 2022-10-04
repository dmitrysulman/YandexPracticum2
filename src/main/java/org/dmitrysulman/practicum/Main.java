package org.dmitrysulman.practicum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MonthlyReport> monthlyReports = new ArrayList<>();
        YearlyReport yearlyReport;
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 6) {
            switch (userInput) {
                case 1 -> {
                    for (int i = 1; i < 4; i++) {
                        monthlyReports.add(ReportScanner.scanFileToMonthlyReport("m.20210" + i + ".csv"));
                    }
                    System.out.println(monthlyReports);
                }
                case 2 -> {
                    yearlyReport = ReportScanner.scanFileToYearlyReport("y.2021.csv");
                    System.out.println(yearlyReport);
                }
            }
            printMenu();
            userInput = scanner.nextInt();
        }





    }

    private static void printMenu() {
        System.out.println("1.\tСчитать все месячные отчёты");
        System.out.println("2.\tСчитать годовой отчёт");
        System.out.println("3.\tСверить отчёты");
        System.out.println("4.\tВывести информацию о всех месячных отчётах");
        System.out.println("5.\tВывести информацию о годовом отчёте");
        System.out.println("6.\tВыйти из программы");
    }
}
