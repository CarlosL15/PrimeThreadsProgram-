import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

public class Prime extends Thread {

	
	int id;
    int max;
    int min;
    
    public Prime(int id, int min, int max)
    {
        this.id = id;
        this.min = min;
        this.max = max;

    }


    public int countPrimes(int min, int max)
    {
        int count = 0;
        for (int i = min; i<=max; i++)
            if (isPrime(i))
                count++;
        return count;
    }

    public boolean isPrime(int x)
    {
        if (x < 1)
        return false;

        int max = (int)Math.sqrt(x);
        for(int i = 2; i <= max; i++)
        if ( x % i == 0 )
        return false;
        return true;

    }

    static int n;
    static int totalPrimes;

    public static void main(String[] args) throws InterruptedException
    {

        String num = JOptionPane.showInputDialog("Enter a value greater than 2");
        n = Integer.parseInt(num);

        if(n < 3)
        {
            System.out.println("Value entered is in-valid");
            System.exit(1);
        }

        Prime[] pr = new Prime[ThreadLocalRandom.current().nextInt(1,5)];

        System.out.println("------run------");
        System.out.println("# of Threads = " + pr.length);

        int size = n/pr.length;

        for(int i = 0; i < pr.length; i++)
            pr[i] = new Prime(i,size*i+1, size*(i+1));
        for(int i = 0; i < pr.length; i++)
            pr[i].start();
        for(int i = 0; i < pr.length; i++)
            pr[i].join();

        System.out.println("Total Primes = " + totalPrimes);

    }

    public void run()
    {
        if(min == 1)
            min = 3;

        long startTime = System.currentTimeMillis();

        int count = countPrimes(min,max);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Thread " + (id+1) + " counted " + count + " primes in " + (elapsedTime/1000.0) + " seconds.");
        totalPrimes = totalPrimes + count;
    }

}
