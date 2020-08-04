# script to randomize the order of the given crossing times for inputs
import sys
import random
temp = open(sys.argv[1])
f = temp.read().split()
temp.close()

rest = f[1:]
random.shuffle(rest)
full = f[0:1] + rest
output = ' '.join(full)

print(output)

temp = open(sys.argv[1],'w')
temp.write(output + '\n')
temp.close()
