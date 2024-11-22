public class semaPhore
{
    protected int NumberOfAvaliableSpots = 0;
    private int initial = 0;
    protected semaPhore()
    {
        NumberOfAvaliableSpots = 0;
    }

    public synchronized int  getNumberOfAvaliableSpots() {
        if(NumberOfAvaliableSpots <= 0)
        {
            return 0;
        }
        return NumberOfAvaliableSpots;

    }

    public void setNumberOfAvaliableSpots(int numberOfAvaliableSpots) {
        NumberOfAvaliableSpots = numberOfAvaliableSpots;
    }

    protected semaPhore(int value)
    {

        this.NumberOfAvaliableSpots = value;
        this.initial = value;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public synchronized void P() throws InterruptedException { // wait
        NumberOfAvaliableSpots--;
        if(NumberOfAvaliableSpots < 0)
        {
            wait();
        }
    }
    public synchronized  void V()
    {
        NumberOfAvaliableSpots++;
        if(NumberOfAvaliableSpots <= 0)
        {
            notify();
        }
    }
}
