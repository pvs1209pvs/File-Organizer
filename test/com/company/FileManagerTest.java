package test.com.company;

import org.junit.jupiter.api.Test;
import src.com.company.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        new File("test/com/company/test-dir").mkdir();

        File a = new File("test/com/company/test-dir/a.txt");
        a.getParentFile().mkdir();

        File b = new File("test/com/company/test-dir/b.png");
        a.getParentFile().mkdir();

        File c = new File("test/com/company/test-dir/c.out");
        a.getParentFile().mkdir();

        try {
            a.createNewFile();
            b.createNewFile();
            c.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File d = new File("test/com/company/test-dir/sub-dir/d.html");
        d.getParentFile().mkdir();

        try {
            d.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void moveToExtdir() {

        new FileManager("test/com/company/test-dir");
        assertEquals("[test/com/company/test-dir/png/b.png, test/com/company/test-dir/html/d.html, test/com/company/test-dir/out/c.out, test/com/company/test-dir/txt/a.txt]", listSubFiles());

    }

    private String listSubFiles() {

        List<File> organizedFilePaths = new LinkedList<>();

        Arrays.stream(Objects.requireNonNull(new File("test/com/company/test-dir").listFiles()))
                .filter(File::isDirectory)
                .forEach(file -> organizedFilePaths.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles()))));

        return organizedFilePaths.toString();

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

            File testDir = new File("test/com/company/test-dir");
            delete(testDir);
            testDir.delete();

    }

    void delete(File f) {

        Arrays.stream(Objects.requireNonNull(f.listFiles())).forEach(
                file -> {
                    if (file.isDirectory()) {
                        delete(file);
                    }
                    file.delete();
                }
        );

    }
}