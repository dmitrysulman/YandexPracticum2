package org.dmitrysulman.practicum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MonthlyReport> monthlyReports = new ArrayList<>();
        YearlyReport yearlyReport = null;
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = Integer.parseInt(scanner.nextLine());
        while (userInput != 6) {
            switch (userInput) {
                case 1 -> {
                    for (int i = 1; i < 4; i++) {
                        monthlyReports.add(ReportScanner.scanFileToMonthlyReport("m.20210" + i + ".csv"));
                    }
                    if (!monthlyReports.contains(null)) {
                        System.out.println("Месячные отчеты успешно считаны");
                    }
                }
                case 2 -> {
                    yearlyReport = ReportScanner.scanFileToYearlyReport("y.2021.csv");
                    if (yearlyReport != null) {
                        System.out.println("Годовой отчет успешно считан");
                    }
                }
                case 3 -> {
                    if (monthlyReports.isEmpty()) {
                        System.out.println("Месячные отчеты не считаны");
                        break;
                    }
                    if (yearlyReport == null) {
                        System.out.println("Годовой отчет не считан");
                        break;
                    }
                    boolean isSuccess = true;
                    for (MonthlyReport monthlyReport : monthlyReports) {
                        if (monthlyReport != null) {
                            if (!monthlyReport.getTotalEarnings().equals(yearlyReport.getMonthlyEarnings(monthlyReport.getMonthInt())) ||
                                    !monthlyReport.getTotalExpenses().equals(yearlyReport.getMonthlyExpenses(monthlyReport.getMonthInt()))) {
                                System.out.println("Не совпадают данные в отчете за месяц: " + monthlyReport.getMonth());
                                isSuccess = false;
                            }
                        }
                    }
                    if (isSuccess) {
                        System.out.println("Данные в отчетах совпадают");
                    }
                }
                case 4 -> {
                    if (monthlyReports.isEmpty()) {
                        System.out.println("Месячные отчеты не считаны");
                        break;
                    }
                    for (MonthlyReport monthlyReport : monthlyReports) {
                        if (monthlyReport != null) {
                            System.out.println(monthlyReport.getMonthInfo());
                            System.out.println();
                        }
                    }
                }
                case 5 -> {
                    if (yearlyReport == null) {
                        System.out.println("Годовой отчет не считан");
                        break;
                    }
                    System.out.println(yearlyReport.getYearInfo());
                    System.out.println();
                }
                default -> System.out.println("Неизвестная команда");
            }
            printMenu();
            userInput = Integer.parseInt(scanner.nextLine());
        }
    }

    private static void printMenu() {
        System.out.println("Введите команду:");
        System.out.println("1.\tСчитать все месячные отчёты");
        System.out.println("2.\tСчитать годовой отчёт");
        System.out.println("3.\tСверить отчёты");
        System.out.println("4.\tВывести информацию о всех месячных отчётах");
        System.out.println("5.\tВывести информацию о годовом отчёте");
        System.out.println("6.\tВыйти из программы");
    }
}
