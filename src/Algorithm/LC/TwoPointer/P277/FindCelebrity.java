package Algorithm.LC.TwoPointer.P277;

/*
LeetCode 277: Find the Celebrity

Problem Statement:
- You are at a party with n people (labeled 0 to n-1) and
  there may be a celebrity among them.
- A celebrity is known by everyone but knows no one.
- You have access to the API: boolean knows(a, b)
  which returns true if person a knows person b.
- Find the celebrity (return their label) or return -1 if none exists.
*/




/*   a b c d  known
   a   1 1 1
   b 0   1 1
   c 0 0   0
   d 1 1 1

   know
 */
public class FindCelebrity {

    public int findCelebrity(int n) {

        int candidate = 0;

        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                //if A knows B, then A can't be candidate
                //if A doesn't know B, then B can't be candidate
                candidate = i;
            }
        }

        for (int i = 0; i < n; i ++) {
            if (i == candidate ) {
                continue;
            }
            if (!knows(i, candidate) || knows(candidate, candidate)) {
                return -1;
            }
        }



        // TODO: Implement the algorithm

        int[] know = new int[n];
        int[] known = new int[n];


        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n;j++) {
                int aKnowb = knows(i,j)? 1 : 0;
                int bknowa = knows(j,i)? 1 : 0;
                know[i] += aKnowb;
                known[i] += bknowa;
            }
        }

        for (int i = 0; i < n; i++) {
            if (know[i] == 0 && known[i] == n-1) {
                return i;
            }
        }

        return -1;
    }

    private boolean knows(int a, int b) {
        return true;
    }
}
