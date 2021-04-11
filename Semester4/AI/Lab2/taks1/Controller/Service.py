import pygame

# Creating some colors
BLUE = (0, 0, 255)
GRAYBLUE = (50, 120, 120)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

# define directions
UP = 0
DOWN = 2
LEFT = 1
RIGHT = 3

# define indexes variations
v = [[-1, 0], [1, 0], [0, 1], [0, -1]]


class Controller:
    def __init__(self):
        pass

    def searchAStar(self, mapM, initialX, initialY, finalX, finalY):
        # TO DO
        # implement the search function and put it in controller
        # returns a list of moves as a list of pairs [x,y]

        total_cost = {}  # F
        distance = {}  # G
        heuristic = {}  # H
        go_back = {}

        open_list = []
        close_list = []

        open_list.append((initialX, initialY))

        distance[(initialX, initialY)] = 0
        heuristic[(initialX, initialY)] = 0
        total_cost[(initialX, initialY)] = 0

        current_pos = (0, 0)
        current_index = 0

        while len(open_list) > 0:

            maximum = 99999999
            index = 0

            # Look for the smallest total cost
            for (x, y) in open_list:
                if total_cost[(x, y)] < maximum:
                    current_pos = (x, y)
                    current_index = index
                    maximum = total_cost[(x, y)]
                index = index + 1

            # Pop that node from the open list, and add it to the closed list
            open_list.pop(current_index)
            close_list.append(current_pos)

            # If we reached the destination, we return the path
            if current_pos == (finalX, finalY):
                path = []
                x1 = finalX
                y1 = finalY
                while x1 != initialX or y1 != initialY:
                    path.append((x1, y1))
                    (x1, y1) = go_back[(x1, y1)]
                path.append((x1, y1))
                return path[::-1]  # Reversed path

            children = []

            for new_possible_pos in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
                new_position = (current_pos[0] + new_possible_pos[0], current_pos[1] + new_possible_pos[1])

                # map range
                if not (new_position[0] >= 0 and new_position[0] <= 19 and new_position[1] >= 0 and new_position[
                    1] <= 19):
                    continue

                # no blocks
                if mapM.surface[new_position[0]][new_position[1]] == 1:
                    continue

                if new_position not in close_list:
                    go_back[new_position] = current_pos
                children.append(new_position)

            for child in children:

                # we select those child who are not closed
                for closed_child in close_list:
                    if child == closed_child:
                        continue

                distance[child] = distance[current_pos] + 1
                heuristic[child] = (child[0] - finalX) ** 2 + (child[1] - finalY) ** 2
                total_cost[child] = distance[child] + heuristic[child]

                # we select those child who are not opened and doesn't have the cost greater than the initial one
                for open_child in open_list:
                    if child == open_child and distance[child] > distance[open_child]:
                        continue

                open_list.append(child)

    def searchGreedy(self, mapM, initialX, initialY, finalX, finalY):
        heuristic = {}  # H
        go_back = {}
        open_list = [(initialX, initialY)]
        close_list = []
        heuristic[(initialX, initialY)] = (initialX - finalX) ** 2 + (initialY - finalY) ** 2

        while len(open_list) > 0:

            current_pos = open_list.pop(0)
            close_list.append(current_pos)

            # If we reached the destination, we return the path
            if current_pos == (finalX, finalY):
                path = []
                x1 = finalX
                y1 = finalY
                while x1 != initialX or y1 != initialY:
                    path.append((x1, y1))
                    (x1, y1) = go_back[(x1, y1)]
                path.append((x1, y1))
                return path[::-1]  # Reversed path

            children = []

            for new_possible_pos in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
                new_position = (current_pos[0] + new_possible_pos[0], current_pos[1] + new_possible_pos[1])

                # map range
                if not (new_position[0] >= 0 and new_position[0] <= 19 and new_position[1] >= 0 and new_position[
                    1] <= 19):
                    continue

                # no blocks
                if mapM.surface[new_position[0]][new_position[1]] == 1:
                    continue

                children.append(new_position)

            maximum = 9999999
            selected_child = (0, 0)

            for child in children:

                # we select those child who are not closed
                for closed_child in close_list:
                    if child == closed_child:
                        continue

                heuristic[child] = (child[0] - finalX) ** 2 + (child[1] - finalY) ** 2

                # we select only that child with the smallest cost (distance)
                if heuristic[child] < maximum:
                    selected_child = child
                    maximum = heuristic[child]

            go_back[selected_child] = current_pos
            open_list.append(selected_child)

    def dummysearch(self):
        # example of some path in test1.map from [5,7] to [7,11]
        return [[5, 7], [5, 8], [5, 9], [5, 10], [5, 11], [6, 11], [7, 11]]

    def displayWithPath(self, image, path):
        mark = pygame.Surface((20, 20))
        mark.fill(GREEN)
        for move in path:
            image.blit(mark, (move[1] * 20, move[0] * 20))

        return image
