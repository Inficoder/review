package com.buaa.review;

import com.buaa.review.java.annotation.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;


@SpringBootTest
class ReviewNApplicationTests {

    @Resource
    Student student;

    @Test
    void contextLoads() {

    }

    public int findKthLargest(int[] nums, int k) {
        return qSort(nums, 0, nums.length - 1, nums.length - k);
    }

    public int qSort(int[] nums, int start, int end, int k){
        int i = start, j = end;
        int flag = nums[start];
        while(i < j){
            while(i < j && nums[j] > flag) j --;
            while(i < j && nums[i] < flag) i ++;
            if(nums[i] == nums[j] && i < j) i ++;
            else{
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        if(nums[i] == nums[k]) return nums[i];
        else if(nums[i] > nums[k]) return qSort(nums, 0, i - 1, k);
        else return qSort(nums, i + 1, end, k);
    }

    public static int[] quickSort(int[] arr, int start, int end){
        int i = start, j = end;
        int flag = arr[start];
        while(i < j){
            while(i < j && arr[j] > flag) j --;
            while(i < j && arr[i] < flag) i ++;
            if ((arr[i] == arr[j])&&(i<j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            for (int i1 : arr) {
                System.out.print(i1);
            }
        }

        System.out.println();
        if (i - 1 > start) arr = quickSort(arr, start, i-1);
        if (j + 1 < end) arr = quickSort(arr, j+1, end);
        return arr;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[length][length];
        dp[0][0] = triangle.get(0).get(0);
        int res = 0;
        //dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                if(i == length - 1) res = Math.min(res, dp[i][j]);
            }
        }
        return res;
    }

    public int climbStairs(int n){
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        while(i < n - 2){
            int j = i + 1, k = n - 1;
            if(nums[i] > 0){
                return res;
            }else{
                while(j < k){
                    System.out.println(i + "-" + j + "-" + k);
                    if(nums[j] + nums[k] == -nums[i]){
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        j++;k--;
                    }else if(nums[j] + nums[k] > -nums[i]){
                        k--;
                    }else{
                        j++;
                    }
                }
            }
            i++;
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        return 0;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        int i = 0, j = 0;
        while(j < n){
            while(nums2[j] >= nums1[i] && i < m){
                i++; //2
            }
            for(int k = i; k < m; k ++){
                nums1[k + 1] = nums1[k];
            }
            nums1[i] = nums2[j];
            m++;
            j++;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        if(k == 0) return head;
        ListNode fast = head, slow = head;
        //1,2,3,4,5   2
        while(k > 0){
            fast = fast.next;
            k--;
            if(fast == null && k != 0){
                fast = head;
            }
        }
        if(fast != null){
            while(fast.next != null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        // 1 / 1
        // 1 2 3 4 5 / 2
        ListNode res = slow.next;
        if(fast != null){
            fast.next = head;
            slow.next = null;
        }
        if(res == null) return head;
        return res;
    }

    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        ListNode tHead = head;
        ListNode quick = head.next, slow = head;
        while(quick != null){
            quick = quick.next;
            if(quick == null) return null;
            quick = quick.next;
            slow = slow.next;
            if(quick == slow) break;
        }
        if(slow == null) return null;
        slow = slow.next;
        while(tHead != slow){
            if(tHead == null || slow == null) return null;
            tHead = tHead.next;
            slow = slow.next;
        }
        return tHead;
    }

    public int maxSubArray(int[] nums) {
        int ans = nums[0], s = 0;
        for(int i : nums) {
            s += i;
            if(s > ans)
                ans = s;
            if(s < 0)
                s = 0;
        }
        return ans;
    }

    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        String res = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder sbTemp = new StringBuilder();
            String s = multiplyChar(num1, num2.charAt(i));
            int count = num2.length() - 1 - i;
            sbTemp.append(s);
            while(count > 0){
                sbTemp.append("0");
                count --;
            }
            res = addStrings(sbTemp.toString(), res);
//            sbTemp.delete(0, sbTemp.length());
        }
        return res;
    }

    public String multiplyChar(String num1, char c){
        StringBuilder res = new StringBuilder();
        int length = num1.length() - 1;
        int carry = 0;
        while(length >= 0 || carry != 0){
            int temp = 0;
            if(length >= 0) temp += (num1.charAt(length) - '0') * (c - '0');
            temp += carry;
            carry = temp / 10;
            res.append(temp % 10);
            length--;
        }
        return res.reverse().toString();
    }

    public String addStrings(String num1, String num2) {
        int nL1 = num1.length() - 1;
        int nL2 = num2.length() - 1;
        StringBuilder res = new StringBuilder();
        int carry = 0 ;
        while(nL1 >= 0 || nL2 >= 0 || carry != 0){
            int temp = 0;
            temp += carry;
            if(nL1 >= 0) temp += num1.charAt(nL1) - '0';
            if(nL2 >= 0) temp += num2.charAt(nL2) - '0';
            carry = temp / 10;
            res.append(temp % 10);
            nL2 --; nL1 --;
        }
        return res.reverse().toString();
    }

    public int search(int[] nums, int target) {
        int aLenth = nums.length;
        int left = 0, right = aLenth - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target) return mid;
            if(nums[0] <= nums[mid]){
                if(nums[0] <= target && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else{
                if(target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1; //i 当前索引， j 后续指针
        while(i < nums.length - 1 && j < nums.length){
            while(nums[j] == nums[i] && j < nums.length - 1) j ++;
            i++;
            nums[i] = nums[j];
            j++;
        }
        return i;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
//            if(i == 0 || nums[i] != nums[i - 1]){
                for (int j = i + 1; j < nums.length; j++) {
                    if(j == i + 1 ||  nums[j] != nums[j - 1]){
                        for (int k = j + 1; k < nums.length; k++) {
                            if(k == j + 1 || nums[k] != nums[k - 1]){
                                if(nums[i] + nums[j] + nums[k] == 0){
                                    List<Integer> temp = new ArrayList<>();
                                    temp.add(nums[i]);
                                    temp.add(nums[j]);
                                    temp.add(nums[k]);
                                    res.add(temp);
                                    break;
                                }
                            }
                        }
                    }
//                }
            }
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return null;
        String publicStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int lTemp = 0;
            for (int j = 0; j < publicStr.length() && j < strs[i].length(); j++) {
                if(strs[i].charAt(j) == publicStr.charAt(j)){
                    lTemp++;
                }else{
                    break;
                }
            }
            if(lTemp == 0) return "";
            if(lTemp < publicStr.length()) {
                publicStr = publicStr.substring(0, lTemp);
            }
        }
        return publicStr;
    }

    public int reverse(int x) {
        int res = 0;
        while(x > 0){
            int temp = x%10;
            res = res*10 + temp;
            x = x / 10;
        }
        return res;
    }

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    public boolean isValid(String s) {
        HashMap<Character, Character> kv = new HashMap<>();
        kv.put(')', '(');
        kv.put(']', '[');
        kv.put('}', '{');
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char cTemp = s.charAt(i);
            if(stack.isEmpty()){
                if(kv.containsKey(cTemp)) return false;
                else{
                    stack.push(cTemp);
                }
            }else{
                if(stack.peek() == kv.get(cTemp)){
                    stack.pop();
                    continue;
                }else{
                    stack.push(cTemp);
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        HashMap<Character, Character> kv = new HashMap<>();
//        kv.put('(', ')');
        kv.put(')', '(');
//        kv.put('[', ']');
        kv.put(']', '[');
//        kv.put('{', '}');
        kv.put('}', '{');
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cTemp = s.charAt(i);
            if(cTemp == ')' || cTemp == ']' || cTemp == '}'){
                if(!map.containsKey(kv.get(cTemp))) return false;
                else{
                    map.put(kv.get(cTemp), map.get(kv.get(cTemp)) - 1);
                    if(map.get(kv.get(cTemp)) == 0) map.remove(kv.get(cTemp));
                }
            }else{
                map.put(cTemp, map.getOrDefault(cTemp, 0) + 1);
            }
        }
        return map.size() == 0;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode head = res;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                res.next = new ListNode(l1.val);
                l1 = l1.next;
            }else{
                res.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            res = res.next;
            if(l1 == null && l2 != null){
                res.next = l2;
            }
            if(l1 != null && l2 == null){
                res.next = l1;
            }
        }
        return head.next;
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(2, Arrays.asList("a", "b", "c"));
        map.put(3, Arrays.asList("d", "e", "f"));
        map.put(4, Arrays.asList("g", "h", "i"));
        map.put(5, Arrays.asList("j", "k", "l"));
        map.put(6, Arrays.asList("m", "n", "o"));
        map.put(7, Arrays.asList("p", "q", "r", "s"));
        map.put(8, Arrays.asList("t", "u", "v"));
        map.put(9, Arrays.asList("w", "x", "y", "z"));
        for (int i = 0; i < digits.length(); i++) {

            for (String s : map.get((int) digits.charAt(i))) {

            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(i == 0 || nums[i] != nums[i - 1]){
                int third = 0;
                for (int j = i + 1; j < nums.length; j++){
//                    int firstSecond = nums[i] + nums[j];
                    third = j + 1;

                }
//                for (int j = i + 1; j < nums.length; j++) {
//                    if(j == i + 1 ||  nums[j] != nums[j - 1]){
//                        for (int k = j + 1; k < nums.length; k++) {
//                            if(k == j + 1 || nums[k] != nums[k - 1]){
//                                if(nums[i] + nums[j] + nums[k] == 0){
//                                    List<Integer> temp = new ArrayList<>();
//                                    temp.add(nums[i]);
//                                    temp.add(nums[j]);
//                                    temp.add(nums[k]);
//                                    res.add(temp);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
            }
        }
        return res;
    }

    public int maxArea(int[] height) {

        int m1 = 0, m1Index = 0, m2 = 0, m2Index = 0;

        for (int i = 0; i < height.length; i++) {
            if(m1 < height[i]){
                m1 = height[i]; m1Index = i;
            }
        }

        for (int i = 0; i < height.length; i++) {
            if(m2 < height[i] && i != m1Index){
                m2 = height[i]; m2Index = i;
            }
        }
        int left = Math.min(m1Index, m2Index);
        int right = Math.max(m1Index, m2Index);
        int max = (right - left) * Math.min(height[left], height[right]);
        int maxLeft = left, maxRight = right;
        while(left > 0){
            left -- ;
            int temp = (right - left) * Math.min(height[left], height[right]);
            if(temp > max){
                max = temp;
                maxLeft = left;
            }
        }
        while(right < height.length - 1){
            right ++;
            int temp = (right - maxLeft) * Math.min(height[maxLeft], height[right]);
            if(temp > max){
                max = temp;
                maxRight = right;
            }
        }
        System.out.println(maxRight + " " +maxLeft);
        return (maxRight - maxLeft) * Math.min(height[maxLeft], height[maxRight]);
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 1) return 1;
        int res = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(list.contains(s.charAt(i))){
                res = Math.max(res,list.size());
                for (Character character : list) {
                    list.remove(character);
                    if(character == s.charAt(i))
                        break;
                }
                list.add(s.charAt(i));
                continue;
            }
            list.add(s.charAt(i));
        }
        return Math.max(res, list.size());
    }

    public int shipWithinDays(int[] weights, int D) {
        for (int i = 0; i < weights.length; i++) {

        }
        int res = 0;
        return res;
    }

    public boolean makesquare(int[] matchsticks) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int avg = 0;
        for (int matchstick : matchsticks) {
            map.put(matchstick, map.getOrDefault(matchstick, 0) + 1);
            avg += matchstick;
        }
        if(avg % 4 != 0) return false;
        avg /= 4;
        int temp = avg;
        return true;
    }

    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int length = s.length();
        for (int i = 0; 2*i  < length; i++) {
            if(s.charAt(i) != s.charAt(length - i - 1))
                return false;
        }
        return true;
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) {
            return 0;
        }
        int s1cnt = 0, index = 0, s2cnt = 0;
        // recall 是我们用来找循环节的变量，它是一个哈希映射
        // 我们如何找循环节？假设我们遍历了 s1cnt 个 s1，此时匹配到了第 s2cnt 个 s2 中的第 index 个字符
        // 如果我们之前遍历了 s1cnt' 个 s1 时，匹配到的是第 s2cnt' 个 s2 中同样的第 index 个字符，那么就有循环节了
        // 我们用 (s1cnt', s2cnt', index) 和 (s1cnt, s2cnt, index) 表示两次包含相同 index 的匹配结果
        // 那么哈希映射中的键就是 index，值就是 (s1cnt', s2cnt') 这个二元组
        // 循环节就是；
        //    - 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
        //    - 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
        // 那么还会剩下 (n1 - s1cnt') % (s1cnt - s1cnt') 个 s1, 我们对这些与 s2 进行暴力匹配
        // 注意 s2 要从第 index 个字符开始匹配
        Map<Integer, int[]> recall = new HashMap<Integer, int[]>();
        int[] preLoop = new int[2];
        int[] inLoop = new int[2];
        while (true) {
            // 我们多遍历一个 s1，看看能不能找到循环节
            ++s1cnt;
            for (int i = 0; i < s1.length(); ++i) {
                char ch = s1.charAt(i);
                if (ch == s2.charAt(index)) {
                    index += 1;
                    if (index == s2.length()) {
                        ++s2cnt;
                        index = 0;
                    }
                }
            }
            // 还没有找到循环节，所有的 s1 就用完了
            if (s1cnt == n1) {
                return s2cnt / n2;
            }
            // 出现了之前的 index，表示找到了循环节
            if (recall.containsKey(index)) {
                int[] value = recall.get(index);
                int s1cntPrime = value[0];
                int s2cntPrime = value[1];
                // 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
                preLoop = new int[]{s1cntPrime, s2cntPrime};
                // 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
                inLoop = new int[]{s1cnt - s1cntPrime, s2cnt - s2cntPrime};
                break;
            } else {
                recall.put(index, new int[]{s1cnt, s2cnt});
            }
        }
        // ans 存储的是 S1 包含的 s2 的数量，考虑的之前的 preLoop 和 inLoop
        int ans = preLoop[1] + (n1 - preLoop[0]) / inLoop[0] * inLoop[1];
        // S1 的末尾还剩下一些 s1，我们暴力进行匹配
        int rest = (n1 - preLoop[0]) % inLoop[0];
        for (int i = 0; i < rest; ++i) {
            for (int j = 0; j < s1.length(); ++j) {
                char ch = s1.charAt(j);
                if (ch == s2.charAt(index)) {
                    ++index;
                    if (index == s2.length()) {
                        ++ans;
                        index = 0;
                    }
                }
            }
        }
        // S1 包含 ans 个 s2，那么就包含 ans / n2 个 S2
        return ans / n2;
    }

    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        StringBuilder sTemp1 = new StringBuilder();
        StringBuilder sTemp2 = new StringBuilder();
        for (int i = 0; i < n1; i++) {
            sTemp1.append(s1);
        }
        for (int i = 0; i < n2; i++) {
            sTemp2.append(s2);
        }
        StringBuilder res = new StringBuilder();
        int sIndex1 = 0, sIndex2 = 0;
        while(sIndex1 < sTemp1.length()){
            if(sTemp1.charAt(sIndex1) == sTemp2.charAt(sIndex2)){
                res.append(sTemp1.charAt(sIndex1));
                sIndex1++;
                sIndex2 = sIndex2 == sTemp2.length() - 1 ? 0 : sIndex2 + 1;
            }else{
                sIndex1++;
            }
        }
        return (res.length() / sTemp2.length());
    }

