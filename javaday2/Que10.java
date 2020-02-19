package java2;

public class Que10
{
    public static void main(String[] args)
    {
        Cashier cashier = new Cashier();
        Bistro bistro = new Bistro();
        Customer customer = new Customer("Himanshu",32456);
        customer.PlaceOrder();
        cashier.takeOrder(customer.getName());
        int token=cashier.giveTokenNo(customer.getName());
        customer.getTokeno(token);
        cashier.receivePayment(customer.getTokeno(token));
        cashier.addItToPendingQueue(customer.getTokeno(token));
        customer.checkOrderStatus(customer.getTokeno(token));
        bistro.getOrderFromPendingQueue();
        bistro.prepareOrder();
        bistro.insertOrderIntoCompletedOrderQueue();

    }
}
