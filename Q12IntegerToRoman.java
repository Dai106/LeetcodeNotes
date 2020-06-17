
public class Q12IntegerToRoman {
	public static void main(String[] args) {
		String roman = bruteForce(1994);
		System.out.println(roman);
	}
	public static String bruteForce(int num) {
		String roman = "";
		
		while(num >= 1000) {
			roman += "M";
			num -= 1000;
		}
		
		if(900 <= num) {
			roman += "CM";
			num -= 900;
		}
		
		while(num >= 500) {
			roman += "D";
			num -= 500;
		}
		
		if(num >= 400) {
			roman += "CD";
			num -= 400;
		}
		
		while(num >= 100) {
			roman += "C";
			num -= 100;
		}
		
		if(num >= 90) {
			roman += "XC";
			num -= 90;
		}
		
		while(num >= 50) {
			roman += "L";
			num -= 50;
		}
		
		if(num >= 40) {
			roman += "XL";
			num -= 40;
		}
		
		while(num >= 10) {
			roman += "X";
			num -= 10;
		}
		
		if(num == 9) {
			roman += "IX";
			return roman;
		}
		
		while(num >= 5) {
			roman += "V";
			num -= 5;
		}
		
		if(num == 4) {
			roman += "IV";
			return roman;
		}
		
		while(num >= 1) {
			roman += "I";
			num --;
		}
		
		return roman;
	}
	
	// by using arrays
	public static String byArray(int num) {
		String[] thousands = {"", "M", "MM", "MMM"};
		String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		
		return thousands[num/1000] +
				hundreds[(num%1000) /100] +
				tens[(num % 100) /10]+
				units[num %10];
	

	}
}
















