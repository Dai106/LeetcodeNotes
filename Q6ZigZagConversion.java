import java.util.List;
import java.util.ArrayList;
public class Q6ZigZagConversion {
	public static void main(String[] args) {
		String s = "PAYPALISHIRING";
		String result = visitByRows(s,4);
		System.out.println(result);
	}
	public static String BFcreat(String s, int numRows) {
		//special case, if numRows = 1;
		if(numRows == 1) {
			return s;
		}
		// initialize the char matrix
		int numColumns;
		int remainder;
		
		numColumns = s.length() / (2*numRows - 2) * (numRows - 1);
		if(numColumns == 0) {
			remainder = s.length();
		}else {
			remainder = s.length() % (2*numRows - 2);
		}
		if(remainder >0) {
			numColumns ++;
			if(remainder > numRows) {
				numColumns += remainder - numRows + 1;
			}
		}
		
		
		
		char[][] z = new char[numRows][numColumns];
		
		// fill in the matrix
		int counter = 0;
		for(int j = 0; j < numColumns; j++) {
			if(counter >= s.length()) {
				break;
			}
			int group = j%(numRows - 1);
			if(group == 0) {
				for(int i = 0; i<numRows; i++) {
					if(counter >= s.length()) {
						break;
					}
					z[i][j] = s.charAt(counter);
					counter ++;
				}
			}else {
				z[numRows-1-group][j] = s.charAt(counter);
				counter ++;
			}
		}
		String result = "";
		for(int i = 0; i<numRows; i++) {
			for(int j = 0; j<numColumns; j++) {
				if(z[i][j] != 0) {
					result += z[i][j];
				}
			}
		}
		return result;
	}
	
	public static String visitByRows(String s, int numRows) {
		//note:
		// char in row 0 are located at indexes k(2*numRows-2)
		// char in row numRow-1 are located at indexes k(2*numRows-2)+numRows - 1
		//char in inner row i are located at indexes
		// k(2*numRows - 2) + i and (k+1)(2*numRows - 2) - i
		
		//special case if numRows = 1
		if(numRows == 1) {
			return s;
		}
		
		String result = "";
		int n = s.length();
		int cycleLen = 2*numRows - 2;
		
		// the chars in the first column
		for(int i = 0; i<numRows; i++) {
			// increase by the cycleLen
			for(int j = 0; j+i<n; j+=cycleLen) {
				// add the first char for each row in every cycle
				result += s.charAt(j+i);
				// remove the top row and bottom row
				// add the second char for the rest of rows
				if(i!= 0 && i != numRows-1 && j+cycleLen-i < n) {
					result += s.charAt(j + cycleLen - i);
				}
			}
		}
		return result;
	}
	
	
	public static String withList(String s, int numRows) {
		
		// special case if numRows = 1
		if(numRows == 1) {
			return s;
		}
		
		List<StringBuilder> rows = new ArrayList<>();
		for(int i = 0; i< Math.min(numRows,  s.length()); i++) {
			rows.add(new StringBuilder());
		}
			int curRow = 0;
			boolean goingDown = false;
			
			for(char c: s.toCharArray()) {
				rows.get(curRow).append(c);
				if(curRow == 0 || curRow == numRows-1) {
					goingDown = ! goingDown;
				}
				if(goingDown) {
					curRow ++;
				}else {
					curRow --;
				}
			}
			
			StringBuilder ret = new StringBuilder();
			for(StringBuilder row: rows) {
				ret.append(row);
			}
			return ret.toString();
		
	}
	

}
