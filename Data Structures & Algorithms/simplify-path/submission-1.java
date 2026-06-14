class Solution {
    public String simplifyPath(String path) {
        int len = path.length();

        var stk = new Stack<Character>();
        String dirName = "";
        for (int i = 0; i < len; i++) {
            char ch = path.charAt(i);

            if (ch == '/') {
                if (stk.isEmpty()) {
                    stk.push(ch);
                } else {
                    if (dirName.equals(".")) {
                        int count = 1;
                        while (!stk.isEmpty() && count > 0) {
                            if (stk.pop() == '/')
                                count--;
                        }

                    } else if (dirName.equals("..")) {
                        int count = 2;
                        while (!stk.isEmpty() && count > 0) {
                            if (stk.pop() == '/')
                                count--;
                        }
                    } else if (dirName.equals("")) {
                        continue;
                    }

                    stk.push('/');
                    dirName = "";
                }
            } else {
                stk.push(ch);
                dirName += ch;
            }
        }

        if (dirName.equals(".")) {
            int count = 1;
            while (!stk.isEmpty() && count > 0) {
                if (stk.pop() == '/')
                    count--;
            }

        } else if (dirName.equals("..")) {
            int count = 2;
            while (!stk.isEmpty() && count > 0) {
                if (stk.pop() == '/')
                    count--;
            }
        }

        if(stk.isEmpty())stk.push('/');

        StringBuilder sb = new StringBuilder();

        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        if (sb.length() > 1 && sb.charAt(0) == '/')
            sb.deleteCharAt(0);

        sb.reverse();

        return sb.toString();
    }
}