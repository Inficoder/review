public static boolean checkPerfectNumber(int num) {
    int res = 1;
    for(int i = 2; i < num; i ++){
        if(num % i == 0)
            res += i;
    }
    return num == res;
}