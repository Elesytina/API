package data;
/**
 * Класс для создания POJO-объекта
 * @author Сытина Елена
 */
public class EmailData {
    private String email;

    public EmailData(){
        super();
    }

    public EmailData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
