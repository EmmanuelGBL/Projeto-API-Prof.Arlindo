package projetoapi.profarlindo.Role;

public enum AdminRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    AdminRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

