package lld.filesystem.search.filter;

import lld.filesystem.file.FileSystemObject;

public interface SearchFilter {
    boolean match(FileSystemObject fileSystemObject);
}
