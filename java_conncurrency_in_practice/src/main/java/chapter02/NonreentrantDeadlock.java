package chapter02;

class Widget{
    public synchronized void doSomething() {

    }
}

class LoggingWidget extends Widget {
    public synchronized void doSomething() {
        System.out.println(toString()+": calling doSomething");
        super.doSomething();
    }
}
