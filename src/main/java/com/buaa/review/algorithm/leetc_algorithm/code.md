public int numWays(int n){
    if(n == 0) return 0;
    if(n == 1) return 1;
    return numWays(n - 1) + numWays(n - 2);
}

public static int numWays2(){
    if(n == 0) return 1;
    if(n == 1) return 1;
    int i = 1;
    int j = 1;
    int temp;
    while(n >= 2){
       temp = i + j;
       i = j;
       j = temp;
       n--;
    }
    return j;
}
public static TreeNode invertTree(TreeNode root){
    TreeNode temp;
    temp = root.left;
    root.left = root.right;
    root.right = temp;
    return root;
}