package lld.filesystem;

import lld.filesystem.file.FileSystemObject;
import lld.filesystem.file.Folder;
import lld.filesystem.file.Type;
import lld.filesystem.search.SearchOption;
import lld.filesystem.search.filter.SearchCondition;
import lld.filesystem.search.filter.SearchFilter;

import java.util.*;

public class SearchHandler {

    private SearchOption searchOption;
    private Folder searchContext;

    public SearchHandler(SearchOption searchOption, Folder searchContext) {
        this.searchOption = searchOption;
        this.searchContext = searchContext;
    }

    public List<FileSystemObject> search()
    {
        List<FileSystemObject> result = new ArrayList<>();
        Queue<FileSystemObject> q = new LinkedList<>();
        q.add(searchContext);
        while(!q.isEmpty())
        {
            FileSystemObject fileSystemObject = q.poll();
            if(doesMatchSearchCriteria(fileSystemObject)) result.add(fileSystemObject);
            if(fileSystemObject.getType() == Type.FOLDER)
            {
                Folder folder = (Folder) fileSystemObject;
                q.addAll(folder.getFileSystemObjects());
            }
        }
        return result;
    }

    protected Folder searchForFolderInFolders()
    {
        List<FileSystemObject> fileSystemObjects = searchContext.getFileSystemObjects();
        for(FileSystemObject fileSystemObject : fileSystemObjects)
        {
            if(fileSystemObject.getType() == Type.FOLDER && doesMatchSearchCriteria(fileSystemObject)) return (Folder) fileSystemObject;
        }
        return null;
    }


    private boolean doesMatchSearchCriteria(FileSystemObject fileSystemObject)
    {
        List<SearchFilter> searchFilters = searchOption.getSearchFilters();
        SearchCondition searchCondition = searchOption.getSearchCondition();
        if(Objects.isNull(searchCondition)) return searchFilters.get(0).match(fileSystemObject);
        else if(searchCondition == SearchCondition.AND)
        {
            for (SearchFilter filter : searchFilters)
            {
                if(!filter.match(fileSystemObject)) return false;
            }
            return true;
        }
        else
        {
            for (SearchFilter filter : searchFilters)
            {
                if(filter.match(fileSystemObject)) return true;
            }
            return false;
        }
    }
}
