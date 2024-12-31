package lld.filesystem.search.filter;

import lld.filesystem.file.File;
import lld.filesystem.file.FileSystemObject;
import lld.filesystem.file.Type;

public class ExtensionFilter implements SearchFilter{
    private String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean match(FileSystemObject fileSystemObject) {
        if (fileSystemObject.getType() == Type.FOLDER) return false;
        File file = (File) fileSystemObject;
        return file.getExtension().equals(extension);
    }
}
