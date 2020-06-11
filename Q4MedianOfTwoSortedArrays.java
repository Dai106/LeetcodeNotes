
public class Q4MedianOfTwoSortedArrays {
	public static void main(String[] args) {
		int[] nums1 = {4,5,6,8,9};
		int[] nums2 = {};
		double result = bySplitArray(nums1, nums2);
		System.out.println(result);
	}
	
	// try to do in O(m+n)
	public static double bruteForce(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		// pointer for nums1
		int i = 0;
		// pointer for nums2
		int j = 0;
		// pointer for merge
		int k = 0;
		int[] merge = new int[m+n];
		
		// if one of the lists is empty
		if(nums1 == null || nums1.length == 0) {
			// if even
			if(n%2 == 0) {
				int sum = nums2[n/2 - 1] + nums2[n/2];
				return (double)sum/2.0;
			}else {
				// odd
				return nums2[n/2];
			}
		}
		if(nums2 == null || nums2.length == 0) {
			if(m % 2 == 0) {
				int sum = nums1[m/2 - 1] + nums1[m/2];
				return (double) sum / 2.0;
			}else {
				return nums1[m/2];
			}
		}
		
		while(i < m && j < n) {
			if(nums1[i] <= nums2[j]) {
				merge[k] = nums1[i];
				i ++;
				k ++;
			}else {
				merge[k] = nums2[j];
				j++;
				k++;
			}
		}
		
		while(i < m) {
			merge[k++] = nums1[i++];
		}
		while(j < n) {
			merge[k++] = nums2[j++];
		}
		
		// find the return value
		if( (m+n)%2 == 0 ) {
			int sum = merge[ (m+n)/2 -1] + merge[ (m+n)/2 ];
			return (double) sum / 2.0;
		}else {
			return merge[ (m+n)/2 ];
		}
	}
	
	
	public static double twoVariables(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		
		// if one of the lists is empty
		if(nums1 == null || nums1.length == 0) {
			// if even
			if(n%2 == 0) {
				int sum = nums2[n/2 - 1] + nums2[n/2];
				return (double)sum/2.0;
			}else {
				// odd
				return nums2[n/2];
			}
		}
		if(nums2 == null || nums2.length == 0) {
			if(m % 2 == 0) {
				int sum = nums1[m/2 - 1] + nums1[m/2];
				return (double) sum / 2.0;
			}else {
				return nums1[m/2];
			}
		}
		
		// pointer for nums1
		int i = 0;
		// pointer for nums2
		int j = 0;
		// 2 variable to store the current value
		int left = 0;
		int right = 0;
		
		// keep updating until (m+n)/2
		for(int k = 0; k<= (m+n)/2; k++) {
			// update left = right
			left = right;
			
			// find the smaller one
			if( i < m && (j>=n || nums1[i] <= nums2[j])) {
				right = nums1[i];
				i++;
			}else {
				right = nums2[j];
				j++;
			}
		}
		
		// now theck if the total length is even or odd
		if( (m+n)%2 == 0 ) {
			return (left + right) / 2.0;
		}else {
			return right;
		}
	}
	
	// do it in O(log(min(m,n))) by splitting the array
	public static double bySplitArray(int[] nums1, int[] nums2) {
		// make sure that nums1 has the shorter length
		int m = nums1.length;
		int n = nums2.length;
		if(m > n) {
			return bySplitArray(nums2, nums1);
		}
		
		// head and tail for partition nums1
		// for m-1 elements, there are m partition places
		int head = 0;
		int tail = m;
		
		// variables
		int maxLeftX, minRightX, maxLeftY, minRightY;
		
		// get the partition, keep looping until head > tail
		while(head <= tail) {
			
			// position of partition
			int partitionX = (head + tail) / 2;
			int partitionY = (m + n + 1)/2 - partitionX;
			
			// initialize the variables
			// case 3 and case 4, check for boundary case
			// for nums1
			if(partitionX == 0) {
				maxLeftX = Integer.MIN_VALUE;
			}else {
				maxLeftX = nums1[partitionX - 1];
			}
			if(partitionX == m) {
				minRightX = Integer.MAX_VALUE;
			}else {
				minRightX = nums1[partitionX];
			}
			
			// for nums2
			if(partitionY == 0) {
				maxLeftY = Integer.MIN_VALUE;
			}else {
				maxLeftY = nums2[partitionY - 1];
			}
			if(partitionY == n) {
				minRightY = Integer.MAX_VALUE;
			}else {
				minRightY = nums2[partitionY];
			}
			
			//check and update
			// if we find the correct partition
			if(maxLeftX <= minRightY && maxLeftY <= minRightX) {
				// if m + n is even
				if( (m+n)%2 == 0 ) {
					int sum = Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY);
					return sum/2.0;
				}else {
					return Math.max(maxLeftX, maxLeftY);
				}
			}else if (maxLeftX > minRightY) {
				// case 1, we are in too right, need to go to left
				tail = partitionX - 1;
			}else {
				// case 2, we are in too left, need to go to right
				head = partitionX + 1;
			}
		}
	    // others case, throw exception
        throw new IllegalArgumentException();
		
	}
}
