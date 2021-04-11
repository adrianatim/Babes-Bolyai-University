# -*- coding: utf-8 -*-

from random import *
from utils import DIRECTIONS
from numpy import average, nanstd
import pickle
import numpy as np


# the glass gene can be replaced with int or float, or other types
# depending on your problem's representation

class Gene:
    def __init__(self):
        self._move = choice(DIRECTIONS)

    def getMove(self):
        return self._move

    def __str__(self):
        return "(" + str(self._move[0]) + ", " + str(self._move[1]) + ")"

    def __getitem__(self, item):
        return self._move[item]


class Individual:
    def __init__(self, size=0):
        self._path_size = size
        self._path = [Gene() for _ in range(self._path_size)]
        self._fitness = None

    def getFitness(self):
        return self._fitness

    def getPath(self):
        return self._path

    def mutate(self, mutateProbability=0.05):
        if random() < mutateProbability:
            self._path[randint(0, self._path_size - 1)] = Gene()

    def crossover(self, otherParent, crossoverProbability=0.8):
        offspring1, offspring2 = Individual(self._path_size), Individual(self._path_size)
        if random() < crossoverProbability:
            split_location = randint(1, self._path_size - 2)
            offspring1._path = self._path[:split_location] + otherParent._path[split_location:]
            offspring2._path = otherParent._path[:split_location] + self._path[split_location:]
            return offspring1, offspring2
        return self, otherParent

    def setFitness(self, value):
        self._fitness = value

    def __lt__(self, other):
        return self._fitness < other._fitness

    def __gt__(self, other):
        return self._fitness > other._fitness

    def __str__(self):
        result = "Individual: " + str(self._fitness) + " path: "
        for gene in self._path:
            result += str(gene)
        return result


class Population():
    def __init__(self, populationSize=0, individualSize=0):
        self._populationSize = populationSize
        self._population = [Individual(individualSize) for _ in range(populationSize)]
        self._n = 0

    def fitnessAverage(self):
        return average([individual.getFitness() for individual in self._population])

    def fitnessStandardDeviation(self):
        return nanstd([individual.getFitness() for individual in self._population])

    def getBestIndividuals(self, count):
        self._population.sort(reverse=True)

        return self._population[:count]

    def add(self, individual):
        self._population.append(individual)

    def __iter__(self):
        self._n = 0
        return self

    def __next__(self):
        if self._n < len(self._population):
            item = self._population[self._n]
            self._n += 1
            return item
        else:
            raise StopIteration


class Map():
    def __init__(self, width=20, height=20):
        self.width = width
        self.height = height
        self.surface = np.zeros((self.width, self.height))

    def randomMap(self, fill=0.2):
        for i in range(self.width):
            for j in range(self.height):
                if random() <= fill:
                    self.surface[i][j] = 1

    def __str__(self):
        string = ""
        for i in range(self.width):
            for j in range(self.height):
                string = string + str(int(self.surface[i][j]))
            string = string + "\n"
        return string

    def saveMap(self, filename):
        with open(filename, "wb") as f:
            pickle.dump(self, f)
            f.close()

    def loadMap(self, filename):
        with open(filename, "rb") as f:
            data = pickle.load(f)
            self.width = data.width
            self.height = data.height
            self.surface = data.surface
            f.close()

    def getPosition(self, x, y):
        return self.surface[x][y]
