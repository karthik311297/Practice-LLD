package lld.callcenter.pool;

import lld.callcenter.model.Director;
import lld.callcenter.model.Manager;
import lld.callcenter.model.Respondent;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class EmployeePool {

    private final ReentrantLock respondentsLock = new ReentrantLock();
    private final ReentrantLock managersLock = new ReentrantLock();
    private final ReentrantLock directorsLock = new ReentrantLock();
    private static final EmployeePool INSTANCE = new EmployeePool();
    private Deque<Respondent> availableRespondents;
    private Set<Respondent> busyRespondents;
    private Deque<Manager> availableManagers;
    private Set<Manager> busyManagers;
    private Deque<Director> availableDirectors;
    private Set<Director> busyDirectors;

    private EmployeePool() {
        availableRespondents = new LinkedList<>();
        busyRespondents = new HashSet<>();
        availableManagers = new LinkedList<>();
        busyManagers = new HashSet<>();
        availableDirectors = new LinkedList<>();
        busyDirectors = new HashSet<>();
    }

    public static EmployeePool getInstance() {
        return INSTANCE;
    }

    public Respondent getFirstAvailableRespondent() throws EmployeeUnavailableException {
        if (availableRespondents.isEmpty()) throw new EmployeeUnavailableException("");
        try {
            respondentsLock.lock();
            Respondent r = availableRespondents.pollFirst();
            busyRespondents.add(r);
            return r;
        } finally {
            respondentsLock.unlock();
        }
    }

    public Manager getFirstAvailableManager() throws EmployeeUnavailableException {
        if (availableManagers.isEmpty()) throw new EmployeeUnavailableException("");
        try {
            managersLock.lock();
            Manager m = availableManagers.pollFirst();
            busyManagers.add(m);
            return m;
        } finally {
            managersLock.unlock();
        }
    }

    public Director getFirstAvailableDirector() throws EmployeeUnavailableException {
        if (availableDirectors.isEmpty()) throw new EmployeeUnavailableException("");
        try {
            directorsLock.lock();
            Director d = availableDirectors.pollFirst();
            busyDirectors.add(d);
            return d;
        } finally {
            directorsLock.unlock();
        }
    }

    public void freeRespondent(Respondent r) {
        try {
            respondentsLock.lock();
            busyRespondents.remove(r);
            availableRespondents.addLast(r);
        } finally {
            respondentsLock.unlock();
        }
    }

    public void freeManager(Manager m) {
        try {
            managersLock.lock();
            busyManagers.remove(m);
            availableManagers.addLast(m);
        } finally {
            managersLock.unlock();
        }
    }

    public void freeDirector(Director d) {
        try {
            directorsLock.lock();
            busyDirectors.remove(d);
            availableDirectors.addLast(d);
        } finally {
            directorsLock.unlock();
        }
    }

    public void initializeEmployeesPool(List<Respondent> respondents, List<Manager> managers, List<Director> directors)
    {
        availableRespondents.addAll(respondents);
        availableManagers.addAll(managers);
        availableDirectors.addAll(directors);
    }
}