    public int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        int[] temp = nums.clone();
        Arrays.sort(temp);
        int pre = 0, suf = temp.length - 1;
        while(pre < suf){
            if(temp[pre] + temp[suf] == target){
                res[0] = temp[pre]; res[1] = temp[suf];
                break;
            }else if(temp[pre] + temp[suf] < target){
                pre ++;
            }else{
                suf --;
            }
        }
        int filter = 0;
        for (int i = 0; i < nums.length; i++) {
            if(res[0] == nums[i]){
                filter = i;
                res[0] = i;
                break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(res[1] == nums[i] && i != filter){
                res[1] = i;
                break;
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target){
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                res[0] = i;
                res[1] = map.get(temp);
                break;
            }else{
                map.put(nums[i], i);
            }
        }
        return res;
    }

    public int[] sortArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = i; j < nums.length; j++) {
                if(nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = false;
                }
            }
            if(flag) break;
        }
        return nums;
    }

    void qSort(int[] nums, int left, int right){
        int i = left, j = right;
        if(i < j){
            int temp = nums[i];
            while(i < j){
                while(i < j && nums[j] >= temp) j --;
                if(i < j) nums[i] = nums[j];
                while(i < j && nums[i] < temp) i ++;
                if(i < j) nums[j] = nums[i];
            }
            nums[i] = temp;
            qSort(nums, 0, i);
            qSort(nums, i + 1, right);
        }
    }

    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode res = new ListNode(head.val);
        head = head.next;
        ListNode temp;
        while(head != null){
            temp = new ListNode(head.val);
            temp.next = res;
            res = temp;
            head = head.next;
        }
        return res;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int val){ this.val = val;}
}
class LRUCache {

