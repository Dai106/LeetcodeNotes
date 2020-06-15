
public class Q7ReverseInteger {
	public static void main(String[] args) {
		int result = reverse(1534236469);
		System.out.println(result);
	}
	public static int reverse(int x) {
		// since the reversed integer may out of range of int, 
		// so we cannot use int to represent it
		/**
		int result = 0;
		int abs = Math.abs(x);
		while(abs > 0) {
			result = result * 10 + abs%10;
			abs = abs/10;
		}
		if(result < -Math.pow(2, 31) || result > Math.pow(2,31)-1) {
			return 0;
		}
		if(x < 0) {
			result = 0-result;
		}
		return result;
		*/
		
		long result = 0;
		while(x != 0) {
			result = result * 10 + x % 10;
			x = x/10;
		}
		if(result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
			return 0;
		}else {
			return (int)result;
		}
	}
}
