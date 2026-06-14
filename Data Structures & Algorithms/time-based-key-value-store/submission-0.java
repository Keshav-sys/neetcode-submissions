class TimeMap {
    HashMap<String, TreeMap<Integer, String>> timeMap;
    public TimeMap() {
        timeMap = new HashMap<String, TreeMap<Integer, String>>();
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> map = timeMap.getOrDefault(key, new TreeMap<Integer, String>());

        map.put(timestamp, value);

        timeMap.put(key, map);
    }
    
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> map = timeMap.get(key);

        if(map == null)return "";

        Map.Entry<Integer,String> entry = map.floorEntry(timestamp);

        return entry == null ? "" : entry.getValue();
    }
}
