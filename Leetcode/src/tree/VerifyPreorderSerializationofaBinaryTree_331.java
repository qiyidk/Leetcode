package tree;

import java.util.Stack;

/**
 * <p>
 * VerifyPreorderSerializationofaBinaryTree_331
 * </p>
 *
 * @author qiyi
 * @version 2016年3月4日
 */
public class VerifyPreorderSerializationofaBinaryTree_331 {
    //      _9_
    //     /   \
    //    3     2
    //   / \   / \
    //  4   5  #  6
    // / \ / \   / \
    // 7 # 4 #   # #
    //# # # # 
    // when meeting one single pound sign, it means either left subtree or right subtree is null
    // left subtree, go to right side, pop stack
    // right subtree go back one level, pop stack. if its parent is on the right side, keep going
    // when meeting two consecutive pound signs, It can be treated as one left pound sign and one right pound sign.
    // when meeting a number, go to next level and store this node is on which side of its parent.
    // when finishing traversal, the level should be zero.
   public boolean isValidSerialization2(String preorder) {
       String[] s = preorder.split(",");
       Stack<Boolean> stack = new Stack<Boolean>();// whether go to the right side of current parent
       for (int i = 0; i < s.length; i++){
           String str = s[i];
           if (str.equals("#")) {
               while (!stack.isEmpty() && stack.peek()) stack.pop();
               if (!stack.isEmpty()){//go to right side as long as not completing traversal
                   stack.pop();
                   stack.push(true);
               }
               else return i == s.length - 1;
           }
           else stack.push(false);
       }
       return stack.isEmpty();
   }
   
   // inefficient but simple
   public boolean isValidSerialization3(String preorder) {
       String s = preorder.replaceAll("\\d+,#,#", "#");
       return s.equals("#") || !s.equals(preorder) && isValidSerialization(s);
   }
   
   // 第一种方法唯一的问题是我们必须区分左右#，因而使得代码必须使用stack来记录
   // 如果我们把当前level的含义定义为下一个节点所处的level，则遇到数字下一个level + 1，遇到左#，回退一格到根节点（level - 1），遇到右#再回退一格到父节点的上级节点，很自然的就会把level送到应该到的位置
   // two possible violations
   // pound signs are not enough, after traversal, level != 0
   // pound signs are redundant, during traversal, level = 0 
   // for post-order , we can just reverse the input
   
   public boolean isValidSerialization(String preorder) {
       int level = 1;
       String[] s = preorder.split(",");
       for (String str : s){
           if (level == 0) return false;
           if (str.equals("#")) level--;
           else level++;
       }
       return level == 0;
   }
}
