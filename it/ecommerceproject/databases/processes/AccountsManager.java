package it.ecommerceproject.databases.processes;

import it.ecommerceproject.databases.accounts.Account;
import it.ecommerceproject.databases.accounts.AccountHandler;
import it.ecommerceproject.databases.permissions.Permission;
import it.ecommerceproject.databases.permissions.PermissionsHandler;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountsManager {
    
    AccountHandler aHandler = new AccountHandler();
    PermissionsHandler pHandler = new PermissionsHandler();
    
    Scanner input = new Scanner(System.in);

    public AccountsManager() {
        
    }
    
    public boolean addUser() throws SQLException {
        
        aHandler.open();
        pHandler.open();
        
        boolean added = false;
        String yesNo = "h";
        boolean found = false;
        Account a;
        
        do{
            System.out.println("New Account's Username: "); String user = input.next();
            System.out.println("New Account's Password: "); String pass = input.next();
            System.out.println("New Account's Permissions: (0 for Admin, 1 for Operator, 2 for User) "); int perm = input.nextInt();
            List<Account> accounts = aHandler.getData();
            for(int i = 0; i < accounts.size(); i++){
                a = accounts.get(i);
                if(user.equals(a.getUsername())){
                    found = true;
                }
            }
                if(!found){
                    try {
                        aHandler.insert(new Account(user, pass));
                        pHandler.insert(new Permission(perm));
                        added = true;
                    }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                    do{
                        System.out.println("User successfuly created, do tou want to create another user? [y/n] "); yesNo = input.next();
                    } while(!((yesNo.equals("y"))||(yesNo.equals("n"))));
                }
                else{
                    do{
                        System.out.println("User " + user + " already exists, do you want to retry? [y/n] "); yesNo = input.next();
                        if(yesNo.equals("y")){
                            found = false;
                        }
                    }while(!((yesNo.equals("y"))||(yesNo.equals("n"))));
                }
        }while(yesNo.equals("y"));
        aHandler.close();
        pHandler.close();
        
        return added;
    }
    
    public int deleteUser(String logged) throws SQLException{
        
        //removed = 0 --> rimosso
        //removed = 1 --> non esiste
        //removed = 2 --> loggato
        
        aHandler.open();
        pHandler.open();
        
        int removed = 3;
        boolean found = false;
        int id = 0;
        Account a;
        System.out.println("Username: "); String user = input.next();
        try {
            List<Account> accounts = aHandler.getData();
            for(int i = 0; i<accounts.size(); i++){
                a = accounts.get(i);
                if(user.equals(a.getUsername())){
                    found = true;
                    id = a.getID();
                }
            }
            if(!found){
                removed = 1;
            }
            if(user.equals(logged)){
                removed = 2;
            }
                if(found){
                    if(!user.equals(logged)){
                        aHandler.delete(id);
                        pHandler.delete(id);
                        removed = 0;
                    }
                }
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        if(removed == 1){
            System.out.println("User " + user + " doesn't exists");
        }
        if(removed == 2){
            System.out.println("You can't delete user " + user + " because you are logged in with it");
        }
        aHandler.close();
        pHandler.close();
        return removed;
    }
    public boolean editUser() throws SQLException{
        aHandler.open();
        pHandler.open();
        
        boolean done = false;
        Account a;
        Permission p;
        System.out.println("Username: "); String user = input.next();
        try {
            List<Account> accounts = aHandler.getData();
            List<Permission> permissions = pHandler.getData();
            System.out.println("New Username: "); String nuser = input.next();
            System.out.println("New Password: "); String npass = input.next();
            System.out.println("New Permission: (0 for Admin, 1 for Operator, 2 for User) "); int nperm = input.nextInt();
            for(int i = 0; i<accounts.size(); i++){
                a = accounts.get(i);
                p = permissions.get(i);
                if(!done){
                    if(user.equals(a.getUsername())){
                        a.setUsername(nuser);
                        a.setPassword(npass);
                        p.setPermission(nperm);
                        aHandler.update(a);
                        pHandler.update(p);
                        done = true;
                    }
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        aHandler.close();
        pHandler.close();
        return done;
    }
    public void printUsers() throws SQLException{
        aHandler.open();
        pHandler.open();
        
        String permString = "";
        
        Account a;
        Permission p;
        
        List<Account> accounts = aHandler.getData();
        List<Permission> permissions = pHandler.getData();
        System.out.printf("%5s%20s%20s%20s\n", "ID", "Username", "Password", "Permission");
        for(int i = 0; i<accounts.size(); i++){
            a = accounts.get(i);
            p = permissions.get(i);
            
            switch(p.getPermission()){
                case 0: permString = "Administrator"; break;
                case 1: permString = "Operator"; break;
                case 2: permString = "User"; break;
            }
            System.out.printf("%5d%20s%20s%20s\n", a.getID(), a.getUsername(), a.getPassword(), permString);
        }
    }
}