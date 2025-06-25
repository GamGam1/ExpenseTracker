package com.GamyA.expense_tracker.Controller;

import com.GamyA.expense_tracker.Entities.Expense;
import com.GamyA.expense_tracker.DTO.ExpenseSummaries;
import com.GamyA.expense_tracker.Service.ExpenseService;
import com.GamyA.expense_tracker.DTO.UpdateExpense;
import com.GamyA.expense_tracker.Service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/expenses")
public class ExpenseTrackerController {

    private static ExpenseService expenseService;
    private static JWTService jwtService;

    @Autowired
    public ExpenseTrackerController(ExpenseService expenseService, JWTService jwtService) {
        ExpenseTrackerController.expenseService = expenseService;
        ExpenseTrackerController.jwtService = jwtService;

    }

    /*

        GET METHODS

    */

    @GetMapping(value="/userExpenses")
    public List<Expense> getUserExpenses(HttpServletRequest request){
        Long userId = jwtService.getUserId(request);
        return expenseService.findExpenseByUsername(userId);
    }
    @GetMapping(value="/userExpenses/filter")
    public List<Expense> getUserExpensesFilterByMonthCategory(@RequestParam(required = false) List<String> category,
                                                              @RequestParam(required = false) List<String> month,
                                                              HttpServletRequest request){
        Long userId = jwtService.getUserId(request);
        return expenseService.findExpenseByUserMonthCategory(userId,category,month);
    }

    @GetMapping(value="/userExpenses/stats/category")
    public  List<ExpenseSummaries.ByCategory> getStatsCategory(HttpServletRequest request){
        Long userId = jwtService.getUserId(request);
        return expenseService.getStatsCategory(userId);
    }

    @GetMapping(value="/userExpenses/stats/month")
    public  List<ExpenseSummaries.ByMonth> getStatsMonth(HttpServletRequest request){
        Long userId = jwtService.getUserId(request);
        return expenseService.getStatsMonth(userId);
    }

    @GetMapping(value="/userExpenses/stats/both")
    public  List<ExpenseSummaries.ByCategoryAndMonth> getStatsCategoryAndMonth(HttpServletRequest request){
        Long userId = jwtService.getUserId(request);
        return expenseService.getStatsCategoryAndMonth(userId);
    }

     /*

        POST METHODS

    */

    @PostMapping(value = "/save")
    public void saveExpense(@Valid @RequestBody Expense newExpense, HttpServletRequest request){
        //need to add in userid
        Long userId = jwtService.getUserId(request);
        expenseService.saveExpense(newExpense, userId);
    }

     /*

        PUT METHODS

    */

    @PutMapping(value = "/update/{id}")
    public void updateExpense(@Valid @RequestBody UpdateExpense newExpense, @PathVariable long id, HttpServletRequest request) {
        //check if the expense belongs to the user
        Long userId = jwtService.getUserId(request);
        expenseService.updateExpense(newExpense, id, userId);
    }

    /*

        DELETE METHODS

    */

    @DeleteMapping(value = "/deleteID/{id}")
    public void deleteExpense(@PathVariable long id, HttpServletRequest request){
        Long userId = jwtService.getUserId(request);
        expenseService.deleteExpense(id, userId);
    }

    @DeleteMapping(value = "/deleteCategory")
    public void deleteExpenseByCategory(@RequestParam List<String> category, HttpServletRequest request){
        Long userId = jwtService.getUserId(request);
        expenseService.deleteByCategoryAndUser(category,userId);
    }




}
