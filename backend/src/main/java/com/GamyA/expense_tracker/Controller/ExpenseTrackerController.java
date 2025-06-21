package com.GamyA.expense_tracker.Controller;

import com.GamyA.expense_tracker.Entities.Expense;
import com.GamyA.expense_tracker.DTO.ExpenseSummaries;
import com.GamyA.expense_tracker.Service.ExpenseService;
import com.GamyA.expense_tracker.DTO.UpdateExpense;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/expenses")
public class ExpenseTrackerController {

    private static ExpenseService expenseService;

    @Autowired
    public ExpenseTrackerController(ExpenseService expenseService) {
        ExpenseTrackerController.expenseService = expenseService;
    }

    /*

        GET METHODS

    */

    @GetMapping(value="/userExpenses")
    public List<Expense> getUserExpenses(){
        Long userId = null;
        return expenseService.findExpenseByUsername(userId);
    }
    @GetMapping(value="/userExpenses/filter")
    public List<Expense> getUserExpensesFilterByMonthCategory(@RequestParam(required = false) List<String> category,
                                                              @RequestParam(required = false) List<String> month){
        Long userId = null;
        return expenseService.findExpenseByUserMonthCategory(userId,category,month);
    }

    @GetMapping(value="/userExpenses/stats/category")
    public  List<ExpenseSummaries.ByCategory> getStatsCategory(){
        Long userId = null;
        return expenseService.getStatsCategory(userId);
    }

    @GetMapping(value="/userExpenses/stats/month")
    public  List<ExpenseSummaries.ByMonth> getStatsMonth(){
        Long userId = null;
        return expenseService.getStatsMonth(userId);
    }

    @GetMapping(value="/userExpenses/stats/both")
    public  List<ExpenseSummaries.ByCategoryAndMonth> getStatsCategoryAndMonth(){
        Long userId = null;
        return expenseService.getStatsCategoryAndMonth(userId);
    }

     /*

        POST METHODS

    */

    @PostMapping(value = "/save")
    public void saveExpense(@Valid @RequestBody Expense newExpense){
        //need to add in userid
        expenseService.saveExpense(newExpense);
    }

     /*

        PUT METHODS

    */

    @PutMapping(value = "/update/{id}")
    public void updateExpense(@Valid @RequestBody UpdateExpense newExpense, @PathVariable long id) {
        //check if the expense belongs to the user
        expenseService.updateExpense(newExpense, id);
    }

    /*

        DELETE METHODS

    */

    @DeleteMapping(value = "/deleteID/{id}")
    public void deleteExpense(@PathVariable long id){
        expenseService.deleteExpense(id);
    }

    @DeleteMapping(value = "/deleteCategory")
    public void deleteExpenseByCategory(@RequestParam List<String> category){
        Long userId = null;
        expenseService.deleteByCategoryAndUser(category,userId);
    }




}
