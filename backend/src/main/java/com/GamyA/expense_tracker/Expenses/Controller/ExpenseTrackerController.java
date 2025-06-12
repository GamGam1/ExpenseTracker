package com.GamyA.expense_tracker.Expenses.Controller;

import com.GamyA.expense_tracker.Expenses.Entities.Expense;
import com.GamyA.expense_tracker.Expenses.DTO.ExpenseSummaries;
import com.GamyA.expense_tracker.Expenses.Service.ExpenseService;
import com.GamyA.expense_tracker.Expenses.DTO.UpdateExpense;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ExpenseTrackerController {

    private static ExpenseService expenseService;

    @Autowired
    public ExpenseTrackerController(ExpenseService expenseService) {
        ExpenseTrackerController.expenseService = expenseService;
    }

    /*

        GET METHODS

    */

    @GetMapping(value="/api/{user}")
    public List<Expense> getUserExpenses(@PathVariable String user){
        return expenseService.findExpenseByUsername(user);
    }
    @GetMapping(value="/api/{user}/filter")
    public List<Expense> getUserExpensesFilterByMonthCategory(@PathVariable String user,
                                                              @RequestParam(required = false) List<String> category,
                                                              @RequestParam(required = false) List<String> month){

        return expenseService.findExpenseByUserMonthCategory(user,category,month);
    }

    @GetMapping(value="/api/{user}/stats/category")
    public  List<ExpenseSummaries.ByCategory> getStatsCategory(@PathVariable String user){

        return expenseService.getStatsCategory(user);
    }

    @GetMapping(value="/api/{user}/stats/month")
    public  List<ExpenseSummaries.ByMonth> getStatsMonth(@PathVariable String user){

        return expenseService.getStatsMonth(user);
    }

    @GetMapping(value="/api/{user}/stats/both")
    public  List<ExpenseSummaries.ByCategoryAndMonth> getStatsCategoryAndMonth(@PathVariable String user){

        return expenseService.getStatsCategoryAndMonth(user);
    }

     /*

        POST METHODS

    */

    @PostMapping(value = "/api/save")
    public void saveExpense(@Valid @RequestBody Expense newExpense){
        expenseService.saveExpense(newExpense);
    }

     /*

        PUT METHODS

    */

    @PutMapping(value = "/api/update/{id}")
    public void updateExpense(@Valid @RequestBody UpdateExpense newExpense, @PathVariable long id) {
        expenseService.updateExpense(newExpense, id);
    }

    /*

        DELETE METHODS

    */

    @DeleteMapping(value = "/api/deleteID/{id}")
    public void deleteExpense(@PathVariable long id){
        expenseService.deleteExpense(id);
    }

    @DeleteMapping(value = "/api/deleteCategory/{user}")
    public void deleteExpenseByCategory(@PathVariable String user,@RequestParam List<String> category){
        expenseService.deleteByCategoryAndUser(category,user);
    }




}
