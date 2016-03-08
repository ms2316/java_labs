package uk.ac.cam.ms2316.Algorithms.Tick2;

import uk.ac.cam.rkh23.Algorithms.Tick2.*;

public class LCSBottomUp extends LCSFinder {
	
	public LCSBottomUp(String s1, String s2) {
		super(s1,s2);
	}

	public int getLCSLength() {
		int n = mString1.length();
		int m = mString2.length();
		
		int a[][] = new int[n+1][m+1];
		
		for (int i = 0; i <= n; i++)
			a[i][0] = 0;
		for (int i = 0; i <= m; i++)
			a[0][i] = 0;
			
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (mString1.charAt(i-1) == mString2.charAt(j-1)) {
					a[i][j] = 1 + a[i-1][j-1];
				} else if (a[i][j-1] > a[i-1][j])
					a[i][j] = a[i][j-1];
				else 
					a[i][j] = a[i-1][j];
			}
		}
		
		mTable = new int[n][m];
		//copy to everything to mTable
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mTable[i][j] = a[i+1][j+1];
			}
		}
		
		if (n == 0 || m == 0)
			return 0;
		else
			return mTable[n-1][m-1];
	}
	
	
	public String getLCSString() {
		String ans = "";
		int i = mString1.length()-1; 
		int j = mString2.length()-1;
		while (i >= 0 && j >= 0) {
			if (mString1.charAt(i) == mString2.charAt(j)) {
				ans += mString1.charAt(i);
				i--; j--;
			} else {
				if (i > 0 && j > 0) {
					if (mTable[i][j-1] > mTable[i-1][j])
						j--;
					else
						i--;
				} else if (i > 0){
					i--;
				} else if (j > 0){
					j--;
				} else {
					break;
				}
			}
		}
		
		String reverse = "";
		for (i = ans.length() - 1 ; i >= 0 ; i-- ) {
			reverse = reverse + ans.charAt(i);
		}
		
		return reverse;
	}
	
}