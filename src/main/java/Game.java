import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static String game(String text) throws IOException {
        String[] Game = new String[1];
        Scanner scann = new Scanner(new File("C:\\Users\\dbDan\\Desktop\\TelegramBot\\src\\main\\java\\Game.txt"));
        int num = 1 + (int) (Math.random() * 25);
        String numSad = Integer.toString(num);
        numSad += ".";
        while (scann.hasNextLine())
        {
            String text1 = scann.nextLine();
            Game = text1.split(numSad);
        }
                return Game[1];
    }}
