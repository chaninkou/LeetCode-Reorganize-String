package leetcode767;

import java.util.HashMap;
import java.util.PriorityQueue;

public class ReorganizeStringSolution {
    // Using priority queue with lambda
    public String reorganizeString(String s) {
    	// Keep count of how many counts per characters
        HashMap<Character, Integer> map = new HashMap<>();
        
        // Count the number of times each character appear
        for(char letter : s.toCharArray()){
            map.put(letter, map.getOrDefault(letter, 0) + 1);
        }
        
        // (a,b) -> map.get(b) - map.get(a) this will make it into a maxheap instead of min heap
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));
        
        // Add all the keys based on their number of counts
        maxHeap.addAll(map.keySet());
        
        // Since adding string is really costly, stringbuilder will be the best
        StringBuilder sb = new StringBuilder();
        
        // While there are at least two character to place
        while(maxHeap.size() > 1){
        	// Will get the root of the max heap (first biggest)
            char firstBiggest = maxHeap.remove();
            
            // Will get the root of the max heap again (second biggest)
            char secondBiggest = maxHeap.remove();
            
            sb.append(firstBiggest);
            sb.append(secondBiggest);
            
            map.put(firstBiggest, map.get(firstBiggest) - 1);
            map.put(secondBiggest, map.get(secondBiggest) - 1);
            
            if(map.get(firstBiggest) > 0){
                maxHeap.add(firstBiggest);
            }
            
            if(map.get(secondBiggest) > 0){
                maxHeap.add(secondBiggest);
            }
        }
        
        // There will be case like odd number of characters ex: aab
        // Or its just aaaaaaaa
        if(!maxHeap.isEmpty()){
            char lastWord = maxHeap.remove();
            
            // There could only be one left or else it fail
            if(map.get(lastWord) > 1){
                return "";
            }
            
            sb.append(lastWord);
        }
        
        return sb.toString();
    }
	
    // Bucket sort
    public String reorganizeString2(String s) {
        // Return original string if length is 1 or 0 or if string is null
        if(s == null || s.length() < 2){
           return s; 
        } 
        
        // An array of 26 since abc..z
        int[] bucket = new int[26];
        
        // ++ will make add how many times any letter appear
        for(int i = 0; i < s.length(); i++){
            bucket[s.charAt(i) - 'a']++;
        }
        
        // Init a max
        int max = 0;
        
        // Getting the letter that appear the most from the left to the right (if they are the same number, nothing happen)
        for(int i = 0; i < bucket.length; i++){
            if(bucket[i] > bucket[max]){
                max = i;
            }
        }
        
        // If it is not possible, then return empty string
        if(bucket[max] > (s.length() + 1) / 2){
            return "";
        }
        
        // Create an array with the length
        char[] ans = new char[s.length()];
        
        // Start from 0
        int start = 0;
        
        // i + 2 every time
        for(int i = 0; i < ans.length; i = i + 2){
            if(bucket[max] > 0){
                // Adding the letter to the array, use 'a' since it - 'a' earlier
                ans[i] = (char)('a' + max);
                
                // Decrease the number of time it appear
                bucket[max]--;
            }else{
                // Part 2
                while(bucket[start] == 0){
                    start++;
                }
                
                ans[i] = (char)('a' + start);
                
                // Decrease the number of time it appear
                bucket[start]--;
            }
        }
        
        // if aab, then it will be b, if aabcc, then it will be cc
        for(int i = 1; i < ans.length; i = i + 2){
            while(bucket[start] == 0){
                start++;
            }
            
            ans[i] = (char)('a' + start);
            
            // Decrease the number of time it appear
            bucket[start]--;
        }
        
        return new String(ans);
    }
}
