package com.GamyA.expense_tracker.Expenses.Service;

import com.GamyA.expense_tracker.Expenses.Entities.Expense;
import com.GamyA.expense_tracker.Expenses.DTO.ExpenseSummaries;
import com.GamyA.expense_tracker.Expenses.Repository.ExpenseRepository;
import com.GamyA.expense_tracker.Expenses.DTO.UpdateExpense;
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

    public List<Expense> findExpenseByUsername(String username){
        return expenseRepository.findByUsername(username);
    }

    public List<Expense> findExpenseByUserMonthCategory(String username, List<String> category, List<String> month){
        return expenseRepository.findByUsernameCategoryMonth(username, category, month);
    }


    public List<ExpenseSummaries.ByCategory> getStatsCategory(String username){
        return expenseRepository.aggSummaryCategory(username);
    }

    public List<ExpenseSummaries.ByMonth> getStatsMonth(String username){
        return expenseRepository.aggSummaryMonth(username);
    }

    public List<ExpenseSummaries.ByCategoryAndMonth> getStatsCategoryAndMonth(String username){
        return expenseRepository.aggSummaryCategoryMonth(username);
    }

     /*

        FOR POST METHOD

    */
     public void saveExpense(Expense newExpense){
         expenseRepository.save(newExpense);
     }
     /*

        FOR PUT METHOD

    */
     public void updateExpense(UpdateExpense newExpense, Expense oldExpense){

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
    public void deleteExpense(long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        expenseRepository.delete(expense);
    }

    @Transactional
    public void deleteByCategoryAndUser(List<String> Category, String Username){
        List<Expense> allExpenses = expenseRepository.findByCategoryInAndUsername(Category, Username);

        if(allExpenses.isEmpty()){
            throw new IllegalArgumentException(" Either category or username is not found in database");
        }else{
            expenseRepository.deleteByCategoryInAndUsername(Category, Username);
        }
    }

    public Optional<Expense> findById(long id){
        return expenseRepository.findById(id);
    }







}
