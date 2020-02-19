package java2;

public class Cashier
{
    String cashierName;
    int CashierId;


    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public int getCashierId() {
        return CashierId;
    }

    public void setCashierId(int cashierId) {
        CashierId = cashierId;
    }

    public void takeOrder(String customerName)
    {
        //cashier receives the order
    }
    public int giveTokenNo(String customerName)
    {
        int token=0;
        //cashier provides tokeno to the customer
        return token;
    }
    public void receivePayment(int tokenno)
    {
        // receives payment
    }
    public void addItToPendingQueue(int tokeno)
    {
        //called when there is a queue of orders
    }



}