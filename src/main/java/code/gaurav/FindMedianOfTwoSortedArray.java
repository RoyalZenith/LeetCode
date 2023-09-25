package code.gaurav;

public class FindMedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //1 | 3
        //|2
        // O(log(m+n))
        // both empty -> 0
        // one empty -> mid of other arr if odd otherwise mid two ele/2
        int len1 = nums1.length;
        int len2 = nums2.length;

        if(len1 > len2) return findMedianSortedArrays(nums2,nums1);
        int low = 0;
        int high = len1;
        int halfEle = (len1+len2+1)/2;
        while(low <= high){
            //first array
            int mid = low + (high-low)/2;
            int left1 = mid-1<0?Integer.MIN_VALUE:nums1[mid-1];
            int right1 = mid <len1 ? nums1[mid] : Integer.MAX_VALUE;
            //second array
            int mid2 = halfEle - mid;
            int left2 = mid2-1<0?Integer.MIN_VALUE:nums2[mid2-1];
            int right2 = mid2 <len2 ? nums2[mid2] : Integer.MAX_VALUE;

            if(left1 <= right2 && left2 <= right1){

            }else if(left1 > right2){
                high = mid-1;
            }else if(left2 > right1){
                low = mid+1;
            }else{
                if(((len1+len2) & 1) == 0){
                    return (double)(Math.max(left1,left2) + Math.min(right1,right2))/2.0;
                }else{
                    return (double) Math.max(left1,left2);
                }
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        FindMedianOfTwoSortedArray findMedianOfTwoSortedArray = new FindMedianOfTwoSortedArray();
        System.out.println(findMedianOfTwoSortedArray.findMedianSortedArrays(new int[]{1},new int[]{1}));
    }
}
