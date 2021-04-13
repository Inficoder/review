public int climbStairs(String date){
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(1, 31);
    map.put(2, 28);
    map.put(3, 31);
    map.put(4, 30);
    map.put(5, 31);
    map.put(6, 30);
    map.put(7, 31);
    map.put(8, 31);
    map.put(9, 30);
    map.put(10, 31);
    map.put(11, 30);
    map.put(12, 31);
    String[] strArr = date.split("-");
    int year = Integer.parseInt(strArr[0]);
    int month = Integer.parseInt(strArr[1]);
    int day = Integer.parseInt(strArr[2]);
    int res = 0;
    if(year % 4 == 0) res ++;
    for(int i = 1; i < month; i ++){
        res += map.get(i);
    }
    res += day;
    return res;
}