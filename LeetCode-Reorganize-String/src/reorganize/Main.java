package reorganize;

public class Main {
	public static void main(String[] args){
		String s = "aab";
		
		System.out.println("Input: " + s);
		
		ReorganizeStringSolution answer = new ReorganizeStringSolution();
		
		System.out.println("Solution: " + answer.reorganizeString(s));
	}
}
