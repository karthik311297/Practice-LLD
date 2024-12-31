package lld.filesystem.file;

public abstract class FileSystemObject {

    private String name;
    private String path;
    private Type type;

    public FileSystemObject(String name, String path, Type type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Type getType() {
        return type;
    }
}
