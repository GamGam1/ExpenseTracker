package com.GamyA.expense_tracker.Service;

import com.GamyA.expense_tracker.Entities.Expense;
import com.GamyA.expense_tracker.DTO.ExpenseSummaries;
import com.GamyA.expense_tracker.Repository.ExpenseRepository;
import com.GamyA.expense_tracker.DTO.UpdateExpense;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private static ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        ExpenseService.expenseRepository = expenseRepository;
    }

    /*

    Methods

    */

    /*

        FOR GET METHOD

    */

    public List<Expense> findExpenseByUsername(Long userId){
        List<Expense> result = expenseRepository.findByUserId(userId);
        if(result.isEmpty()){
            throw new RuntimeException("no expenses found by the user");
        }
        return result;
    }

    public List<Expense> findExpenseByUserMonthCategory(Long userId, List<String> category, List<String> month){
        List<Expense>  result = expenseRepository.findByUsernameCategoryMonth(userId, category, month);
        if(result.isEmpty()){
            throw new RuntimeException("no expenses found by the user or filter");
        }
        return result;
    }


    public List<ExpenseSummaries.ByCategory> getStatsCategory(Long userId){
        List<ExpenseSummaries.ByCategory>  result = expenseRepository.aggSummaryCategory(userId);
        if(result.isEmpty()){
            throw new RuntimeException("no expenses found by the user");
        }
        return result;
    }

    public List<ExpenseSummaries.ByMonth> getStatsMonth(Long userId){
        List<ExpenseSummaries.ByMonth>  result = expenseRepository.aggSummaryMonth(userId);
        if(result.isEmpty()){
            throw new RuntimeException("no expenses found by the user or filter");
        }
        return result;
    }

    public List<ExpenseSummaries.ByCategoryAndMonth> getStatsCategoryAndMonth(Long userId){
        List<ExpenseSummaries.ByCategoryAndMonth>  result = expenseRepository.aggSummaryCategoryMonth(userId);
        if(result.isEmpty()){
            throw new RuntimeException("no expenses found by the user or filter");
        }
        return result;
    }

     /*

        FOR POST METHOD

    */
     public void saveExpense(Expense newExpense, Long userId){
         newExpense.setUserId(userId);

         expenseRepository.save(newExpense);
     }
     /*

        FOR PUT METHOD

    */
     public void updateExpense(UpdateExpense newExpense, long id, Long userId){

         Expense oldExpense = expenseRepository.findByUserIdAndId(userId,id).orElseThrow(() -> new IllegalArgumentException("Expense not found"));

         if(newExpense.getAmount()  != null){
             oldExpense.setAmount(newExpense.getAmount());
         }
         if(newExpense.getMonth()  != null){
             oldExpense.setMonth(newExpense.getMonth());
         }
         if(newExpense.getCategory()  != null){
             oldExpense.setCategory(newExpense.getCategory());
         }
         if(newExpense.getExpenseName()  != null){
             oldExpense.setExpenseName(newExpense.getExpenseName());
         }

         expenseRepository.save(oldExpense);
     }
     /*

        FOR DELETE METHOD

    */

    @Transactional
    public void deleteExpense(long id, Long userId){
        Expense expense = expenseRepository.findByUserIdAndId(userId,id).orElseThrow(() -> new RuntimeException("Not found"));
        expenseRepository.delete(expense);
    }

    @Transactional
    public void deleteByCategoryAndUser(List<String> Category, Long userId){
        List<Expense> allExpenses = expenseRepository.findByCategoryInAndUserId(Category, userId);

        if(allExpenses.isEmpty()){
            throw new IllegalArgumentException(" Either category or username is not found in database");
        }else{
            expenseRepository.deleteByCategoryInAndUserId(Category, userId);
        }
    }







}
