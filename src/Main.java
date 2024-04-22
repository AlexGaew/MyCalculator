import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, MyExeption {
        String a = enterNumbers();
        System.out.println("Output:" + " " + '\n' + a);
    }


    public static String enterNumbers() throws IOException, MyExeption {

        System.out.println("Input: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String enterLine = reader.readLine();


        if (enterLine.contains("I") || enterLine.contains("II") || enterLine.contains("III") || enterLine.contains("IV")
                || enterLine.contains("V") || enterLine.contains("VI") || enterLine.contains("VII") || enterLine.contains("VIII") ||
                enterLine.contains("IX") || enterLine.contains("X")) {
            return calculateRomanNumbers(enterLine);
        } else return String.valueOf(calculateArabNumbers(enterLine));
    }

    public static Integer calculateArabNumbers(String enterDataForCalculate) throws MyExeption {
        int result = 0;

        if (enterDataForCalculate == null || enterDataForCalculate.isEmpty() || enterDataForCalculate.length() == 1)
            throw new MyExeption(" строка не является математической операцией");


        String newDataForCalculate = enterDataForCalculate.replaceAll(" ", "");

        for (int i = 0; i < newDataForCalculate.length(); i++) {
            if (newDataForCalculate.charAt(i) == '+') {
                result = metodMetod(enterDataForCalculate).get(0) + metodMetod(enterDataForCalculate).get(1);

            } else if (newDataForCalculate.charAt(i) == '-') {
                result = metodMetod(enterDataForCalculate).get(0) - metodMetod(enterDataForCalculate).get(1);
            } else if (newDataForCalculate.charAt(i) == '*') {
                result = metodMetod(enterDataForCalculate).get(0) * metodMetod(enterDataForCalculate).get(1);
            } else if (newDataForCalculate.charAt(i) == '/') {
                result = metodMetod(enterDataForCalculate).get(0) / metodMetod(enterDataForCalculate).get(1);
            }
        }
        return result;
    }

    public static String calculateRomanNumbers(String enterLine) throws MyExeption {
        String result = "";
        if (enterLine == null || enterLine.isEmpty())
            throw new MyExeption("Введите данные!!!");
        String newDataForCalculate = enterLine.replaceAll(" ", "");
        for (int i = 0; i < newDataForCalculate.length(); i++) {
            if (newDataForCalculate.charAt(i) == '+') {
                String bezOperatora = newDataForCalculate.replace('+', ' ');
                String[] razbilPoProbelu = bezOperatora.split(" ");
                int tmp = convertRomanToArabic(razbilPoProbelu).get(0) + convertRomanToArabic(razbilPoProbelu).get(1);
                return ConvertDigitToRoman.convert(tmp);

            } else if (newDataForCalculate.charAt(i) == '-') {
                String bezOperatora = newDataForCalculate.replace('-', ' ');
                String[] razbilPoProbelu = bezOperatora.split(" ");
                if (convertRomanToArabic(razbilPoProbelu).get(0) - convertRomanToArabic(razbilPoProbelu).get(1) < 0)
                    throw new MyExeption("в римской системе нет отрицательных чисел");
                int tmp = convertRomanToArabic(razbilPoProbelu).get(0) - convertRomanToArabic(razbilPoProbelu).get(1);
                return ConvertDigitToRoman.convert(tmp);

            } else if (newDataForCalculate.charAt(i) == '*') {
                String bezOperatora = newDataForCalculate.replace('*', ' ');
                String[] razbilPoProbelu = bezOperatora.split(" ");
                int tmp = convertRomanToArabic(razbilPoProbelu).get(0) * convertRomanToArabic(razbilPoProbelu).get(1);
                return ConvertDigitToRoman.convert(tmp);

            } else if (newDataForCalculate.charAt(i) == '/') {
                String bezOperatora = newDataForCalculate.replace('/', ' ');
                String[] razbilPoProbelu = bezOperatora.split(" ");
                int tmp = convertRomanToArabic(razbilPoProbelu).get(0) / convertRomanToArabic(razbilPoProbelu).get(1);
                return ConvertDigitToRoman.convert(tmp);
            }
        }
        return result;
    }

    public static ArrayList<Integer> convertRomanToArabic(String[] s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        ArrayList<Integer> list = new ArrayList<>();
        try {
            for (int j = 0; j < s.length; j++) {
                String simbol = s[j];
                int end = simbol.length() - 1; //число места последнего символа
                char[] chars = simbol.toCharArray(); // разбил строку на символы
                int arabian;
                int result = map.get(chars[end]);
                list.add(j, result);
                for (int k = end - 1; k >= 0; k--) {
                    arabian = map.get(chars[k]);
                    if (arabian < map.get(chars[k + 1])) {
                        result -= arabian;
                        list.add(j, result);
                    } else {
                        result += arabian;
                        list.add(j, result);
                    }
                }
            }

        } catch (Exception e) {

            System.out.println("Недопустимый ввод символа");
        }

        return list;
    }

    public static ArrayList<Integer> metodMetod(String s) throws MyExeption {
        String[] str = s.split(" ");
        if (str.length > 3) {
            throw new MyExeption(" формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else {
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[2]);
            if (x > 10 || y > 10) {
                throw new MyExeption("Запрещенно воводить значения больше 10!");
            }
            ArrayList<Integer> list = new ArrayList<>();
            list.add(x);
            list.add(y);
            return list;
        }
    }


}



