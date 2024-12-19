import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    private int[][] xy;
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int curDir = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        xy = new int[][]{{0, m},{0, n}};
        int i = 0;
        int j = 0;
        while(isValidCell(i,j))
        {
            ans.add(matrix[i][j]);
            if(!isValidCell(i+dir[curDir][0], j+dir[curDir][1]))
            {
                changeBound(curDir);
                curDir = (curDir + 1)%4;
            }
            i += dir[curDir][0];
            j += dir[curDir][1];
        }
        return ans;
    }

    private boolean isValidCell(int i, int j)
    {
        return i>=xy[0][0] && i<xy[0][1] && j>=xy[1][0] && j<xy[1][1];
    }

    private void changeBound(int curDir)
    {
        if(curDir == 0)
        {
            xy[0][0]++;
        }
        else if(curDir == 1)
        {
            xy[1][1]--;
        }
        else if(curDir == 2)
        {
            xy[0][1]--;
        }
        else
        {
            xy[1][0]++;
        }
    }


    /**
     *         Solution s = new Solution();
     *         List<Integer> ans = s.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
     *         System.out.println(String.join(", ", ans.stream()
     *                 .map(integer -> integer + "")
     *                 .toList().toArray(new String[0])));
     */
}