package com.GamyA.expense_tracker.Expenses.Repository;

import com.GamyA.expense_tracker.Expenses.Entities.Expense;
import com.GamyA.expense_tracker.Expenses.DTO.ExpenseSummaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

    List<Expense> findByUsername(String Username);

    @Query("SELECT e FROM Expense e WHERE e.username = ?1 " +
            "AND (?2 IS NULL OR e.category IN ?2) AND (?3 IS NULL OR e.month IN ?3)")
    List<Expense> findByUsernameCategoryMonth(String Username, List<String> Category, List<String> Month);

    @Query("SELECT new com.GamyA.expense_tracker.Expenses.DTO.ExpenseSummaries$ByCategoryAndMonth(e.category, e.month, SUM(e.amount), AVG(e.amount))" +
            " FROM Expense e  WHERE e.username = ?1 GROUP BY  e.category, e.month")
    List<ExpenseSummaries.ByCategoryAndMonth> aggSummaryCategoryMonth(String Username);

    @Query("SELECT new com.GamyA.expense_tracker.Expenses.DTO.ExpenseSummaries$ByCategory(e.category, SUM(e.amount), AVG(e.amount)) " +
            "FROM Expense e WHERE e.username = ?1 GROUP BY  e.category")
    List<ExpenseSummaries.ByCategory> aggSummaryCategory(String Username);

    @Query("SELECT new com.GamyA.expense_tracker.Expenses.DTO.ExpenseSummaries$ByMonth(e.month, SUM(e.amount), AVG(e.amount)) " +
            "FROM Expense e WHERE e.username = ?1 GROUP BY e.month")
    List<ExpenseSummaries.ByMonth> aggSummaryMonth(String Username);

    List<Expense> findByCategoryInAndUsername(List<String> category, String username);

    void deleteByCategoryInAndUsername(List<String> category, String username);


}
