package com.GamyA.expense_tracker.Expenses.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name = "Expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotBlank(message = "You must enter a username")
    private String username;

    @NotBlank(message = "You must enter a name for the expense")
    @Pattern(
            regexp = "^[^/\\\\:*?\"<>|]*$",
            message = "Must not contain any of the following characters: / \\ : * ? \" < > |"
    )
    private String expenseName;

    @Pattern(
            regexp = "^[^/\\\\:*?\"<>|]*$",
            message = "Must not contain any of the following characters: / \\ : * ? \" < > |"
    )
    private String category;

    @NotNull(message = "amount is required")
    @Min(value = 0, message = "amount must be positive")
    private Double amount;

    @NotNull(message = "month must not be blank")
    @Pattern(regexp = "^(January|February|March|April|May|June|July|August|September|October|November|December)-\\d{4}$",
            message = "Month must be in the format 'Month-YYYY', e.g., 'April-2025'")
    private String month;

    public Expense() {
    }

    @Autowired
    public Expense(String username, String category, Double amount, String month, String expenseName){
        this.username = username;
        if (category == null){
            this.category = "General";
        }else{
            this.category = category;
        }
        this.amount = amount;
        this.month =month;
        this.expenseName = expenseName;
    }

    public long getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public String getCategory() {
        return category;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMonth() {
        return month;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
