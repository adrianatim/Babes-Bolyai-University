# k - number of clusters (classes I have to divide data in)
# k = 4 (A,B,C,D)
from random import sample

from reader import readData
from domain import Cluster


def print_statistics(clusters, data):
    clusters.sort(key=lambda x: x.label, reverse=False)
    for c in clusters:
        accuracy, precision, rappel, score = c.get_statistics(data)
        print("\n")
        print(c.label + " (" + str(round(c.val1, 2)) + ", " + str(round(c.val2, 2)) + ")")
        print("Accuracy: " + str(accuracy))
        print("Precision: " + str(precision))
        print("Rappel: " + str(rappel))
        print("Score: " + str(score))


def print_output(data):
    f = open("output_dataset.csv", 'w')
    f.write('cluster_label,label,val1,val2\n')
    for d in data:
        f.write(d.cluster.label + ',' + d.label1 + ',' + str(d.val1) + ',' + str(d.val2) + '\n')
    f.close()


def main():
    data = readData()
    # Choose the number of clusters k = 4
    clusters = [Cluster(""), Cluster(""), Cluster(""), Cluster("")]

    # Select k random points from the data as centroids
    centroids = sample(data, 4)
    for i in range(0, 4):
        clusters[i].val1 = centroids[i].val1
        clusters[i].val2 = centroids[i].val2

    run = True
    while run:
        # Assign all the points to the closest cluster centroid
        for p in data:
            c = p.closest_cluster(clusters)
            c.add_record(p)

        # Recompute the centroids of newly formed clusters
        for c in clusters:
            if not c.recompute_centroids():
                run = False
            else:
                continue

    for i in clusters:
        i.update_label()

    print_statistics(clusters, data)
    print_output(data)


main()

