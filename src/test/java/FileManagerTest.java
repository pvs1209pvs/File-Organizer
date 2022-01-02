import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

class FileManagerTest {

    final String TEST_DIR = "src/test/java/test-dir/";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        new File(TEST_DIR).mkdir();
        Stream.of(TEST_DIR + "a.txt", TEST_DIR + "b.png", TEST_DIR + "c.out", TEST_DIR + "d.html").map(File::new).forEach(x -> x.getParentFile().mkdir());
    }

    @Test
    public void moveToExtDir() {

        new FileManager(TEST_DIR);

        List<String> organizedFiles = Stream.of("png/b.png", "html/d.html", "out/c.out", "txt/a.txt")
                .map(file -> TEST_DIR + file)
                .toList();

        List<String> algoResult = listSubFiles();

        Assertions.assertTrue(organizedFiles.containsAll(algoResult));

    }


    private List<String> listSubFiles() {

        File[] dirToOrganize = new File(TEST_DIR).listFiles();

        return Arrays.stream(Objects.requireNonNull(dirToOrganize))
                .filter(File::isDirectory)
                .map(File::listFiles)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .map(File::toString)
                .toList();

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        File testDir = new File(TEST_DIR);
        delete(testDir);
        testDir.delete();
    }

    void delete(File f) {

        Arrays.stream(Objects.requireNonNull(f.listFiles())).forEach(
                file -> {
                    if (file.isDirectory()) delete(file);
                    file.delete();
                }
        );

    }
}