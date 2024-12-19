package lld.callcenter.dispatcher;

import lld.callcenter.model.Employee;
import lld.callcenter.pool.EmployeePool;
import lld.callcenter.pool.EmployeeUnavailableException;

public class RespondentCallDispatcher implements CallDispatcher {

    private CallDispatcher nextDispatcher;

    @Override
    public Employee dispatchCall() throws EmployeeUnavailableException{
        try {
            return EmployeePool.getInstance().getFirstAvailableRespondent();
        } catch (EmployeeUnavailableException e) {
            return this.nextDispatcher.dispatchCall();
        }
    }

    @Override
    public void setNextDispatcher(CallDispatcher nextDispatcher) {
        this.nextDispatcher = nextDispatcher;
    }
}
