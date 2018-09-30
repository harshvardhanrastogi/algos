class InsertionSort:
    def __init__(self):
        pass

    def sort(self, data):
        for i in range(1, len(data)):
            key = data[i];
            j = i - 1;
            while(j >= 0 and data[j] > key):
                 data[j+1] = data[j];
                 j = j-1;
            data[j+1] = key;
        return data

ins = InsertionSort()
data = ins.sort([99,88,77,66,55,44,33,22,11]);
for d in enumerate(data):
    print(d)
