package lld.filesystem.file;

public class File extends FileSystemObject {

    private int size;
    private String extension;

    public File(String name, String path, int size, String extension) {
        super(name, path, Type.FILE);
        this.size = size;
        this.extension = extension;
    }

    public int getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }
}
