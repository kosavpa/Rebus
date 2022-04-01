import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class reb {
    static int W = 0;
    static int rankValue = 2;
    static int charValue = 97;

    static HashMap<Integer,Character> decoderMap = new HashMap<>(27);

    static { //заполнил мапу кодами букв с инверсией
            for (int i = 0; i < 27; i++) {
                W = (int) Math.pow(rankValue, i);
                if(i == 26){
                    decoderMap.put(W, ' ');
                    continue;
                }
                decoderMap.put(W, (char)(charValue+i));
            }
    }

    public static int[] getCipher(){ //получаю массив чисел-шифров из файла
        String[] cipherString;
        int[] cipherDigit = null;
        try(
                BufferedReader fileReader = new BufferedReader(new FileReader(new Scanner(System.in).nextLine()))
                ){

            String[] lineArray = new String[2];
            int counter = 0;
            while (counter <= 1){
                lineArray[counter] = fileReader.readLine();
                counter++;
            }
            cipherString = lineArray[1].split(" ");
            cipherDigit = new int[cipherString.length];
            for(int i = 0; i < cipherString.length; i++){
                cipherDigit[i] = Integer.parseInt(cipherString[i]);
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return cipherDigit;
    }

    public static void decoding(int[] array) { //расшифровка и запись в файл

        StringBuilder result = new StringBuilder();
        int starNumber;
        for (int j = 0; j < array.length; j++) {
            if(j == 0){
                result.append(decoderMap.get(array[j]));
                continue;
            } else {
                if (array[j] < array[j - 1]) {
                    int i = array[j-1] - array[j];
                    result.append(decoderMap.get(i));
                } else{
                    result.append(decoderMap.get(array[j] - array[j - 1]));
                }
            }
        }
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        reb.decoding(reb.getCipher());
    }
}
