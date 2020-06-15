
public class Q9PalindromeNum {
	public static void main(String[] args) {
		boolean result = halfCompare(121);
		System.out.println(result);
	}
	//full compare
	public static boolean fullCompare(int x) {
		
		// case: if 0<=x<=9
		if(0<=x && x<=9) {
			return true;
		}
		//case: if x is negative, false
		if(x < 0) {
			return false;
		}
		// else, get the reverse int and compare if the 2 int are the same
		long reverse = 0;
		int t = x;
		while(t != 0) {
			reverse = reverse * 10 + t % 10;
			t = t/10;
		}
		if(x == reverse) {
			return true;
		}else {
			return false;
		}
	}
	
	// half compare
	// much faster
	public static boolean halfCompare(int x) {
		if(x<0 || (x!=0 && x%10==0)) {
			return false;
		}
		int rev = 0;
		while(x > rev) {
			rev = rev * 10 + x % 10;
			x = x/10;
		}
		// even num of digits or odd num of digits
		return (x == rev || x == rev/10);
	}
}
