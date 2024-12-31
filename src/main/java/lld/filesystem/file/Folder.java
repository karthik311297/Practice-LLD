package lld.filesystem.file;

import java.util.List;

public class Folder extends FileSystemObject {

    private List<FileSystemObject> fileSystemObjects;

    public Folder(String name, String path, List<FileSystemObject> fileSystemObjects) {
        super(name, path, Type.FOLDER);
        this.fileSystemObjects = fileSystemObjects;
    }

    public List<FileSystemObject> getFileSystemObjects() {
        return fileSystemObjects;
    }
}
