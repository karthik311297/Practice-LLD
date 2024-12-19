package lld.callcenter;

import lld.callcenter.dispatcher.CallDispatcher;
import lld.callcenter.dispatcher.DirectorCallDispatcher;
import lld.callcenter.dispatcher.ManagerCallDispatcher;
import lld.callcenter.dispatcher.RespondentCallDispatcher;
import lld.callcenter.model.*;
import lld.callcenter.pool.EmployeePool;
import lld.callcenter.pool.EmployeeUnavailableException;

public class CallController {
    private static final CallController INSTANCE = new CallController();
    private final CallDispatcher callDispatcher;

    public static CallController getInstance() {
        return INSTANCE;
    }

    private CallController() {
        this.callDispatcher = setupCallDispatchers();
    }

    private CallDispatcher setupCallDispatchers() {
        RespondentCallDispatcher respondentCallDispatcher = new RespondentCallDispatcher();
        ManagerCallDispatcher managerCallDispatcher = new ManagerCallDispatcher();
        DirectorCallDispatcher directorCallDispatcher = new DirectorCallDispatcher();
        respondentCallDispatcher.setNextDispatcher(managerCallDispatcher);
        managerCallDispatcher.setNextDispatcher(directorCallDispatcher);
        return respondentCallDispatcher;
    }

    public Call assignCall(Call call) throws EmployeeUnavailableException {
        Employee e = this.callDispatcher.dispatchCall();
        call.setEmployee(e);
        return call;
    }

    public void endCall(Call call) {
        Employee e = call.getEmployee();
        if (e instanceof Respondent) EmployeePool.getInstance().freeRespondent((Respondent) e);
        else if (e instanceof Manager) EmployeePool.getInstance().freeManager((Manager) e);
        else EmployeePool.getInstance().freeDirector((Director) e);
    }
}
