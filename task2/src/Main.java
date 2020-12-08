import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(100, 2, 1, 1.3D);
        GameProgress gameProgress2 = new GameProgress(50, 4, 80, 1.5D);
        GameProgress gameProgress3 = new GameProgress(1, 2, 100, 1.7D);
        saveGame("Games//savegames//save_1.dat", gameProgress1);
        saveGame("Games//savegames//save_2.dat", gameProgress2);
        saveGame("Games//savegames//save_3.dat", gameProgress3);
        List<String> list = new ArrayList<>();
        list.add("Games//savegames//save_1.dat");
        list.add("Games//savegames//save_2.dat");
        list.add("Games//savegames//save_3.dat");
        zipFiles("Games//savegames//save.zip",list);
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> fileList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String pth: fileList) {
                FileInputStream fis = new FileInputStream(pth);
                ZipEntry entry = new ZipEntry(pth.split("//")[2]);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                fis.close();
                File file = new File(pth);
                file.delete();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
