package reorganize;

public class ReorganizeStringSolution {
    public String reorganizeString(String s) {
        // Return itself if length is 1 or 0 or if string is null
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
