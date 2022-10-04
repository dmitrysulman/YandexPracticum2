package org.dmitrysulman.practicum;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    private final List<Item> items;
    private final YearMonth yearMonth;

    public MonthlyReport(String yearMonth) {
        this.yearMonth = YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyyMM"));
        this.items = new ArrayList<>();
    }

    public void addItem(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        items.add(new Item(itemName, isExpense, quantity, sumOfOne));
    }

    public int getMonthInt() {
        return yearMonth.getMonth().getValue();
    }

    public Month getMonth() {
        return yearMonth.getMonth();
    }

    public Integer getTotalEarnings() {
        return items.stream()
                .filter(item -> !item.isExpense)
                .mapToInt(item -> item.sumOfOne * item.quantity)
                .sum();
    }

    public Integer getTotalExpenses() {
        return items.stream()
                .filter(item -> item.isExpense)
                .mapToInt(item -> item.sumOfOne * item.quantity)
                .sum();
    }

    private record Item(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        @Override
        public String toString() {
            return "Item{" +
                    "itemName='" + itemName + '\'' +
                    ", isExpense=" + isExpense +
                    ", quantity=" + quantity +
                    ", sumOfOne=" + sumOfOne +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
                "items=" + items +
                ", month='" + yearMonth + '\'' +
                '}';
    }
}
