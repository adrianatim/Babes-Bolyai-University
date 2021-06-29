from PIL import Image
from matplotlib import image
from matplotlib import pyplot
import matplotlib.pyplot as plt
import matplotlib.image as mpimg


def print_menu():
    print("1. Display an image")
    print("2. Convert the image to a numpy array")
    print("3. Resize an image to a specific dimension")
    print("0. Exit")


def run():
    while True:
        command = input("Enter command:")
        if command == "1":
            # load and show an image with Pillow
            # load the image
            image = Image.open('C:/Users/adria/Downloads/Assignment8 (1)/Assignment8/f1.jpg')
            # summarize some details about the image
            print(image.format)
            print(image.mode)
            print(image.size)
            # show the image
            image.show()
        elif command == "2":
            # load and display an image with Matplotlib
            # load image as pixel array
            data = mpimg.imread('C:/Users/adria/Downloads/Assignment8 (1)/Assignment8/f1.jpg')
            # summarize shape of the pixel array
            print(data.dtype)
            print(data.shape)
            # display the array of pixels as an image
            pyplot.imshow(data)
            pyplot.show()
        elif command == "3":
            # create a thumbnail of an image
            # load the image
            image = Image.open('C:/Users/adria/Downloads/Assignment8 (1)/Assignment8/f1.jpg')
            # report the size of the image
            print(image.size)
            # create a thumbnail and preserve aspect ratio
            image.thumbnail((100, 100))
            image.show()
            # report the size of the thumbnail
            print(image.size)
        elif command == "0":
            exit()


print_menu()
run()
