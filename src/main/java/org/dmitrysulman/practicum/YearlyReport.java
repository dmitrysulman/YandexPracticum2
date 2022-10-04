package org.dmitrysulman.practicum;

import java.time.Month;
import java.time.Year;
import java.util.*;

public class YearlyReport {
    private final Map<Month, EarningsAndExpenses> earningsAndExpenses;
    private final Year year;

    public YearlyReport(String year) {
        this.year = Year.parse(year);
        this.earningsAndExpenses = new HashMap<>();
    }

    public void addEarnings(String month, int earnings) {
        earningsAndExpenses.computeIfAbsent(Month.of(Integer.parseInt(month)), k -> new EarningsAndExpenses()).setEarnings(earnings);
    }

    public void addExpenses(String month, int expenses) {
        earningsAndExpenses.computeIfAbsent(Month.of(Integer.parseInt(month)), k -> new EarningsAndExpenses()).setExpenses(expenses);
    }

    public String getYear() {
        return year.toString();
    }

    public Integer getMonthlyEarnings(int month) {
        return earningsAndExpenses.get(Month.of(month)).getEarnings();
    }

    public Integer getMonthlyExpenses(int month) {
        return earningsAndExpenses.get(Month.of(month)).getExpenses();
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

        @Override
        public String toString() {
            return "EarningsAndExpenses{" +
                    "earnings=" + earnings +
                    ", expenses=" + expenses +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "YearlyReport{" +
                "earningsAndExpenses=" + earningsAndExpenses +
                ", year=" + year +
                '}';
    }
}
