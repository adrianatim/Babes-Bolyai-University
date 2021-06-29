import csv

from domain import Record


def readData():
    data = []
    with open('dataset.csv') as f:
        csv_reader = csv.reader(f, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                line_count += 1
            else:
                record = Record(row[0], row[1], row[2])
                data.append(record)
                line_count += 1
    return data
