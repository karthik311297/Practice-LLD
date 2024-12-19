package lld.callcenter.dispatcher;

import lld.callcenter.model.Employee;
import lld.callcenter.pool.EmployeePool;
import lld.callcenter.pool.EmployeeUnavailableException;

public class DirectorCallDispatcher implements CallDispatcher {

    @Override
    public Employee dispatchCall() throws EmployeeUnavailableException {
        return EmployeePool.getInstance().getFirstAvailableDirector();
    }

    @Override
    public void setNextDispatcher(CallDispatcher callDispatcher) {

    }
}
