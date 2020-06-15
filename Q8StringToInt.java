// String to integer
public class Q8StringToInt {
	public static void main(String[] artgs) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		int result = myAtoi("  ");
		System.out.println(result);
	}
	public static int myAtoi(String str) {
	/**
		// special cases 
		if (str == null || str.contentEquals("")) {
			return 0;
		}
	*/
		char[] a = str.toCharArray();
		if (a.length == 0){
			return 0;
		}
		// find the first non whitespace char
		int index = 0;
		while( (index < a.length) && (a[index] == ' ')) {
			index ++;
		}
		
		// if no non-whitespace char
		if(index >= a.length) {
			return 0;
		}
		
		char sign = '+';
		long result = 0;
		if(a[index] == '-') {
			sign = '-';
			index ++;
		}else if(a[index] == '+' ) {
			index ++;
		}else if( (a[index]<='0') && (a[index]>='9') ) {
			return 0;
		}
		while( (index < a.length) && ('0' <= a[index]) && (a[index] <= '9') ) {	
			 if( (result * 10 + a[index] - 48) > Integer.MAX_VALUE) {
				 if( sign == '-' ) {
					 return Integer.MIN_VALUE;
				 }
				 return Integer.MAX_VALUE;
			 }else {
				result = result * 10 + a[index] - 48; 
				index ++;
			}
		}
		
		if( sign == '-' ) {
			result = 0-result;
		}
		return (int)result;
	}
}

