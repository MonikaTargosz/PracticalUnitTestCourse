public class StringCalculator {

    public int add(String input) {

        if (input.isEmpty()) {
            return 0;
        }

        else {
            String[] numbers = input.split(",");
            int result = 0;
            for (String singleNumber:numbers) {
                result += getIntForm(singleNumber);
            }
            return result;
        }
    }

    private int getIntForm(String numbers){
        return Integer.parseInt(numbers);
    }
}