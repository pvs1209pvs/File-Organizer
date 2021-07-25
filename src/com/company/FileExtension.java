package com.company;

class FileExtension implements Comparable<FileExtension> {

    private final String fileExt; // extension of the a file

    FileExtension(String fileExt) {
        this.fileExt = fileExt;
    }

    String getFileExt() {
        return fileExt;
    }

    @Override
    public int compareTo(FileExtension o) {
        return this.getFileExt().compareTo(o.getFileExt());
    }

    @Override
    public String toString(){
        return "FileExtension("+fileExt+")";
    }
}

