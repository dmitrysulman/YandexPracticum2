package org.dmitrysulman.practicum;

import java.time.Month;
import java.time.Year;
import java.util.*;

public class YearlyReport {
    private final Map<Month, EarningsAndExpenses> monthEarningsAndExpensesMap;
    private final Year year;

    public YearlyReport(String year) {
        this.year = Year.parse(year);
        this.monthEarningsAndExpensesMap = new TreeMap<>();
    }

    public void addEarnings(String month, int earnings) {
        monthEarningsAndExpensesMap.computeIfAbsent(Month.of(Integer.parseInt(month)), k -> new EarningsAndExpenses())
                .setEarnings(earnings);
    }

    public void addExpenses(String month, int expenses) {
        monthEarningsAndExpensesMap.computeIfAbsent(Month.of(Integer.parseInt(month)), k -> new EarningsAndExpenses())
                .setExpenses(expenses);
    }

    public String getYear() {
        return year.toString();
    }

    public String getYearInfo() {
        StringBuilder result = new StringBuilder();
        result.append(year);
        for (Map.Entry<Month, EarningsAndExpenses> entry : monthEarningsAndExpensesMap.entrySet()) {
            EarningsAndExpenses earningsAndExpenses = entry.getValue();
            result
                    .append("\nМесяц: ")
                    .append(entry.getKey())
                    .append(", прибыль: ")
                    .append(earningsAndExpenses.getRevenue());
        }
        result
                .append("\nCредний расход: ")
                .append(getAverageExpenses())
                .append(", средний доход: ")
                .append(getAverageEarnings());
        return result.toString();
    }

    public double getAverageEarnings() {
        return monthEarningsAndExpensesMap.values().stream()
                .mapToInt(EarningsAndExpenses::getEarnings)
                .average()
                .orElse(0.0);
    }

    public double getAverageExpenses() {
        return monthEarningsAndExpensesMap.values().stream()
                .mapToInt(EarningsAndExpenses::getExpenses)
                .average()
                .orElse(0.0);
    }

    public Integer getMonthlyEarnings(int month) {
        return monthEarningsAndExpensesMap.get(Month.of(month)).getEarnings();
    }

    public Integer getMonthlyExpenses(int month) {
        return monthEarningsAndExpensesMap.get(Month.of(month)).getExpenses();
    }

    private static final class EarningsAndExpenses {
        private Integer earnings;
        private Integer expenses;

        public EarningsAndExpenses() {
        }

        public Integer getEarnings() {
            return earnings;
        }

        public void setEarnings(Integer earnings) {
            this.earnings = earnings;
        }

        public Integer getExpenses() {
            return expenses;
        }

        public void setExpenses(Integer expenses) {
            this.expenses = expenses;
        }

        public int getRevenue() {
            return earnings - expenses;
        }
    }
}
