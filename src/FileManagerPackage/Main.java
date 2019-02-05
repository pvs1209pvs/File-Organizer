package FileManagerPackage;

import javax.swing.*;
import java.io.File;

public class Main {

    Main() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String p = chooser.getCurrentDirectory() + File.separator;
            String f = chooser.getSelectedFile().getName();
            System.out.println(p + "  " + f);
            FileManager main = new FileManager(p, f);
        }
        else {
            System.exit(-1);
        }


    }

    public static void main(String[] args) {
        new Main();
    }
}
