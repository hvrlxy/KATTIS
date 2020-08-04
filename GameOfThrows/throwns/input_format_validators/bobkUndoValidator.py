#!/usr/bin/python2

import sys

line = raw_input()
(n,k) = line.split(' ')
n = int(n)
k = int(k)

line = raw_input()
cmds = line.split(' ')
i = 0
top = 0

while i < len(cmds):
	if cmds[i] == "undo":
		u = int(cmds[i+1])
		if u > top:
			sys.exit(1)
		top -= u
		i += 2
	else:
		i += 1
		top += 1

sys.exit(42)
