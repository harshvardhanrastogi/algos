class InsertionSortDescend:
    def __init__(self):
        pass

    def sort(self, data):
        for i in range(1, len(data)):
            key = data[i];
            j = i - 1;
            while(j >= 0 and data[j] < key):
                 data[j+1] = data[j];
                 j = j-1;
            data[j+1] = key;
        return data

insd = InsertionSortDescend()
data = insd.sort([11,22,33,44,55,66,77,88,99,0,1,2]);
for d in enumerate(data):
    print(d)
