class BinarySearchIterative:

    def search(self, arr, left: int, right: int, x:int) -> int:

        while(left <= right):
             m = int(left + (right - left)/2)
             if arr[m] == x:
                 return m;

             if arr[m] < x:
                 left = m + 1
             elif arr[m] > x:
                 right = m - 1;
                 
        return -1
