package FileManagerPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class FileManager {

    private File folder;
    private String path;
    private String folderName;
    private Set<FileExtension> fileExtension;
    private ArrayList<File> allFiles;

    FileManager(String path, String folderName) {

        this.folderName = folderName;
        this.path = path;
        this.folder = new File(this.path + folderName);
        this.fileExtension = new TreeSet<>();
        this.allFiles = new ArrayList<>();

        addAllFileExt();
        makeDirs();
        moveFiles();

    }

    private void moveFiles() {

        FileExtension[] fileExtArray = new FileExtension[fileExtension.size()];

        int i = 0;
        for (FileExtension x : fileExtension) fileExtArray[i++] = x;

        i = 0;
        for (i = 0; i < fileExtArray.length; i++) {
            for (int j = 0; j < allFiles.size(); j++) {
                if (fileExtArray[i].getFileExt().equals(getFileExt(allFiles.get(j)).getFileExt())) {
                    File toMove = new File(allFiles.get(j).toString());
                    toMove.renameTo(new File(this.path + this.folderName + File.separator + fileExtArray[i].getFileExt() + File.separator + allFiles.get(j).getName()));
                }
            }
        }

    }

    // make dirs for extension
    private void makeDirs() {

        for (FileExtension fileExt : fileExtension) {
            File dir = new File(this.path + this.folderName + File.separator + fileExt.getFileExt());
            dir.mkdir();
        }

    }

    // Adds all the extensions to file extension's set
    private void addAllFileExt() {

        getAllFiles(this.folder);
        for (File x : allFiles) fileExtension.add(getFileExt(x));

    }

    // Returns the file extension of the passed given file.
    private FileExtension getFileExt(File file) {

        StringBuilder ext = new StringBuilder();

        for (int i = file.toString().length() - 1; i >= 0; i--) {
            if (file.toString().charAt(i) == '.') break;
            ext.append(file.toString().charAt(i));
        }

        return new FileExtension(ext.reverse().toString());

    }

    // Adds all the file to the File ArrayList
    private void getAllFiles(File fullPath) {

        File[] dirContents = fullPath.listFiles();
        for (File x : dirContents) {
            if (x.isDirectory()) getAllFiles(x);
            if (x.isFile()) this.allFiles.add(x);
        }

    }
    
    private void cleanUp(){
        
        File[] currentDir = new File(path+folderName).listFiles();

        if(currentDir==null) return;

        for (File x : currentDir){
            x.delete();
        }
        
    }

}
