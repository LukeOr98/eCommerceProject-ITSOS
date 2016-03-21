package dbconnection;

import it.ecommerceproject.databases.Credentials;
import it.ecommerceproject.databases.DatabaseHelper;
import it.ecommerceproject.databases.products.*;
import it.ecommerceproject.databases.accounts.Account;
import it.ecommerceproject.databases.accounts.AccountHandler;
import it.ecommerceproject.databases.permissions.Permission;
import it.ecommerceproject.databases.permissions.PermissionsHandler;
import it.ecommerceproject.databases.processes.Authentication;
import it.ecommerceproject.databases.panels.*;
import java.util.List;

// Permission id = 0 --> Admin
// Permission id = 1 --> Operator
// Permission id = 2 --> User

public class DbConnection {

    public static void main(String[] args) throws Exception {
        Credentials credentials = new Credentials("wikibackup.tes.mi.it","root","oliva");
        DatabaseHelper.openConnection(credentials);
        
        AccountHandler aHandler = new AccountHandler();
        PermissionsHandler pHandler = new PermissionsHandler();
        
        aHandler.open();
        pHandler.open();
        
        //aHandler.delete(3);
        //pHandler.delete(3);
        
        //aHandler.insert(new Account("root", "oliva"));
        //pHandler.insert(new Permission(0));
        
        Authentication instance = new Authentication();
        
        String user = instance.getUsername();
        
        switch(instance.getPermission()){
            case 0: System.out.println("You are an Administrator!"); AdminPanel ap = new AdminPanel(user); break;
            case 1: System.out.println("You are an Operator!"); break;
            case 2: System.out.println("You are an User!"); break;
        }
        /*
        System.out.println("\nAccounts:");
        List<Account> accounts = aHandler.getData();
        for(int i=0;i<accounts.size();i++){
            Account tmp = accounts.get(i);
            System.out.println(tmp.getID() + " "+tmp.getUsername()+" "+tmp.getPassword());
            
        }
        System.out.println("\nPermissions:");
        List<Permission> permissions = pHandler.getData();
        for(int i=0;i<permissions.size();i++){
            Permission tmp = permissions.get(i);
            System.out.println(tmp.getID() + " "+tmp.getPermission());
        }
        */
        aHandler.close();
        pHandler.close();
    }
    
}
