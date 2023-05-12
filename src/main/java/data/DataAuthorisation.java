package data;

/**
 * Класс для создания POJO-объекта
 * @author Сытина Елена
 */
public class DataAuthorisation {
    private String email;
    private String password;

    public DataAuthorisation(){
        super();
    }

    public DataAuthorisation(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