    int size;
    Map<Integer, Integer> lhm;

    public LRUCache(int capacity) {
        size = capacity;
        lhm = new LinkedHashMap<>();
    }

    public int get(int key) {
        if(lhm.size() == 0 || !lhm.containsKey(key)) return -1;
        int value = lhm.remove(key);
        lhm.put(key, value);
        return lhm.get(key);
    }

    public void put(int key, int value) {
        if(lhm.containsKey(key)){
            lhm.remove(key);
        }
        lhm.put(key, value);
        if(lhm.size() > size){
            lhm.remove(lhm.entrySet().iterator().next().getKey());
        }
    }
}
class Solution {
    static List<String> result;
    public static List<String> readBinaryWatch(int num) {
        result=new ArrayList<String>();
        //判断输入
        if(num<0)
            return result;
        //LED灯，前四个为小时，后六个为分钟
        int[] nums=new int[]{8,4,2,1,32,16,8,4,2,1};
        backTrack(num,nums,0,0,0);
        return result;
    }
    public static void backTrack(int num,int[] nums,int start,int hour,int minute){
        if(num==0){
            //判断时间是否正确
            if(hour>11||minute>59)
                return;
            StringBuilder tmp=new StringBuilder();
            //小时
            tmp.append(hour);
            tmp.append(":");
            //分钟
            if(minute<10)
                tmp.append(0);
            tmp.append(minute);
            result.add(new String(tmp));
            return ;
        }
        for(int i=start;i<nums.length;i++){
            /*回溯做选择*/
            //判断是小时还是分钟
            if(i<4)//小时
                hour+=nums[i];
            else
                minute+=nums[i];
            //递归
            backTrack(num-1,nums,i+1,hour,minute);
            /*回溯取消选择*/
            if(i<4)//小时
                hour-=nums[i];
            else
                minute-=nums[i];
        }
    }

    public static void main(String[] args) {
        List<String> strings = readBinaryWatch(7);
    }
}
