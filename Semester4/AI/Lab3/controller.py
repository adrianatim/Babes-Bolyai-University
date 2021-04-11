from utils import DIRECTIONS
import matplotlib.pyplot as plotlib
from time import time
import numpy as np
from random import random, choice, randint
from repository import Repository
from domain import Map, Individual, Population


class Controller:
    def __init__(self):
        self._map = Map()
        self._currentPopulationNumber = 1
        self._droneCoordinates = (-1, -1)
        self._batteryCapacity = 5
        self._mutationProbability = 0.04
        self._crossoverProbability = 0.8
        self._iterations = 30
        self._populationSize = 30
        self._parentCount = 6
        self._currentRepository = None
        self._Repositories = []

    def iteration(self):
        population = self._currentRepository.lastIteration
        for individual in population:
            self.computeFitness(individual)

        bestIndividuals = population.getBestIndividuals(self._parentCount)

        secondPopulation = Population()

        for i in range(self._populationSize // 2):
            firstIndividual = choice(bestIndividuals)
            secondIndividual = choice(bestIndividuals)

            firstOffspring, secondOffspring = firstIndividual.crossover(secondIndividual, self._crossoverProbability)

            secondPopulation.add(firstOffspring)
            secondPopulation.add(secondOffspring)

        for individual in secondPopulation:
            individual.mutate(self._mutationProbability)

        return population, secondPopulation

    def run(self):
        for i in range(self._iterations):
            population, newPopulation = self.iteration()
            self._currentRepository.addIteration(newPopulation)

    def solver(self, count=1):
        for i in range(count):
            start = time()
            self._currentRepository = Repository(random(), self._populationSize, self._batteryCapacity)
            self._Repositories.append(self._currentRepository)
            self.run()
            end = time()
            duration = end - start
            if int(duration) == 1:
                print(duration, "second needed to compute run", self._currentPopulationNumber)
            else:
                print(duration, "seconds needed to compute run", self._currentPopulationNumber)
            self._currentPopulationNumber += 1

    def createMap(self, width, height, fill):
        self._map = Map(width, height)
        self._map.randomMap(fill)

    def saveMap(self, filename):
        self._map.saveMap(filename)

    def loadMap(self, filename):
        self._map.loadMap(filename)

    def getSize(self):
        return self._map.width * 20, self._map.height * 20

    def getMap(self):
        return self._map

    def discover(self, position, surface, width, height):
        for direction in DIRECTIONS:
            x, y = (position[0] + direction[0], position[1] + direction[1])
            while 0 <= x < width and 0 <= y < height and self._map.getPosition(x, y) != 1:
                if surface[x][y] == 0:
                    surface[x][y] = 2
                x, y = (position[0] + x, position[1] + y)

    def computeFitness(self, individual: Individual):
        # 0: undiscovered space; 1: visited space by the drone; 2:discovered space by the drone
        fitness = 0
        currentPosition = self._droneCoordinates
        mapWidth = self._map.width
        mapHeight = self._map.height

        surface = np.zeros((mapWidth, mapHeight))
        surface[currentPosition[0]][currentPosition[1]] = 1

        self.discover(currentPosition, surface, mapWidth, mapHeight)

        for gene in individual.getPath():
            (x, y) = (currentPosition[0] + gene[0], currentPosition[1] + gene[1])
            if not (0 <= x < mapWidth and 0 <= y < mapHeight):  # drone is going out of field
                fitness -= 10
                break
            if self._map.getPosition(x, y) == 1:  # drone is going to bump into a wall
                fitness -= 10
                break
            if surface[x][y] == 1:  # drone is going to an already visited square
                fitness -= 5
            surface[x][y] = 1
            self.discover(currentPosition, surface, mapWidth, mapHeight)
            currentPosition = (x, y)

        fitness += len([x for x in range(mapWidth) for y in range(mapHeight) if surface[x][y] == 2])
        individual.setFitness(fitness)

    def _placeDroneRandom(self):
        while True:
            x = randint(0, self._map.width - 1)
            y = randint(0, self._map.height - 1)
            print(x, y)
            if self._map.getPosition(x, y):
                self._droneCoordinates = (x, y)
                return

    def setDroneCoordinates(self, coordinates):
        if coordinates == (-1, -1):
            self._placeDroneRandom()
        else:
            self._droneCoordinates = coordinates

    def setIterations(self, iterations):
        self._iterations = iterations

    def setPopulationSize(self, populationSize):
        self._populationSize = populationSize

    def setBatteryCapacity(self, batteryCapacity):
        self._batteryCapacity = batteryCapacity

    def statistics(self):
        fitnessList = []
        i = 1
        print("Run id, Seed, Fitness")
        for repository in self._Repositories:
            bestIndividual = repository[self._iterations - 1].getBestIndividuals(1)[0]
            fitnessList.append(bestIndividual.getFitness())
            print(i, ",", repository.seed, ",", bestIndividual.getFitness())
            i += 1

        print("Average fitness:", np.average(fitnessList), "standard deviation:",
              np.nanstd(fitnessList))

        x = []
        y = []
        z = []
        for i in range(self._iterations):
            x.append(i)
            population = self._currentRepository[i]
            y.append(population.getBestIndividuals(1)[0].getFitness())
            z.append(population.fitnessAverage())

        plotlib.plot(x, y, label="Best fitness")
        plotlib.plot(x, z, label="Average fitness")
        plotlib.legend(loc="lower right")
        plotlib.xlabel("Iterations")
        plotlib.ylabel("Fitness score")
        plotlib.show()

    def _getBestRepository(self):
        bestRepository = None
        fitness = 0
        for repository in self._Repositories:
            individual = repository[self._iterations - 1].getBestIndividuals(1)[0]
            if individual.getFitness() > fitness:
                fitness = individual.getFitness()
                bestRepository = repository

        return bestRepository

    def getBestPath(self):
        repository = self._getBestRepository()
        path = []
        for gene in repository[self._iterations - 1].getBestIndividuals(1)[0].getPath():
            path.append(gene.getMove())
        return path

    @property
    def droneCoordinates(self):
        return self._droneCoordinates
