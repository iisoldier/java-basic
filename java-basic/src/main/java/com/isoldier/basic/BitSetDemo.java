package com.isoldier.basic;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * 1. Attention
 * Basically it seems to me that
 * size() is kept because of legacy reasons and there's no real use for it.
 * length() which gives you the largest index at which a bit is set.
 */
public class BitSetDemo {

	public static void main(String[] args) {

		basicTest();
		statisticalTest();
	}


	/**
	 * output 23 5 true 5
	 *
	 */
	public static void basicTest() {

		int[] array = new int[] { 1, 2, 3, 22, 5 };

		BitSet bitSet = new BitSet();
		for (int i = 0; i < array.length; i++) {
			bitSet.set(array[i]);
		}

		System.out.println(bitSet.length());
		//return the number of bits set to true in this {@code BitSet}
		System.out.println(bitSet.cardinality());
		// check if true
		System.out.println(bitSet.get(3));
		// find previous bit
		System.out.println(bitSet.previousSetBit(11));
	}


	/**
	 * Simple statistics
	 */
	public static void statisticalTest() {
		Random random=new Random();

		List<Integer> list=new ArrayList<>();
		int maxNum = 1000;
		for(int i=0;i<maxNum;i++) {
			int randomResult=random.nextInt(maxNum);
			list.add(randomResult);
		}

		BitSet bitSet=new BitSet();
		for(int i=0;i<maxNum;i++) {
			bitSet.set(list.get(i));

		}
		System.out.println("bitSet length"+bitSet.length());
		System.out.println("random size" + list.size());
		System.out.println("unique count"+bitSet.cardinality());
	}

}
