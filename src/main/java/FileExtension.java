class FileExtension {

    private final String fileExt; // extension of the file

    FileExtension(String fileExt) {
        this.fileExt = fileExt;
    }

    String getFileExt() {
        return fileExt;
    }

    @Override
    public String toString(){
        return "FileExtension("+fileExt+")";
    }
}

