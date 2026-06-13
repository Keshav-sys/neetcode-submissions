class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        var mailIndexMap = new HashMap<String, Integer>();
        int numAccounts = accounts.size();

        int[] accPar  = new int[numAccounts];

        for(int i=0; i<numAccounts; i++){
            accPar[i] = i;
        }  

        int currAccount = 0;
        for(List<String> account : accounts){
            for(int i=1; i<account.size(); i++){
                if(mailIndexMap.containsKey(account.get(i))){
                    union(accPar, currAccount, mailIndexMap.get(account.get(i)));
                }else{
                    mailIndexMap.put(account.get(i), currAccount);
                }
            }
            currAccount++;
        }


        var ansMap = new HashMap<Integer, Set<String>>();

        currAccount = 0;
        for(List<String> account : accounts){
            for(int i=1; i<account.size(); i++){
                String currMail = account.get(i);

                int ulp = findUlp(accPar, currAccount);

                Set<String> mailSet;
                if(ansMap.containsKey(ulp)){
                    mailSet = ansMap.get(ulp);
                }else{
                    mailSet = new TreeSet<String>();
                }

                mailSet.add(currMail);

                ansMap.put(ulp, mailSet);
                
            }
            currAccount++;
        }


        List<List<String>> ans = new ArrayList<List<String>>();

        for(Map.Entry entry : ansMap.entrySet()){
            List<String> currList = new ArrayList<String>();

            String user = accounts.get((int)entry.getKey()).get(0);

            currList.add(user);

            for(String str : (Set<String>)entry.getValue()){
                currList.add(str);
            }

            ans.add(currList);
        }

        return ans;
    }

    private int findUlp(int[] par, int curr){
        if(par[curr] == curr)return curr;

        return par[curr] = findUlp(par, par[curr]);
    }

    private void union(int[] par, int i, int j) {
        int rootI = findUlp(par, i);
        int rootJ = findUlp(par, j);
        if (rootI != rootJ) par[rootI] = rootJ;
    }
}