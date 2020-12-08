import java.io.*;
import java.time.LocalDateTime;

public class Main {
    public static StringBuilder sBuilder = new StringBuilder();
    public static LocalDateTime ldTime = LocalDateTime.now();

    public static void main(String[] args) {
        createDir("Games", "src");
        createDir("Games", "res");
        createDir("Games", "savegames");
        createDir("Games", "temp");
        createDir("Games//src", "main");
        createDir("Games//src", "test");
        createDir("Games//res", "drawables");
        createDir("Games//res", "vectors");
        createDir("Games//res", "icons");
        createFile("Games//src//main//Main.java");
        createFile("Games//src//main//Utils.java");
        createFile("Games//temp//temp.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Games//temp//temp.txt"))) {
            bufferedWriter.write(sBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sBuilder);
    }

    public static void createDir(String parentName, String dirName) {
        File dir = new File(parentName, dirName);
        if (dir.mkdir()) {
            sBuilder.append(ldTime)
                    .append(": ")
                    .append("Создан каталог ")
                    .append(dirName)
                    .append(" в каталоге ")
                    .append(parentName)
                    .append("\n");
        } else {
            sBuilder.append(ldTime)
                    .append(": ")
                    .append("Ошибка при создании каталога ")
                    .append(dirName)
                    .append(" в каталоге ")
                    .append(parentName)
                    .append("\n");
        }
    }

    public static void createFile(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
                sBuilder.append(ldTime)
                        .append(": ")
                        .append("Создан файл ")
                        .append(path)
                        .append("\n");
            } else {
                sBuilder.append(ldTime)
                        .append(": ")
                        .append("Ошибка при создании файла ")
                        .append(path)
                        .append("\n");
            }
        } catch (IOException e) {
            sBuilder.append(ldTime)
                    .append(": ")
                    .append("При создании файла ")
                    .append(path)
                    .append(" возникло исключение ")
                    .append(" :\n\t")
                    .append(e.toString())
                    .append("\n");
        }
    }
}
