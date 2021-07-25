package com.company;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

class FileManager {

    private final File parentFolder;
    private final Set<FileExtension> fileExtensions;

    FileManager(String path) {
   
        parentFolder = new File(path);
        fileExtensions = new TreeSet<>();

        moveToExtensionDir(parentFolder);
        cleanUp(parentFolder);

    }

    /**
     * Moves files to the directory corresponding to its extension.
     * @param crntDir Current parent directory.
     */
    private void moveToExtensionDir(File crntDir){

        Arrays.stream(crntDir.listFiles()).forEach(crntFile -> {

            if(crntFile.isDirectory()){
                // recurse sub-directory
                moveToExtensionDir(crntFile);
            }
            else{
        
                FileExtension fileExt = getFileExt(crntFile);
                fileExtensions.add(fileExt);

                // creates the extension-dir 
                new File(parentFolder.toPath()+"/"+fileExt.getFileExt()).mkdir();

                // moves the file to extension-dir.
                crntFile.renameTo(new File(parentFolder.toPath()+"/"+fileExt.getFileExt()+"/"+crntFile.getName()));
            }
            
        });

    }
  
    /**
     * Returns the extension of the file.
     * @param file File whose extension needs to be returned.
     * @return The FileExtension object with the extension of the file. 
     * FileExtension object with the value of 'no-extn' is returned if the file has no extension.
     */
    private FileExtension getFileExt(File file) {

        String pathToString =  file.toPath().toString();
        int dotIndex = pathToString.indexOf('.', 0)+1;

        return 
        dotIndex > 0 ? 
        new FileExtension(pathToString.substring(dotIndex, pathToString.length())) : 
        new FileExtension("no-extn");

    }

    /**
     * Deletes empty directories recursivley. 
     * @param crntDir Parent directory.
     */
    private void cleanUp(File crntDir){
        
       Arrays.stream(crntDir.listFiles())
       .filter(File::isDirectory)
       .forEach(dir -> {
            cleanUp(dir);
            dir.delete();
       });
    
    }

}
