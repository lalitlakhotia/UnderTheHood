A twin prime is a prime number that differs from another prime number by two. Except for the pair (2, 3), 
this is the smallest possible difference between two primes. Some examples of twin prime pairs are (3, 5), (5, 7), 
(11, 13), (17, 19), (29, 31) and (41, 43). Sometimes the term twin prime is used for a pair of twin primes; an 
alternative name for this is prime twin.// http://ideone.com/09QPNz


public class TwinPrime {
	
	public static void main(String a[])
	{
		boolean flag=false; int i,n=10024;
		boolean k=false;
		for(i=3;n>0;i+=2)
		{
				k=isPRIME(i)==true && isPRIME(i+2)==true;
				if(k)
					n--;
				//System.out.println( "" + i + "" + (i+2));
		}
		System.out.println("Twins Prime are:" + (i-2) + ":" + i);
	}
	
	static boolean isPRIME(int x)
	{
		int i=0;
		if(x <= 1)
		return false;
		int s = (int)Math.sqrt(x);
		for(i = 2; i <= s; i++)
		if(x%i == 0)
		{	
			//System.out.println("" + x + "is prime" );
			return false;
		}
		return true;
	}


}

Run Here http://ideone.com/TeJKSv
