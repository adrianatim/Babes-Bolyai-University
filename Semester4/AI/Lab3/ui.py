# -*- coding: utf-8 -*-


# imports
import gui as gui
import pygame
from controller import Controller


# create a menu
#   1. map options:
#         a. create random map
#         b. load a map
#         c. save a map
#         d visualise map
#   2. EA options:
#         a. parameters setup
#         b. run the solver
#         c. visualise the statistics
#         d. view the drone moving on a path
#              function gui.movingDrone(currentMap, path, speed, markseen)
#              ATTENTION! the function doesn't check if the path passes trough walls

def printMenu():
    print("")
    print("-----------MENU-----------")
    print("Operations with Map:")
    print(" 1.Create a random map")
    print(" 2.Load a map from a given file")
    print(" 3.Save the current map in a given file")
    print(" 4.Display current map")
    print("Options with EA:")
    print(" 5.Setup parameters")
    print(" 6.Run solver")
    print(" 7.Statistics")
    print(" 8.Moving drone on a given path")
    print("0.Exit")


def createMap(controller):
    try:
        width = int(input("Width: "))
        height = int(input("Height: "))
        fill = float(input("Wall probability: "))
        controller.createMap(width, height, fill)
    except ValueError:
        print("Invalid command")


def loadMap(controller):
    filename = input("Input the filename: ")
    controller.loadMap(filename)


def saveMap(controller):
    filename = input("Input the filename: ")
    controller.saveMap(filename)


def printMap(controller):
    screen = gui.initPyGame(controller.getSize())
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        image = gui.image(controller.getMap())
        screen.blit(image, (0, 0))
        pygame.display.flip()

    pygame.quit()


def setupParameters(controller):
    filename = input("Filename: ")

    with open(filename, "r") as f:
        try:
            l1 = f.readline().split(",")
            x, y = int(l1[0]), int(l1[1])
            populationSize = int(f.readline())
            iterations = int(f.readline())
            batteryCapacity = int(f.readline())

            controller.setDroneCoordinates((x, y))
            controller.setPopulationSize(populationSize)
            controller.setIterations(iterations)
            controller.setBatteryCapacity(batteryCapacity)
        except ValueError:
            print("Invalid file")


def runSolver(controller):
    try:
        count = int(input("Number of runs: "))
        controller.solver(count)
    except ValueError:
        print("Invalid command")


def printStats(controller):
    controller.statistics()


def seeDrone(controller):
    path = controller.getBestPath()
    gui.movingDrone(controller.getMap(), controller.droneCoordinates, path)


def run():
    running = True
    controller = Controller()

    controller.loadMap("1.map")
    with open("in.txt", "r") as f:
        try:
            l1 = f.readline().split(",")
            x, y = int(l1[0]), int(l1[1])
            populationSize = int(f.readline())
            iterations = int(f.readline())
            batteryCapacity = int(f.readline())

            controller.setDroneCoordinates((x, y))
            controller.setPopulationSize(populationSize)
            controller.setIterations(iterations)
            controller.setBatteryCapacity(batteryCapacity)
        except ValueError:
            print("Invalid file")

    commands = {
        1: createMap,
        2: loadMap,
        3: saveMap,
        4: printMap,
        5: setupParameters,
        6: runSolver,
        7: printStats,
        8: seeDrone,
    }
    while running:
        printMenu()
        command = int(input(">>>"))
        if command in commands:
            commands[command](controller)
        elif command == 0:
            running = False
        else:
            print("Invalid command!")


if __name__ == "__main__":
    run()
