# -*- coding: utf-8 -*-

import pickle
from domain import *
import random


class Repository():
    def __init__(self, seed, populationSize, individualSize):
        random.seed(seed)
        self._seed = seed
        self._populations = [Population(populationSize, individualSize)]
        self._map = Map()
        self._individualSize = individualSize
        self._populationSize = populationSize

    @property
    def lastIteration(self):
        return self._populations[-1]

    def addIteration(self, population):
        self._populations.append(population)

    def __getitem__(self, item):
        return self._populations[item]

    @property
    def seed(self):
        return self._seed
