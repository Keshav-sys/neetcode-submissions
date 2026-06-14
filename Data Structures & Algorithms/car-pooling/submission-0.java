class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        //sort according to arrival time
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        int totalTrips = trips.length;

        // pq to store trips according to most recent drop off time
        var tripsPq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);

        for(int i=0; i<totalTrips; i++){
            int[] currTrip = trips[i];
            
            //remove trips which are completed before or on current time
            while(!tripsPq.isEmpty() && tripsPq.peek()[1] <= currTrip[1]){
                int[] remTrip = tripsPq.remove();
                //increase capacity as passengers are removed
                capacity += remTrip[0];
            }

            //return false if not possible for current trip
            if(currTrip[0] > capacity)return false;

            //decrese the capacity
            capacity -= currTrip[0];

            //load trip in priorityQueue
            tripsPq.add(new int[]{currTrip[0], currTrip[2]});
        }

        return true;

    }
}