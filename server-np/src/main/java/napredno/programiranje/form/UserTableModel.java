package napredno.programiranje.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import napredno.programiranje.domain.User;

public class UserTableModel extends AbstractTableModel {

	List<User> users = new ArrayList<>();
    String[] columns = {"Ime i prezime", "Username", "Password", "Datum prijave"};

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    public UserTableModel() {
    }
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return users.get(rowIndex).getFirstName() + " " + users.get(rowIndex).getLastName();
            case 1:
                return users.get(rowIndex).getUsername();
            case 2:
                return users.get(rowIndex).getPassword();
            case 3:
                return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public void clear() {
        this.users = new ArrayList<>();
        fireTableDataChanged();
    }

    public void add(User user) {
        users.add(user);
        fireTableDataChanged();
    }

    public void delete(User user) {
        users.remove(user);
        fireTableDataChanged();
    }

}
