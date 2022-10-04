package org.dmitrysulman.practicum;

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

    public void addItem(String itemName, boolean isExpense, int quantity, int someOfOne) {
        items.add(new Item(itemName, isExpense, quantity, someOfOne));
    }

    public String getYearMonth() {
        return yearMonth.toString();
    }

    private record Item(String itemName, boolean isExpense, int quantity, int someOfOne) {
        @Override
        public String toString() {
            return "Item{" +
                    "itemName='" + itemName + '\'' +
                    ", isExpense=" + isExpense +
                    ", quantity=" + quantity +
                    ", someOfOne=" + someOfOne +
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
