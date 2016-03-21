package it.ecommerceproject.databases.processes;

import java.util.Scanner;
import java.util.List;
import it.ecommerceproject.databases.accounts.Account;
import it.ecommerceproject.databases.accounts.AccountHandler;
import it.ecommerceproject.databases.permissions.Permission;
import it.ecommerceproject.databases.permissions.PermissionsHandler;
import java.sql.SQLException;

// Permission id = 0 --> Admin
// Permission id = 1 --> Operator
// Permission id = 2 --> User

public class Authentication{

    Scanner input = new Scanner(System.in);
    AccountHandler aHandler = new AccountHandler();
    PermissionsHandler pHandler = new PermissionsHandler();
    
    String username;
    String password;
    int permission;
    
    public Authentication() throws SQLException{
        aHandler.open();
        pHandler.open();
        List<Account> accounts = aHandler.getData();
        List<Permission> permissions = pHandler.getData();
        boolean auth = false;
        int id = -1;
        int attempts = 3;
        Account tmp;
        while((!auth) && (attempts != 0)){
            System.out.println("Username: "); username = input.next();
            System.out.println("Password: "); password = input.next();
            for(int i = 0; i < accounts.size(); i++){
                tmp = accounts.get(i);
                if((username.equals(tmp.getUsername()))&&(password.equals(tmp.getPassword()))){
                    auth = true;
                    id = tmp.getID();
                }
            }
            if(auth){
                    System.out.println("You are Authenticated!");
                    System.out.println("Welcome back " + username + "!");
                    Permission p = permissions.get(id-1);
                    permission = p.getPermission();
            }
            else {
                System.out.println("Warning! Wrong username and/or password, retry");
                System.out.println(attempts + " attempts remaining");
                attempts--;
            }
        }
        aHandler.close();
        pHandler.close();
    }

    public int getPermission() {
        return permission;
    }

    public String getUsername() {
        return username;
    }
}
