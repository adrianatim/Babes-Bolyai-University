# -*- coding: utf-8 -*-

import pygame, time
from utils import *
from domain import *


def initPyGame(dimension):
    # init the pygame
    pygame.init()
    logo = pygame.image.load("logo32x32.png")
    pygame.display.set_icon(logo)
    pygame.display.set_caption("drone exploration with AE")

    # create a surface on screen that has the size of 800 x 480
    screen = pygame.display.set_mode(dimension)
    screen.fill(WHITE)
    return screen


def closePyGame():
    # closes the pygame
    running = True
    # loop for events
    while running:
        # event handling, gets all event from the event queue
        for event in pygame.event.get():
            # only do something if the event is of type QUIT
            if event.type == pygame.QUIT:
                # change the value to False, to exit the main loop
                running = False
    pygame.quit()


def movingDrone(currentMap, droneCoordinates, path, speed=1, markSeen=True):
    # animation of a drone on a path

    screen = initPyGame((currentMap.width * 20, currentMap.height * 20))

    drone = pygame.image.load("drona.png")

    path = [droneCoordinates] + path
    print(path)

    for i in range(len(path)):
        screen.blit(image(currentMap), (0, 0))
        currentPath = [0, 0]
        for j in range(i + 1):

            if markSeen:
                brick = pygame.Surface((20, 20))
                brick.fill(GREEN)
                currentPath[0], currentPath[1] = (currentPath[0] + path[j][0], currentPath[1] + path[j][1])
                for direction in DIRECTIONS:
                    x, y = (currentPath[0] + direction[0], currentPath[1] + direction[1])
                    while 0 <= x < currentMap.width and 0 <= y < currentMap.height and currentMap.surface[x][y] != 1:
                        screen.blit(brick, (x * 20, y * 20))
                        x += direction[0]
                        y += direction[1]

        screen.blit(drone, (currentPath[0] * 20, currentPath[1] * 20))
        pygame.display.flip()
        time.sleep(0.5 * speed)
    closePyGame()


def image(currentMap, colour=BLUE, background=WHITE):
    # creates the image of a map

    imagine = pygame.Surface((currentMap.width * 20, currentMap.height * 20))
    brick = pygame.Surface((20, 20))
    brick.fill(colour)
    imagine.fill(background)
    for i in range(currentMap.width):
        for j in range(currentMap.height):
            if currentMap.surface[i][j] == 1:
                imagine.blit(brick, (i * 20, j * 20))

    return imagine
