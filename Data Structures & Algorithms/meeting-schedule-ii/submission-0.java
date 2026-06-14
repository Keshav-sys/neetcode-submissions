/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        Collections.sort(intervals, (a,b) -> (a.start - b.start));
        PriorityQueue<Interval> pq = new PriorityQueue<>((a,b) -> (a.end - b.end));

        int ans = 0, totalMeetings = intervals.size();
        
        for(int i=0; i<totalMeetings; i++){
            Interval meeting = intervals.get(i);

            while(pq.size() > 0 && pq.peek().end <= meeting.start){
                pq.remove();
            }

            pq.add(meeting);

            ans = Math.max(ans, pq.size());
        }

        return ans;
    }
}
