import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileManagerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        new File("src/test/java/test-dir").mkdir();

        File a = new File("src/test/java/test-dir/a.txt");
        a.getParentFile().mkdir();

        File b = new File("src/test/java/test-dir/b.png");
        a.getParentFile().mkdir();

        File c = new File("src/test/java/test-dir/c.out");
        a.getParentFile().mkdir();

        try {
            a.createNewFile();
            b.createNewFile();
            c.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File d = new File("src/test/java/test-dir/sub-dir/d.html");
        d.getParentFile().mkdir();

        try {
            d.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void moveToExtdir() {

       new FileManager("src/test/java/test-dir");

        Set<String> organizedFiles = Stream.of("src/test/java/test-dir/png/b.png", "src/test/java/test-dir/html/d.html", "src/test/java/test-dir/out/c.out", "src/test/java/test-dir/txt/a.txt").collect(Collectors.toCollection(TreeSet::new));
        Set<String> algoResult = listSubFiles();

        Assertions.assertTrue(organizedFiles.containsAll(algoResult));

    }


    private Set<String> listSubFiles() {

        List<File> organizedFilePaths = new LinkedList<>();

        Arrays.stream(Objects.requireNonNull(new File("src/test/java/test-dir").listFiles()))
                .filter(File::isDirectory)
                .forEach(file -> organizedFilePaths.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles()))));

        return organizedFilePaths.stream().map(File::toString).collect(Collectors.toCollection(TreeSet::new));


    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

        File testDir = new File("src/test/java/test-dir/");
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