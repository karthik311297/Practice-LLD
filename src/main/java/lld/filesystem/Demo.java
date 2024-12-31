package lld.filesystem;

import lld.filesystem.file.File;
import lld.filesystem.file.FileSystemObject;
import lld.filesystem.file.Folder;
import lld.filesystem.search.SearchOption;
import lld.filesystem.search.filter.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    /*
        FileObject
            name
            type
            path

        Folder is FileObject
            List<FileObject>

        File is FileObject
            size
            extension

        FileSystem
            RootFolder
            createFile(Folderpath, File)
            createFolder(Folderpath, Folder)
            findFiles(SearchOption s)
            findFolder(currentFolder, name)

        Search
            FileSystem
            find()

        SearchOption
            SearchFilter
            SearchCondition

        SearchFilter
            match(File file)

        enum SearchCondition - AND , OR

        NameSearchFilter

        SizeSearchFilter

        ExtensionSearchFilter

     */

    public static void main(String[] args) {
        FileSystem fileSystem = FileSystem.getInstance();
        fileSystem.createFile("", new File("file1", null, 10, "txt"));
        fileSystem.createFile("", new File("file2", null, 20, "txt"));
        fileSystem.createFolder("", new Folder("folder1", null, new ArrayList<>()));
        fileSystem.createFolder("/folder1", new Folder("folder2", null, new ArrayList<>()));
        fileSystem.createFile("/folder1", new File("file3", null, 30, "json"));
        fileSystem.createFile("/folder1", new File("file4", null, 30, "txt"));
        List<FileSystemObject> res1 = fileSystem.findFiles(new SearchOption(List.of(new SizeFilter(30, Operator.EQUAL_TO)), null));
        List<FileSystemObject> res2 = fileSystem.findFiles(new SearchOption(Arrays.asList(new SizeFilter(10, Operator.EQUAL_TO),
                new ExtensionFilter("txt")), SearchCondition.AND));
        List<FileSystemObject> res3 = fileSystem.findFiles(new SearchOption(List.of(new ExtensionFilter("json")), null));
        List<FileSystemObject> res4 = fileSystem.findFiles(new SearchOption(Arrays.asList(new SizeFilter(30, Operator.EQUAL_TO),
                new ExtensionFilter("json")), SearchCondition.OR));
        List<FileSystemObject> res5 = fileSystem.findFiles(new SearchOption(Arrays.asList(new SizeFilter(10, Operator.EQUAL_TO),
                new ExtensionFilter("txt")), SearchCondition.OR));
        List<FileSystemObject> res6 = fileSystem.findFiles(new SearchOption(Arrays.asList(new NameFilter("file1"),
                new NameFilter("folder2")), SearchCondition.OR));
        System.out.println(res1.size());
        System.out.println(res2.size());
        System.out.println(res3.size());
        System.out.println(res4.size());
        System.out.println(res5.size());
        System.out.println(res6.size());
    }
}
