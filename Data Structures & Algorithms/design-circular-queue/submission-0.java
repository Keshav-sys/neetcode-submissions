class MyCircularQueue {
    class Node{
        int val;
        Node next, prev;
        Node(int val){
            this.val = val;
        }
    }

    Node head, tail;
    int size, limit;
   
    public MyCircularQueue(int k) {
        this.head = new Node(-1);
        this.tail = new Node(-1);
        this.size = 0;
        this.limit = k;

        head.prev = tail;
        tail.next = head;
    }
    
    public boolean enQueue(int value) {
        
        if(size == limit)return false;

        Node prev = head.prev;

        Node newNode = new Node(value);

        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = head;
        head.prev = newNode;

        size++;

        return true;
    }
    
    public boolean deQueue() {
        if(size == 0)return false;

        Node newNext = tail.next.next;
        newNext.prev = tail;
        tail.next = newNext;

        size--;

        return true;
    }
    
    public int Front() {
        if(size == 0)return -1;

        return tail.next.val;
    }
    
    public int Rear() {
        if(size == 0)return -1;

        return head.prev.val;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == limit;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */