package data;
/**
 * Класс для создания POJO-объекта
 * @author Сытина Елена
 */
public class ErrorData {
     private String error;

     public ErrorData(){
         super();
     }

    public ErrorData(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
