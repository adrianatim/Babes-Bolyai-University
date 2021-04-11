# # import the pygame module, so you can use it
# import pickle, pygame, time
# import Domain.Service
# from pygame.locals import *
# from random import random, randint
# import numpy as np
#
# # Creating some colors
# BLUE = (0, 0, 255)
# GRAYBLUE = (50, 120, 120)
# RED = (255, 0, 0)
# GREEN = (0, 255, 0)
# BLACK = (0, 0, 0)
# WHITE = (255, 255, 255)
#
# # define directions
# UP = 0
# DOWN = 2
# LEFT = 1
# RIGHT = 3
#
# # define indexes variations
# v = [[-1, 0], [1, 0], [0, 1], [0, -1]]
#
#
# class Map:
#     def __init__(self, n=20, m=20):
#         self.n = n
#         self.m = m
#         self.surface = np.zeros((self.n, self.m))
#
#     def randomMap(self, fill=0.2):
#         for i in range(self.n):
#             for j in range(self.m):
#                 if random() <= fill:
#                     self.surface[i][j] = 1
#
#     def __str__(self):
#         string = ""
#         for i in range(self.n):
#             for j in range(self.m):
#                 string = string + str(int(self.surface[i][j]))
#             string = string + "\n"
#         return string
#
#     def saveMap(self, numFile="test.map"):
#         with open(numFile, 'wb') as f:
#             pickle.dump(self, f)
#             f.close()
#
#     def loadMap(self, numfile):
#         with open(numfile, "rb") as f:
#             dummy = pickle.load(f)
#             self.n = dummy.n
#             self.m = dummy.m
#             self.surface = dummy.surface
#             f.close()
#
#     def image(self, colour=BLUE, background=WHITE):
#         imagine = pygame.Surface((400, 400))
#         brick = pygame.Surface((20, 20))
#         brick.fill(BLUE)
#         imagine.fill(WHITE)
#         for i in range(self.n):
#             for j in range(self.m):
#                 if self.surface[i][j] == 1:
#                     imagine.blit(brick, (j * 20, i * 20))
#
#         return imagine
#
#
# class Drone():
#     def __init__(self, x, y):
#         self.x = x
#         self.y = y
#
#     def move(self, detectedMap):
#         pressed_keys = pygame.key.get_pressed()
#         if self.x > 0:
#             if pressed_keys[K_UP] and detectedMap.surface[self.x - 1][self.y] == 0:
#                 self.x = self.x - 1
#         if self.x < 19:
#             if pressed_keys[K_DOWN] and detectedMap.surface[self.x + 1][self.y] == 0:
#                 self.x = self.x + 1
#
#         if self.y > 0:
#             if pressed_keys[K_LEFT] and detectedMap.surface[self.x][self.y - 1] == 0:
#                 self.y = self.y - 1
#         if self.y < 19:
#             if pressed_keys[K_RIGHT] and detectedMap.surface[self.x][self.y + 1] == 0:
#                 self.y = self.y + 1
#
#     def mapWithDrone(self, mapImage):
#         drona = pygame.image.load("drona.png")
#         mapImage.blit(drona, (self.y * 20, self.x * 20))
#
#         return mapImage
#
# # define a main function
# def main():
#     # we create the map
#     m = Map()
#     s = Domain.Service()
#
#     # m.randomMap()
#     # m.saveMap("test2.map")
#     m.loadMap("test1.map")
#
#     # initialize the pygame module
#     pygame.init()
#     # load and set the logo
#     logo = pygame.image.load("logo32x32.png")
#     pygame.display.set_icon(logo)
#     pygame.display.set_caption("Path in simple environment")
#
#     # we position the drone somewhere in the area
#     x = randint(0, 19)
#     y = randint(0, 19)
#
#     # create drona
#     d = Drone(x, y)
#
#     # create a surface on screen that has the size of 400 x 480
#     screen = pygame.display.set_mode((400, 400))
#     screen.fill(WHITE)
#
#     # define a variable to control the main loop
#     running = True
#
#     # main loop
#     while running:
#         # event handling, gets all event from the event queue
#         for event in pygame.event.get():
#             # only do something if the event is of type QUIT
#             if event.type == pygame.QUIT:
#                 # change the value to False, to exit the main loop
#                 running = False
#
#             if event.type == KEYDOWN:
#                 d.move(m)  # this call will be erased
#
#         screen.blit(d.mapWithDrone(m.image()), (0, 0))
#         pygame.display.flip()
#
#     # path = dummysearch()
#     x1 = randint(0, 19)
#     y1 = randint(0, 19)
#     while m.surface[x1][y1] == 1:
#         x1 = randint(0, 19)
#         y1 = randint(0, 19)
#
#     print("Start points:", x, y)
#     print("End points:", x1, y1)
#     path = s.searchAStar(m, d, x, y, x1, y1)
#     print(path)
#     screen.blit(s.displayWithPath(m.image(), path), (0, 0))
#
#     pygame.display.flip()
#     time.sleep(1)
#     pygame.quit()
#
#
# # run the main function only if this module is executed as the main script
# # (if you import this as a module then nothing is executed)
# if __name__ == "__main__":
#     # call the main function
#     main()
