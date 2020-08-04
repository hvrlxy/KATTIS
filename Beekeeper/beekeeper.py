#!/usr/bin/python

#
# Solution to Beekeeper
# VT HS Contest 2014
#
# @author godmar@gmail.com
#

vowels = ['aa', 'ee', 'oo', 'ii', 'uu', 'yy']
while True:
    n = int(raw_input())
    if n == 0:
        break

    words = [raw_input() for i in range(n)]
    def howmanyvowelpairs(word):
        return sum(word.count(vowel) for vowel in vowels)

    words.sort(key = howmanyvowelpairs)
    print words[-1]
