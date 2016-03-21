package it.ecommerceproject.databases.permissions;

import it.ecommerceproject.util.Identificable;

public class Permission extends Identificable{
    
    private int permission;

    protected Permission(int id, int permission) {
        setID(id);
        this.permission = permission;
    }

    public Permission(int permission) {
        this.permission = permission;
        setID(-1);
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
