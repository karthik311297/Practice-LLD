package lld.resturant;

import java.util.List;

public class Menu {

    private List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void display()
    {
        System.out.println("------Menu-----");
        for(MenuItem item : menuItems)
        {
            System.out.println(menuItems);
        }
        System.out.println("-------------");
    }
}
