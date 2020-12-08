import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        openZip("Games//savegames//","Games//savegames//save.zip");
        openProgress("Games//savegames//save_1.dat");
    }

    public static void openZip(String extractPath, String zipPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(extractPath+name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void openProgress(String path){
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             GameProgress gameProgress = (GameProgress) ois.readObject();
            System.out.println(gameProgress.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
