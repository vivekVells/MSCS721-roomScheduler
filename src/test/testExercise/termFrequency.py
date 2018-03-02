# Embedded file name: michaelgildein.concordance2.py
"""
Description:
  Term frequency of input
Input:
  Input file or stdin
Output:
  Term frequency of input to a file, termFreqOutput.txt by default, in json format
Example:
  {"meadows": {"count": "3", "tokenIds": ["30198", "125145", "126280"]},"tantalizing": {"count": "3", "tokenIds": ["102935", "177287", "243300"]},"prize": {"count": "3", "tokenIds": ["136707", "206299", "238541"]},"broiled": {"count": "3", "tokenIds": ["6209", "6237", "6255"]},"sooty": {"count": "3", "tokenIds": ["15051", "189755", "216312"]}}
Assumption:
  Whole input is content with no metadata such as txt file
Last Modified:
  Feb 20, 2016
Author:
  Michael Gildein
"""
import sys
import getopt
import re
import io
import operator
import os
import stat
debug = False
inputFileName = ''
outputFileName = './termFreqOutput.txt'
alphabetical = False
stdinput = False
displayOutput = False
summary = False
filterNum = -1
lowerNum = -1
upperNum = -1
from datetime import datetime

def output(i, wordKey):
    if debug:
        print('"%s": {"count": "%s", "tokenIds": %s}' % (wordKey, len(sentenceDict[wordKey]), str(sentenceDict[wordKey]).replace("'", '"')))
    if displayOutput:
        print('"%s": %s' % (wordKey, len(sentenceDict[wordKey])))
    outputFile.write('"%s": {"count": "%s", "tokenIds": %s}' % (wordKey, len(sentenceDict[wordKey]), str(sentenceDict[wordKey]).replace("'", '"')))
    if filterNum == -1:
        if i != len(sentenceDict.keys()) - 1:
            outputFile.write(',')
    elif printCounter < filterNum:
        outputFile.write(',')


def printHelp():
    print(sys.argv[0])
    print ('  -h  :help')
    print ('  -i <inputfile>')
    print ('  -o <outputfile>')
    print ('  -v  :verbose mode')
    print ('  -n  :take input from stdin')
    print ('  -a  :sort alphabetically instead of by frequency')
    print ('  -f <number>   :fetch only # tokens')
    print ('  -l <number>   :only return tokens with frequency less than #')
    print ('  -g <number>   :only return tokens with frequency greater than #')
    print ('  -d  :display term frequency output to console')
    print ('  -s  :display summary information to console')
    sys.exit()


try:
    opts, args = getopt.getopt(sys.argv[1:], 'hvasndg:l:f:i:o:', ['ifile=', 'ofile='])
except getopt.GetoptError:
    printHelp()
    sys.exit(2)

for opt, arg in opts:
    if opt == '-h':
        printHelp()
        sys.exit(2)
    elif opt in ('-i', '--ifile'):
        inputFileName = arg
    elif opt in ('-o', '--ofile'):
        outputFileName = arg
    elif opt == '-v':
        debug = True
    elif opt == '-a':
        alphabetical = True
    elif opt == '-n':
        stdinput = True
    elif opt == '-d':
        displayOutput = True
    elif opt == '-s':
        summary = True
    elif opt == '-f':
        try:
            filterNum = int(arg)
        except Exception:
            print ('Filter is not an integer.  Please try again.')
            sys.exit(2)

        if filter < 0:
            print ('Filter must be a positive number. Please try again.')
            sys.exit(2)
    elif opt == '-l':
        try:
            lowerNum = int(arg)
        except Exception:
            print ('Lower bound is not an integer.  Please try again.')
            sys.exit(2)

        if filter < 1:
            print ('Lower bound must be a non-zero positive number. Please try again.')
            sys.exit(2)
    elif opt == '-g':
        try:
            upperNum = int(arg)
        except Exception:
            print ('Upper bound is not an integer.  Please try again.')
            sys.exit(2)

        if filter < 1:
            print ('upper bound must be a non-zero positive number. Please try again.')
            sys.exit(2)

sentenceDict = {}
tokenCountDict = {}
sentenceCount = 1
if stdinput:
    content = sys.stdin.read()
    print (content)
elif inputFileName == '':
    print("Time Before inputting the fileName: ", str(datetime.now()))
    inputFileName = input('Please enter a file name: ')
if os.path.isfile(inputFileName):
    inputFile = io.open(inputFileName, 'rU', encoding='utf-8')
    content = inputFile.read()
    inputFile.close()
elif not stdinput:
    print (inputFileName, ' does not exist. Please try again.')
    sys.exit(2)
if debug:
    print ('Debug mode!')
    print ('Input file:', inputFileName, '\n\n')
    print ('Whole Input File Content:', content, '\n\n===================\n\n')
tokens = re.findall('[\\w]+', content.lower().replace('\n', ' ').replace('\r', ' ').replace('. ', ' ').replace('; ', ' ').replace(', ', ' '))
for wordNum, word in enumerate(tokens):
    if word in sentenceDict:
        sentenceDict[word].append(str(wordNum))
    else:
        sentenceDict[word] = [str(wordNum)]

outputFile = open(outputFileName, 'w')
if debug:
    print ('Output file:', outputFileName, '\n\n')
    print ('Term Frequencies:')
outputFile.write('{')
printCounter = 0
for i in sentenceDict.keys():
    tokenCountDict[i] = len(sentenceDict[i])

tokenDict = sorted(tokenCountDict.items(), key=operator.itemgetter(1), reverse=True)
if alphabetical:
    for i, wordKey in enumerate(sorted(sentenceDict.keys())):
        if filterNum == 0 or filterNum != -1 and printCounter >= filterNum:
            break
        if (lowerNum == -1 or lowerNum <= wordKey[1]) and (upperNum == -1 or upperNum >= wordKey[1]):
            printCounter = printCounter + 1
            output(i, wordKey)

else:
    for i, wordKey in enumerate(tokenDict):
        if filterNum == 0 or filterNum > 0 and printCounter >= filterNum:
            break
        if (lowerNum == -1 or lowerNum <= wordKey[1]) and (upperNum == -1 or upperNum >= wordKey[1]):
            printCounter = printCounter + 1
            output(i, wordKey[0])


outputFile.write('}')
print("Time Before completing the file write function: ", str(datetime.now()))
outputFile.close()
print("Time After completing the file write function: ", str(datetime.now()))
if summary:
    print ('Summary:')
    print ('Tokens: ', len(tokens))
    print ('Unique Tokens: ', len(sentenceDict.keys()))
    print ('Max Fequency: ', tokenDict[0])
    print ('Min Fequency: ', tokenDict[len(tokenDict) - 1][1])