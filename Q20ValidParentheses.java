import java.util.Stack;

public class Q20ValidParentheses {
	public static void main(String[] args) {
		String s = "]";
		System.out.println(usingStack(s));
	}
	
	public static boolean usingStack(String s) {
		//special case
		if(s == null || s.length()==0  ) {
			return true;
		}
		
		Stack<Character> st = new Stack<>();
		
		for(int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);
			if( c == '(' || c == '[' || c == '{') {
				st.push(c);
			}else if (c == ')' || c == ']' || c == '}') {
				if(st.isEmpty()) {
					return false;
				}
				char p = st.pop();
				if(p == '(' && c == ')') {
					continue;
				}else if(p == '[' && c == ']') {
					continue;
				}else if(p == '{' && c == '}') {
					continue;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		
		// now check if the stack is empty
		if(st.isEmpty()) {
			return true;
		}else {
			return false;
		}
		
	}
}
