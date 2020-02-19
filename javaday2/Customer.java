package java2;

public class Customer
{
    private String name;
    private double phone;
    private int tokeno;
    Customer(String name,double phone)
    {
        this.name=name;
        this.phone=phone;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPhone() {
        return phone;
    }

    public void setPhone(double phone) {
        this.phone = phone;
    }

    public int getTokeno(int t) {
        tokeno=t;
        return tokeno;
    }

    public void setTokeno(int tokeno) {
        this.tokeno = tokeno;
    }
    public void PlaceOrder()
    {

        //customer places order on the basis of his name and phone number
    }
    public void checkOrderStatus(int tokeno)
    {

        // customer checks order status on the basis of tokeno given him

    }
    public void collectOrder(int tokeno)
    {
        // customer can collect the coffee after entering tokenno
    }
    public void payment()
    {
        //payment is done through this method
    }
    public void wrongOrder(){
        //not liking his coffee, he getting wrong coffee are not considered to keep the design simple.
    }
}
