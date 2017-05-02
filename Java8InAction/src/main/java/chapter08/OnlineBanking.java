package chapter08;


public abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    protected abstract void makeCustomerHappy(Customer c);

    static private class Customer{}
    static private class Database{
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }
}
