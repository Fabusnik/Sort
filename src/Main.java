import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * метод слияния двух массивов
     *
     * @param mas1          - первый массив на слияние
     * @param mas2          - второй массив на слияние
     * @param sortRoute     - порядок сортировки (-1 убывание, 1 - возростание)
     * @param inputDataType - тип входных данных Integer или String (в зависимости от входных параметров)
     * @return
     */
    public static ArrayList merge_num(ArrayList<String> mas1, ArrayList<String> mas2, int sortRoute, String inputDataType) {

        ArrayList<String> res = new ArrayList();
        int iFirst = 0;
        int iSecond = 0;

        while (iFirst < mas1.size() && iSecond < mas2.size()) {
            // для Integer
            if (inputDataType.equals("Integer")) {
                if (sortRoute * Integer.parseInt(mas1.get(iFirst)) < sortRoute * Integer.parseInt(mas2.get(iSecond))) {
                    res.add((mas1.get(iFirst)));
                    iFirst++;
                } else {
                    res.add(mas2.get(iSecond));
                    iSecond++;
                }
            } // для Integer
            else {
                // Для String
                if (mas1.get(iFirst).compareTo(mas2.get(iSecond)) * sortRoute < 0) {
                    res.add((mas1.get(iFirst)));
                    iFirst++;
                } else {
                    res.add(mas2.get(iSecond));
                    iSecond++;
                }


            }
        }
        if (iFirst < mas1.size()) {
            for (int i = iFirst; i < mas1.size(); i++) {
                res.add(mas1.get(i));
            }
        } else if (iSecond < mas2.size()) {
            for (int i = iSecond; i < mas2.size(); i++) {
                res.add(mas2.get(i));
            }
        }
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String inputDataType = "";
        int sortRoute = 1;
        List<String> inputFile = new ArrayList<>();
        String outputFile;

        // Инициализация переменных из входных параметров
        for (String arg : args) {
            if (arg.equals("-a") || arg.equals("-d")) {
                switch (arg) {
                    case ("-a"):
                        sortRoute = 1;
                        break;
                    case ("-d"):
                        sortRoute = -1;
                        break;
                }
            } else if (arg.equals("-s") || arg.equals("-i")) {
                switch (arg) {
                    case ("-s"):
                        inputDataType = "String";
                        break;
                    case ("-i"):
                        inputDataType = "Integer";
                        break;
                }
            } else {
                inputFile.add(arg);
            }
        }
        outputFile = inputFile.remove(0);

        ArrayList<ArrayList<String>> myDate = new ArrayList<>(); //массив входных данных

        for (String fileName : inputFile) {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<String> list = new ArrayList<String>();
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
            myDate.add(list);
        }

        ArrayList<String> res = new ArrayList();

        /* слияние данных в цикле*/
        for (ArrayList list : myDate) {
            res = (merge_num(res, list, sortRoute, inputDataType));
        }

        /* запись итового массива в файл*/
        PrintStream fos = new PrintStream(outputFile);
        for (String i : res) {
            fos.println(i);
        }
        fos.close();
    }
}
