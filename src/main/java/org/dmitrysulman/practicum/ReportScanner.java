package org.dmitrysulman.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReportScanner {
    public static MonthlyReport scanFileToMonthlyReport(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            MonthlyReport monthlyReport = new MonthlyReport(filename.replace("m.", "").replace(".csv", ""));
            //first line is a header
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineContents = line.split(",");
                monthlyReport.addItem(lineContents[0], Boolean.parseBoolean(lineContents[1]), Integer.parseInt(lineContents[2]), Integer.parseInt(lineContents[3]));
            }
            return monthlyReport;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static YearlyReport scanFileToYearlyReport(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            YearlyReport yearlyReport = new YearlyReport(filename.replace("y.", "").replace(".csv", ""));
            //first line is a header
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineContents = line.split(",");
                boolean isExpense = Boolean.parseBoolean(lineContents[2]);
                if (isExpense) {
                    yearlyReport.addExpenses(lineContents[0], Integer.parseInt(lineContents[1]));
                } else {
                    yearlyReport.addEarnings(lineContents[0], Integer.parseInt(lineContents[1]));
                }
            }
            return yearlyReport;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
