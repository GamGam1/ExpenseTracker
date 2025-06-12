package com.GamyA.expense_tracker.Expenses.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExpenseSummaries {

    public static class ByCategory {
        private String category;
        private Double totalAmount;
        private Double avgAmount;

        public ByCategory(String category, Double totalAmount, Double avgAmount) {

            double roundedAvg = new BigDecimal(avgAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();

            this.category = category;
            this.totalAmount = totalAmount;
            this.avgAmount = roundedAvg;
        }

        public Double getAvgAmount() {
            return avgAmount;
        }

        public void setAvgAmount(Double avgAmount) {
            this.avgAmount = avgAmount;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }

    public static class ByMonth {
        private String Month;
        private Double totalAmount;
        private Double avgAmount;

        public ByMonth(String date, Double totalAmount, Double avgAmount) {

            double roundedAvg = new BigDecimal(avgAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();

            this.Month = date;
            this.totalAmount = totalAmount;
            this.avgAmount = roundedAvg;
        }

        public String getMonth() {
            return Month;
        }

        public void setMonth(String month) {
            this.Month = month;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Double getAvgAmount() {
            return avgAmount;
        }

        public void setAvgAmount(Double avgAmount) {
            this.avgAmount = avgAmount;
        }
    }

    public static class ByCategoryAndMonth {
        private String category;
        private String month;
        private Double totalAmount;
        private Double avgAmount;

        public ByCategoryAndMonth(String category, String month, Double totalAmount, Double avgAmount) {

            double roundedAvg = new BigDecimal(avgAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();

            this.category = category;
            this.month = month;
            this.totalAmount = totalAmount;
            this.avgAmount = roundedAvg;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Double getAvgAmount() {
            return avgAmount;
        }

        public void setAvgAmount(Double avgAmount) {
            this.avgAmount = avgAmount;
        }
    }
}
