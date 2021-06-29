import operator

from utils import euclidean_dist


class Record:

    def __init__(self, label1, val1, val2):
        self.label1 = label1
        self.val1 = float(val1)
        self.val2 = float(val2)
        self.cluster = None

    def closest_cluster(self, clusters):
        closest = -1
        for c in clusters:
            if closest == -1:
                closest = c
                continue
            if euclidean_dist(c.val1, c.val2, self.val1, self.val2) < euclidean_dist(closest.val1, closest.val2,
                                                                                     self.val1, self.val2):
                closest = c
        return closest


class Cluster:

    def __init__(self, label):
        self.label = label
        self.val1 = 0
        self.val2 = 0
        self.records = []

    def add_record(self, record):
        self.records.append(record)
        if record.cluster:
            record.cluster.records.remove(record)
        record.cluster = self

    def recompute_centroids(self):
        old1 = self.val1
        old2 = self.val2
        sum_val1 = 0
        sum_val2 = 0
        for i in self.records:
            sum_val1 += i.val1
            sum_val2 += i.val2
        self.val1 = sum_val1 / len(self.records)
        self.val2 = sum_val2 / len(self.records)
        # Stop condition: Centroids of newly formed clusters do not change
        if abs(self.val1 - old1) <= 0.001 and abs(self.val2 - old2) <= 0.001:
            return False
        return True

    def update_label(self):
        freq = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
        for i in self.records:
            freq[i.label1] += 1

        self.label = max(freq.items(), key=operator.itemgetter(1))[0]

    def get_statistics(self, data):
        accuracy = 0  # Accuracy = the number of correct classified entries / the total number of entries
        precision = 0  # P -no. of correct positive classified/total no. of examples classified as positive (TP/(TP+FP))
        rappel = 0  # R - no. of correct positive classified / total no. of positive examples ( TP/(TP+FN) )
        score = 0  # combines P and R (2PR/(P+R))
        '''
        TP - true positive - no.  of correct positive classified 
        FP - false positive
        TN - true negative
        FN - false negative
        '''
        TP = FP = TN = FN = 0

        for r in self.records:
            if r.label1 == self.label:
                TP += 1
            else:
                FP += 1

        for d in data:
            if d not in self.records:
                if d.label1 != self.label:
                    TN += 1
                else:
                    FN += 1

        accuracy = (TP + TN) / (TP + TN + FP + FN)
        precision = TP / (TP + FP)
        rappel = TP / (TP + FN)
        score = 2 * precision * rappel / (precision + rappel)

        return accuracy, precision, rappel, score
