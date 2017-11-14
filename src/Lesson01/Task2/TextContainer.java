package Lesson01.Task2;

import java.io.FileWriter;
import java.io.IOException;


@SaveTo(path="c:\\song.txt")
public class TextContainer {

    private static String text = "Row, row, row your boat...";

    @Save
    public static void save(String path, String text) {

        try(FileWriter fw = new FileWriter(path)) {
            fw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

