package code.gaurav;

public class DecodedStringAtIndex {

    public String decodeAtIndex(String s, int k) {
        long len = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch > 96 && ch <= 122) {
                len++;
                if (len == k) {
                    return String.valueOf(ch);
                }
            } else {
                int tempInt = (int) (ch - '0');
                long nextLen = len * tempInt;
                if (k <= nextLen) {
                    return findChar(s, len,i-1, (k) % len);

                }
                len = nextLen;
            }
        }
        return null;
    }

    private String findChar(String s, long len,int i,  long k) {
        while (i >= 0) {
            char ch = s.charAt(i);
            if (ch >= 97 && ch <= 122) {
                if (k==0 || len == k) {
                    return String.valueOf(ch);
                }
                len--;
            } else {
                int tempInt = (int) (ch - '0');
                len = len / tempInt;
                k = k % len;
            }
            i--;
        }
        return null;
    }

    public static void main(String[] args) {
        DecodedStringAtIndex d = new DecodedStringAtIndex();
        System.out.println(d.decodeAtIndex("a2345678999999999999999",83015304));
    }

}
