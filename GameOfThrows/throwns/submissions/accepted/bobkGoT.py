#!/usr/bin/python2

line = raw_input()
(n,k) = line.split(' ')

n = int(n)
k = int(k)

childList = [ 0 ]
childIndex = 0

line = raw_input()
cmdList = line.split(' ')
cmdIndex = 0

while cmdIndex < len(cmdList):
	if cmdList[cmdIndex] == "undo":
		childIndex -= int(cmdList[cmdIndex+1])
		cmdIndex += 2
	else:
		while len(childList) < childIndex + 2:
			childList.append(0)
		childList[childIndex+1] = ((childList[childIndex] + n + int(cmdList[cmdIndex])) % n)
		cmdIndex += 1
		childIndex += 1
#	print childList

print childList[childIndex]
