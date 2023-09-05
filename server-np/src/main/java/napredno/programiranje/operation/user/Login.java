package napredno.programiranje.operation.user;

import java.util.List;

import napredno.programiranje.domain.User;
import napredno.programiranje.operation.AbstractGenericOperation;

public class Login extends AbstractGenericOperation{

    List<User> users;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        users = repository.getAll(new User());
    }

    public List<User> getUsers() {
        return users;
    }
}
