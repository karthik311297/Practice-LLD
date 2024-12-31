package lld.filesystem.search.filter;

import lld.filesystem.file.FileSystemObject;

public class NameFilter implements SearchFilter{
    private String name;

    public NameFilter(String name) {
        this.name = name;
    }

    @Override
    public boolean match(FileSystemObject fileSystemObject) {
        return fileSystemObject.getName().equals(name);
    }
}
