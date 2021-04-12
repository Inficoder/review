public int climbStairs(int n){
    int i = 1;
    int j = 2;
    while(n > 2){
        i ,j = j, i + j;
        n --;
    }
    System.out:.println(j);
}