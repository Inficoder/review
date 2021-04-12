public int numWays(int n, int k){
    if(n == 0) return 0;
    if(n == 1) return k;
    return (numWays(n - 1) * (k - 1)) + (numsWays(n - 2) * (k - 1));
}

public static int[] compareNums(String str, char c){
    int[] answer = new int[str.length()];
    Arrays.fill(answer, length);
    for(int i = 0; i < answer.length; i ++){
        if(str.charAt(i) == c){
            answer[i] == 0;
            continue;
        }
        if(i != 0)
            answer[i] = Math.min(answer[i], answer[i - 1] + 1);
    }
    
    for(int i = answer.length - 1; i >= 0; i --){
        if(str.charAt(i) == c){
            answer[i] == 0;
            continue;
        }
        if(i != answer.length - 1)
            answer[i] = Math.min(answer[i], answer[i + 1] + 1);
    }
    
    return answer;
}