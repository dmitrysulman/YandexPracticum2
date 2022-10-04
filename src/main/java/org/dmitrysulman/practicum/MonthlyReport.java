package org.dmitrysulman.practicum;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
                .mapToInt(Item::getTotalCost)
                .sum();
    }

    public Integer getTotalExpenses() {
        return items.stream()
                .filter(Item::isExpense)
                .mapToInt(Item::getTotalCost)
                .sum();
    }

    public String getMonthInfo() {
        StringBuilder result = new StringBuilder();
        result.append(yearMonth);
        Item maxEarning = getMaxEarning();
        Item maxExpense = getMaxExpense();
        result
                .append("\nСамый прибыльный товар: ")
                .append(maxEarning.itemName)
                .append(", сумма: ")
                .append(maxEarning.getTotalCost())
                .append("\nСамая большая трата: ")
                .append(maxExpense.itemName)
                .append(", сумма: ")
                .append(maxExpense.getTotalCost());
        return result.toString();
    }

    private Item getMaxExpense() {
        return items.stream()
                .filter(Item::isExpense)
                .max(Comparator.comparingInt(Item::getTotalCost))
                .orElse(null);
    }

    private Item getMaxEarning() {
        return items.stream()
                .filter(item -> !item.isExpense)
                .max(Comparator.comparingInt(Item::getTotalCost))
                .orElse(null);
    }

    private record Item(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        public int getTotalCost() {
            return quantity * sumOfOne;
        }
    }
}
