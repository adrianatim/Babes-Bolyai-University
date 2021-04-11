import time
from random import randint

import pygame

from Domain.Drone import Drone
from Domain.Map import Map
from sketch2 import WHITE, KEYDOWN


class UI:
    def __init__(self, controller):
        self.controller = controller

    def run(self):

        # we create the map
        m = Map()
        # m.randomMap()
        # m.saveMap("test2.map")
        m.loadMap("test1.map")

        # initialize the pygame module
        pygame.init()
        # load and set the logo
        logo = pygame.image.load("logo32x32.png")
        pygame.display.set_icon(logo)
        pygame.display.set_caption("Path in simple environment")

        # we position the drone somewhere in the area

        x = 4
        y = 15

        #x = randint(0, 19)
        #y = randint(0, 19)

        # create drona
        d = Drone(x, y)

        # create a surface on screen that has the size of 400 x 480
        screen = pygame.display.set_mode((400, 400))
        screen.fill(WHITE)

        # define a variable to control the main loop
        running = True

        # main loop
        while running:
            # event handling, gets all event from the event queue
            for event in pygame.event.get():
                # only do something if the event is of type QUIT
                if event.type == pygame.QUIT:
                    # change the value to False, to exit the main loop
                    running = False
                if event.type == KEYDOWN:
                    running = False

            screen.blit(d.mapWithDrone(m.image()), (0, 0))
            pygame.display.flip()


        x1 = 17
        y1 = 5

        #x1 = randint(0, 19)
        #y1 = randint(0, 19)
        print("Start:", x, y)
        print("Goal:", x1, y1)

        # A* search algorithm
        initial_time = time.time()
        #path = self.controller.searchAStar(m, x, y, x1, y1)

        # Greedy search algorithm
        path = self.controller.searchGreedy(m, x, y, x1, y1)
        final_time = time.time()

        print("The path is: ", path)
        print("Execution time is: ", str(final_time - initial_time))

        screen.blit(self.controller.displayWithPath(m.image(), path), (0, 0))
        pygame.display.flip()
        time.sleep(5)
        pygame.quit()


