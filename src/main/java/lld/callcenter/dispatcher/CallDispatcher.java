package lld.callcenter.dispatcher;

import lld.callcenter.model.Employee;
import lld.callcenter.pool.EmployeeUnavailableException;

public interface CallDispatcher
{
    Employee dispatchCall() throws EmployeeUnavailableException;
    void setNextDispatcher(CallDispatcher callDispatcher);
}
