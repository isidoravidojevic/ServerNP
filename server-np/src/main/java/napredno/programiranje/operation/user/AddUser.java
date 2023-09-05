package napredno.programiranje.operation.user;

import java.util.List;

import napredno.programiranje.domain.User;
import napredno.programiranje.operation.AbstractGenericOperation;

public class AddUser extends AbstractGenericOperation {

    List<User> users;

    @Override
    protected void preconditions(Object param) throws Exception {
        users = repository.getAll(new User());
        User u = (User) param;
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid user data!");
        }
        if (users.contains(u)) {
            throw new Exception("There is already the same user!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((User) param);
    }

}
