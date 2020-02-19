package java2;

public class Sing
{
        public static Sing obj;
        private Sing()
        {

        }

        public static Sing getInsatnce()
        {
            if(obj==null)
            {
                System.out.println("object created....this message will be printed only once as only one object is created");
                obj = new Sing();
            }
            return obj;
        }
    }


