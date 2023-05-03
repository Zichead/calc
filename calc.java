import java.util.Scanner;

 class Calculator {
    public static void main(String[] args) throws ThrowExeption {
        Scanner sc = new Scanner(System.in);
        Main result = new Main();
        System.out.print("Введите выражение: ");
        String expression = sc.nextLine();
        String answer = result.calc(expression);

        System.out.println(answer);
    }
}

class Main{
    public static String calc(String input) throws ThrowExeption {
        boolean romanOrArabian = false;
        Main romanExamination = new Main();
        Main arabianToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");

        if (inputSplit.length != 3) {
            throw new ThrowExeption();
        }

        Integer firstNumber = 0;
        Integer secondNumber = 0;
        try {
            firstNumber = Integer.valueOf(inputSplit[0]);
            secondNumber = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException e) {                          // арабские
            try {
                firstNumber = romanExamination.romanToArabian(inputSplit[0]);
                secondNumber = romanExamination.romanToArabian(inputSplit[2]);
                romanOrArabian = true;
            } catch (NumberFormatException ex) {                     // римские
                throw new ThrowExeption();
            }
        }

        if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){
            throw new ThrowExeption();
        }

        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> result = firstNumber / secondNumber;
            default -> {
                throw new ThrowExeption();                                    // проверка на знак
            }
        }

        String output;

        if (romanOrArabian){
            if(result < 1){
                throw new ThrowExeption();
            } else {
                output = arabianToRoman.arabianToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }

        return output;
    }

    Integer romanToArabian(String romanInput){                            // перевод римский в арабский
        int result = 0;
        int[] arabian = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabian.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arabian[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }

        return result;
    }

    String arabianToRome(int arabianInput){                                  // перевод арабские в римские
        String result = "";
        int value = 0;
        int[] arabian = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabian.length; i++){
            value = arabianInput / arabian[i];
            for (int z = 0; z < value; z++){
                result = result.concat(roman[i]);
            }
            arabianInput = arabianInput % arabian[i];
        }
        return result;
    }
}
