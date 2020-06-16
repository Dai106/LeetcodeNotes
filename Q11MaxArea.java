
public class Q11MaxArea {
	
	public static void main(String[] args) {
		int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
		int maxarea = twoPointer(height);
		System.out.println(maxarea);
	}
	
	public static int bruteForce(int[] height) {
		int maxarea = 0;
		for(int i = 0; i<height.length; i++) {
			for(int j = 0; j < height.length; j++) {
				maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j-i));
			}
		}
		return maxarea;
	}
	
	// If we try to move the pointer at the longer line inwards, 
	// we won't gain any increase in area, since it is limited by the shorter line. 
	// But moving the shorter line's pointer could turn out to be beneficial, 
	// as per the same argument, despite the reduction in the width.
	
	public static int twoPointer(int[] height) {
		int maxarea = 0;
		int left = 0; 
		int right = height.length-1;
		while(left < right) {
			maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * (right - left));
			if(height[left] <= height[right]) {
				left ++;
			}else {
				right --;
			}
		}
		return maxarea;
	}
}
