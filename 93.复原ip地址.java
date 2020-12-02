import java.util.ArrayList;
import java.util.List;


/*
 * @lc app=leetcode.cn id=93 lang=java
 *
 * [93] 复原IP地址
 */

// @lc code=start
class Solution {
    //递归
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segements = new int[SEG_COUNT];
    public List<String> restoreIpAddresses(String s) {
        segements = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    private void dfs(String s, int segId, int segStart) {
       if(segId == SEG_COUNT){
           if(segStart == s.length()) {
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < SEG_COUNT; i ++){
                sb.append(segements[i]);
                if(i != SEG_COUNT -1) {
                    sb.append('.');
                }
            }
            ans.add(sb.toString());
           }  
           return;
       } //找到答案的代码段,最外面的if当已经4段却没用完所有数字的时候也是直接返回
       if(segStart == s.length()){ //数字用完了没到4段直接返回
           return;
       }
       if(s.charAt(segStart) == '0'){
           segements[segId] = 0; 
           dfs(s, segId + 1, segStart + 1);
       } 

       int addr = 0;
       for(int segEnd = segStart; segEnd < s.length(); segEnd ++) {
           addr = addr * 10 + (s.charAt(segEnd) - '0');
           if(addr > 0 && addr <= 0xFF){
                segements[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
           }else{
               break;
           }
       }
    }
}
// @lc code=end

