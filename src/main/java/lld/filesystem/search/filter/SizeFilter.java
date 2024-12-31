package lld.filesystem.search.filter;

import lld.filesystem.file.File;
import lld.filesystem.file.FileSystemObject;
import lld.filesystem.file.Type;

public class SizeFilter implements SearchFilter {
    private int size;
    private Operator operator;

    public SizeFilter(int size, Operator operator) {
        this.size = size;
        this.operator = operator;
    }

    @Override
    public boolean match(FileSystemObject fileSystemObject) {
        if (fileSystemObject.getType() == Type.FOLDER) return false;
        File file = (File) fileSystemObject;
        int fileSize = file.getSize();
        return switch (operator) {
            case EQUAL_TO -> fileSize == size;
            case LESS_THAN -> fileSize < size;
            case GREATOR_THAN -> fileSize > size;
            case LESS_THAN_OR_EQUAL_TO -> fileSize <= size;
            case GREATOR_THAN_OR_EQUAL_TO -> fileSize >= size;
        };
    }
}
