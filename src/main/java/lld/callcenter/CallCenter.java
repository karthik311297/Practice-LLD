package lld.callcenter;

import lld.callcenter.model.Call;
import lld.callcenter.model.Director;
import lld.callcenter.model.Manager;
import lld.callcenter.model.Respondent;
import lld.callcenter.pool.EmployeePool;
import lld.callcenter.pool.EmployeeUnavailableException;

import java.util.Arrays;
import java.util.List;

public class CallCenter {
    public static void main(String[] args) throws EmployeeUnavailableException {
        /*
         *Imagine you have a call center with three levels of employees: respondent, manager and director.
         *An incoming telephone call must be first allocated to a respondent who is free.
         *If the respondent cant handle the call, he or she must escalate the call to a manager.
         *If the manager is not free or not able to handle it, then the call should be escalated to a director.
         *Design the classes and data structure for this problem. Implement a method `dispatchCall()` which assigns a call to the first available employees.
         */

        /*
        Call comes, then go to entry point which routes the call to an employee/agent who is free, agent becomes engaged.
        Then call will be ended and the agent becomes free again to take a call.

        Call

        Employee : Respondent, Manager, Director

        CallCenter has CallController

        CallController has CallDispatcher and EmployeePool
            assignCall() -> dispatches call and assigns employee to a call
            endCall() -> returns an call engaged employee to pool of free employees

        CallDispatcher : RespondentCallDispatcher -> ManagerCallDispatcher -> DirectorCallDispatcher
            dispatchCall(), returns the employee based on level from pool of employees

        EmployeePool

         */
        List<Respondent> respondents = Arrays.asList(new Respondent("1", "res1"), new Respondent("2", "res2"));
        List<Manager> managers = Arrays.asList(new Manager("3", "man1"), new Manager("4", "man2"));
        List<Director> directors = Arrays.asList(new Director("5", "dir1"), new Director("6", "dir2"));
        CallController callController = CallController.getInstance();
        EmployeePool.getInstance().initializeEmployeesPool(respondents, managers, directors);

        Call call = new Call();
        call.setNumber("90909");
        call = callController.assignCall(call);
        Call call2 = new Call();
        call2.setNumber("90908");
        call2 = callController.assignCall(call2);
        Call call3 = new Call();
        call3.setNumber("90907");
        call3 = callController.assignCall(call3);
        callController.endCall(call);
    }
}
