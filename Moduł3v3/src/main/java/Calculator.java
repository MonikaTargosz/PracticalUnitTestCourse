import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private static boolean isSingleNumber(String input) {
        return input.matches("[0-9]]");
    }

    public int add(String input) {
        //Test 1
        //RED: return -1;
        //GREEN:return 0;

        //Test 2
        //RED:return Integer.parseInt(input);
        //GREEN:
        //if (input.isEmpty()){
        //return 0;
        //}
        //return Integer.parseInt(input);

        //Test 3
        if (input.isEmpty()) {
            return 0;
        }
        String[] numbers = input.split(",|\n");
        int result = 0;
        List<String> negatives = new ArrayList<>();
        for (String number : numbers) {
            if (Integer.parseInt(number) < 0) {
                negatives.add(number);
            }
            result += Integer.parseInt(number);
        }
        if (negatives.isEmpty()) {
            return result;
        }
        String message = "Negatives: ";
        message += String.join(" ",negatives);
        //StringBuilder builder = new StringBuilder();
        //builder.append("Negatives: ");
        //for (String negative: negatives){
        //    builder.append(negative);
        //    builder.append(" ");
        //}
        throw new NegativeNumberExpection(message);
    }
}
