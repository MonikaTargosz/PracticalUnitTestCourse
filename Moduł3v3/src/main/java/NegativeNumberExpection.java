public class NegativeNumberExpection extends RuntimeException{

    @Override
    public String getMessage() {
        return message;
    }

    String message;

    public NegativeNumberExpection(String message) {
        this.message= message;
    }
}
