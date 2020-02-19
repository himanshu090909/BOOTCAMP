package java2;

public class Parent extends GrandParent {

        {
            System.out.println("instance - parent");
        }

        public Parent() {
            System.out.println("constructor - parent");
        }

        static {
            System.out.println("static - parent");
        }
    }

