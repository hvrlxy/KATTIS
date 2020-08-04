#!/usr/bin/python

#
# Create a bunch of small, large, and random test cases
# and stick them into files of 10 each.
#

def write(tests, number):
    with open("secret/secret-%03d.in" % number, "w") as f:
        print >>f, len(tests)
        for b, d, s in tests:
            print >>f, b, d, s

number = 1
tests = []
for b in range(1, 6):
    for d in range(1, 6):
        for s in ["V", "H"]:
            tests.append((b, d, s))

tests.append((500, 500, 'H'))
tests.append((500, 500, 'V'))
tests.append((499, 500, 'V'))
tests.append((500, 499, 'H'))
tests.append((257, 500, 'V'))
tests.append((256, 500, 'V'))
tests.append((255, 500, 'V'))
tests.append((500, 257, 'H'))
tests.append((500, 256, 'H'))
tests.append((500, 255, 'H'))

from random import randint
for i in range(100):
    b = randint(6, 500)
    d = randint(6, 500)
    s = "V" if randint(1, 2) == 1 else "H"
    tests.append((b, d, s))

while len(tests) > 0:
    L = min(10, len(tests))
    write(tests[:L], number)
    number += 1
    tests = tests[L:]
