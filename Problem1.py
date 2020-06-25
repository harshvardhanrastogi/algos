""""
Describe a  theta(n log n) time algorithm that, given a set S of n integers and another
integer x, determines whether or not there exist two elements in S whose sum is
exactly x.

I'll be using merge sort and binary search here. As we all know mearge sort has running time,
in worst case of, (n log n) and binary search has running time, in worst case, of log n.    

"""
import math
from MergeSort import MergeSort
from BinarySearchIterative import BinarySearchIterative

arr = [21, 43, 147, 207, 232, 267, 322, 418, 456, 509, 521, 600, 603, 639, 648, 713, 418, 800, 844, 957, math.inf]

ms = MergeSort()
bs = BinarySearchIterative()
ms.mergeSort(arr, 0, len(arr) - 1)

#By now the array is sorted.

x = 418

for i in range(len(arr) - 1):
    # let v1 be the first part of x
    v1 = arr[i]
    if v1 <= 0:
        continue
    
    # let v2 be the second part of x
    v2 = x - v1

    # if v2 exists then two elements whose sum is exactly equals to
    # x exists in arr.
    if v2 <= 0:
        continue
    index = bs.search(arr, 0, len(arr), v2)
    if index != -1:
        print("index: ", index, "value: ", arr[index])
        break

# Faster solution would be add values to the hashmap and find for a value if x-value is there in the map
# This is omega(n) solution.
