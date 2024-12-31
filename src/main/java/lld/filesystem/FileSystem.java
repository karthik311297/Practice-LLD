package lld.filesystem;

import lld.filesystem.file.File;
import lld.filesystem.file.FileSystemObject;
import lld.filesystem.file.Folder;
import lld.filesystem.search.SearchOption;
import lld.filesystem.search.filter.NameFilter;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    private Folder rootFolder;
    private static final FileSystem instance = new FileSystem();

    private FileSystem() {
        rootFolder = new Folder("", "/", new ArrayList<>());
    }

    public static FileSystem getInstance() {
        return instance;
    }

    public void createFile(String folderPath, File file) {
        file.setPath(folderPath + "/" + file.getName() + "." + file.getExtension());
        String[] path = folderPath.split("/");
        if (path.length == 0) {
            rootFolder.getFileSystemObjects().add(file);
            return;
        }
        Folder context = rootFolder;
        for (int i = 1; i < path.length; i++) {
            context = findFolder(path[i], context);
        }
        context.getFileSystemObjects().add(file);
    }

    public void createFolder(String folderPath, Folder folder) {
        folder.setPath(folderPath + "/" + folder.getName());
        String[] path = folderPath.split("/");
        if (path.length == 0) {
            rootFolder.getFileSystemObjects().add(folder);
            return;
        }
        Folder context = rootFolder;
        for (int i = 1; i < path.length; i++) {
            context = findFolder(path[i], context);
        }
        context.getFileSystemObjects().add(folder);
    }

    private Folder findFolder(String name, Folder context) {
        SearchOption so = new SearchOption(List.of(new NameFilter(name)), null);
        SearchHandler searchHandler = new SearchHandler(so, context);
        return searchHandler.searchForSubFolderInFolder();
    }

    public List<FileSystemObject> findFiles(SearchOption searchOption) {
        SearchHandler searchHandler = new SearchHandler(searchOption, rootFolder);
        return searchHandler.search();
    }
}
