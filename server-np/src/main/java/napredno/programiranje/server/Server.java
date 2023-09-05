package napredno.programiranje.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import napredno.programiranje.constants.ServerConstants;
import napredno.programiranje.domain.User;
import napredno.programiranje.form.ServerForm;
import napredno.programiranje.form.UserTableModel;
import napredno.programiranje.thread.ProcessClientRequests;

public class Server extends Thread {

	private ServerSocket serverSocket;
    private List<ProcessClientRequests> clients = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private ServerForm form;
    private boolean signal = true;

    public Server(ServerForm form) {
        try {
            int port = getPort();
            this.serverSocket = new ServerSocket(port);
            this.form = form;

            start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        while (signal) {
            System.out.println("Server is up and running!");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("New client detected!");
                    handleClient(socket);
                } catch (Exception ex) {
//                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    private void handleClient(Socket socket) throws Exception {
        ProcessClientRequests processClientRequests = new ProcessClientRequests(this, socket);
        clients.add(processClientRequests);
        processClientRequests.start();
    }
    
    public void addUser(ProcessClientRequests pcr) {
        ((UserTableModel) form.getTblUsers().getModel()).add(pcr.getUser());
    }
    
    public int addNewUser(User user) throws Exception {
        if (users.contains(user)) {
            return -1;
        }
        users.add(user);
        return 0;
    }
    
    public void removeUser(ProcessClientRequests pcr) {
        users.remove(pcr);
        users.remove(pcr.getUser());
        ((UserTableModel) form.getTblUsers().getModel()).delete(pcr.getUser());
    }
    
     private int getPort() throws FileNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerConstants.FILE_PATH_SERVER));
        return Integer.valueOf(properties.getProperty(ServerConstants.PORT));

    }
    
    public List<User> getUserList(){
        return users;
    }
    
    public List<ProcessClientRequests> getClientList(){
        return clients;
    }
}
