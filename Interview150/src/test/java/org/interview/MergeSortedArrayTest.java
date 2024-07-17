package org.interview;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortedArrayTest {

    @Test
    public void firstTest() {
        MergeSortedArray mock = new MergeSortedArray();
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        int m = 3;
        int n = 3;

        mock.merge(nums1, m, nums2, n);
        Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }

    @Test
    public void secondTest() {
        MergeSortedArray mock = new MergeSortedArray();
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{};
        int m = 1;
        int n = 0;

        mock.merge(nums1, m, nums2, n);
        Assert.assertArrayEquals(new int[]{1}, nums1);
    }

    @Test
    public void thirdTest() {
        MergeSortedArray mock = new MergeSortedArray();
        int[] nums1 = new int[]{0};
        int[] nums2 = new int[]{1};
        int m = 0;
        int n = 1;

        mock.merge(nums1, m, nums2, n);
        Assert.assertArrayEquals(new int[]{1}, nums1);
    }

    @Test
    public void fourthTest() {
        MergeSortedArray mock = new MergeSortedArray();
        int[] nums1 = new int[]{4, 5, 6, 0, 0, 0};
        int[] nums2 = new int[]{1, 2, 3};
        int m = 3;
        int n = 3;

        mock.merge(nums1, m, nums2, n);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, nums1);
    }
}
