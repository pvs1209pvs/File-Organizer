package FileManagerPackage;

class FileExtension implements Comparable<FileExtension> {

    private final String fileExt;

    FileExtension(String fileExt) {
        this.fileExt = fileExt;
    }

    String getFileExt() {
        return fileExt;
    }

    @Override
    public int compareTo(FileExtension o) {
        String a = this.getFileExt();
        String b = o.getFileExt();
        return a.compareTo(b);
    }
}

