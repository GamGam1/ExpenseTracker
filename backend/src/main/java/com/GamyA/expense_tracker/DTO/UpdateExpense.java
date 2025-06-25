package com.GamyA.expense_tracker.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;


public class UpdateExpense {

    @Pattern(
            regexp = "^[^/\\\\:*?\"<>|]*$",
            message = "Must not contain any of the following characters: / \\ : * ? \" < > |"
    )
    private String category;

    @Min(value = 0)
    private Double amount;

    @Pattern(regexp = "^(January|February|March|April|May|June|July|August|September|October|November|December)-\\d{4}$",
            message = "Month must be in the format 'Month-YYYY', e.g., 'April-2025'")
    private String month;

    @Pattern(
            regexp = "^[^/\\\\:*?\"<>|]*$",
            message = "Must not contain any of the following characters: / \\ : * ? \" < > |"
    )
    private String expenseName;

    public UpdateExpense() {
    }

    public UpdateExpense(String category, Double amount, String month, String expenseName) {
        this.category = category;
        this.amount = amount;
        this.month = month;
        this.expenseName = expenseName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }
}
