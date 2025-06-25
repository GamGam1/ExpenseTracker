package com.GamyA.expense_tracker.Repository;

import com.GamyA.expense_tracker.Entities.Expense;
import com.GamyA.expense_tracker.DTO.ExpenseSummaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);

    Optional<Expense> findByUserIdAndExpenseId(Long userId, long expenseId);

    @Query("SELECT e FROM Expense e WHERE e.userId = ?1 " +
            "AND (?2 IS NULL OR e.category IN ?2) AND (?3 IS NULL OR e.month IN ?3)")
    List<Expense> findByUsernameCategoryMonth(Long userId, List<String> Category, List<String> Month);

    @Query("SELECT new com.GamyA.expense_tracker.DTO.ExpenseSummaries$ByCategoryAndMonth(e.category, e.month, SUM(e.amount), AVG(e.amount))" +
            " FROM Expense e  WHERE e.userId = ?1 GROUP BY  e.category, e.month")
    List<ExpenseSummaries.ByCategoryAndMonth> aggSummaryCategoryMonth(Long userId);

    @Query("SELECT new com.GamyA.expense_tracker.DTO.ExpenseSummaries$ByCategory(e.category, SUM(e.amount), AVG(e.amount)) " +
            "FROM Expense e WHERE e.userId = ?1 GROUP BY  e.category")
    List<ExpenseSummaries.ByCategory> aggSummaryCategory(Long userId);

    @Query("SELECT new com.GamyA.expense_tracker.DTO.ExpenseSummaries$ByMonth(e.month, SUM(e.amount), AVG(e.amount)) " +
            "FROM Expense e WHERE e.userId = ?1 GROUP BY e.month")
    List<ExpenseSummaries.ByMonth> aggSummaryMonth(Long userId);

    List<Expense> findByCategoryInAndUserId(List<String> category, Long userId);

    void deleteByCategoryInAndUserId(List<String> category, Long userId);



}
